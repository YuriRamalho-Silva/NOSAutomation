package restassured_automation;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import utils.ReportAPI;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class POST_client {

    Random random = new Random();
    int randomInt = random.nextInt(200);
    String BaseUrl = "https://gorest.co.in/public/v2/users/";
    static ReportAPI report = new ReportAPI();

    @BeforeAll
    static void iniciation() {
        report.createReport();
    }

    @BeforeEach
    void createTest(TestInfo info) {
        report.createTest(info.getTestMethod().get().getName());
    }

    @AfterAll
    static void end() {
        report.endExecution();
    }


    String bodyRandom = "{\n" +
            "    \"name\": \"Amy Ya\",\n" +
            "    \"gender\": \"male\",\n" +
            "    \"id\": 994957,\n" +
            "    \"email\": \"email@" + randomInt + ".info\",\n" +
            "    \"status\": \"inactive\"\n" +
            "}";

    String token = "Bearer 25569952dfcaa58e1b35c0ad778dbcb10d91eba0e49f6490114627673b254078";


    @Test
    public void POST_ShouldReturnClientWithSuccess() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .accept("*/*")
                .body(bodyRandom)
                .when()
                .post(BaseUrl)
                .then()
                .log().all()
                .statusCode(201);
        report.endExecution();

    }

    @Test
    public void POST_Integration_GET_ShouldReturnExceptionForUserAlreadyCreated() {
        String client = createClient();
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .accept("*/*")
                .body(client)
                .when()
                .post(BaseUrl)
                .then()
                .statusCode(422)
                .body("field[0]", is("email"))
                .body("message[0]", is("has already been taken"))
                .log().all();
        report.endExecution();

    }

    @Test
    public void POST_ShouldReturnExceptionForIncorrectBdyFields() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .accept("*/*")
                .body("{\n" +
                        "    \"name\": \"1234\",\n" +
                        "    \"gender\": \"1234\",\n" +
                        "    \"email\": \"incorrect.com\",\n" +
                        "    \"status\": \"incorrect\"\n" +
                        "}")
                .when()
                .post(BaseUrl)
                .then()
                .log().all()
                .body("field", hasSize(3))
                .body("message", hasSize(3))
                .body("field", hasItems("email", "gender", "status"))
                .body("message[0]", is("can't be blank, can be male of female"))
                .body("message[-1]", is("is invalid"))
                .statusCode(422);
        report.endExecution();

    }

    @Test
    public void POST_ShouldReturnExceptionForNullBdyFields() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .accept("*/*")
                .body("{\n" +
                        "    \"name\": \"\",\n" +
                        "    \"gender\": \"\",\n" +
                        "    \"email\": \"\",\n" +
                        "    \"status\": \"\"\n" +
                        "}")
                .when()
                .post(BaseUrl)
                .then()
                .log().all()
                .body("field", hasSize(4))
                .body("field", hasItems("email", "name", "gender", "status"))
                .body("message", hasSize(4))
                .statusCode(422);
        report.endExecution();

    }

    @Test
    public void POST_ShouldReturnExceptionForInvalidEmail() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .accept("*/*")
                .body("{\n" +
                        "    \"name\": \"Amy Yang\",\n" +
                        "    \"gender\": \"female\",\n" +
                        "    \"email\": \"incorrect.com\",\n" +
                        "    \"status\": \"active\"\n" +
                        "}")
                .when()
                .post(BaseUrl)
                .then()
                .log().all()
                .body("field[0]", is("email"))
                .body("message[0]", is("is invalid"))
                .statusCode(422);
        report.endExecution();

    }

    public String createClient() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .accept("*/*")
                .body(bodyRandom)
                .when()
                .post(BaseUrl)
                .then()
                .log().all()
                .statusCode(201);

        return bodyRandom;
    }

}
