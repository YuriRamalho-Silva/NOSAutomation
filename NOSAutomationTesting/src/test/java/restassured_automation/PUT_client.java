package restassured_automation;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import utils.ReportAPI;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PUT_client {

    Random random = new Random();
    int randomInt = random.nextInt(200);
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

    String body = "{\n" +
            "    \"name\": \"Amy Ya\",\n" +
            "    \"gender\": \"male\",\n" +
            "    \"id\": 994957,\n" +
            "    \"email\": \"adinath_ms_guneta@kub-pollich.info\",\n" +
            "    \"status\": \"inactive\"\n" +
            "}";

    @Test
    public void PUT_ShouldReturnEditClientWithSuccess() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 25569952dfcaa58e1b35c0ad778dbcb10d91eba0e49f6490114627673b254078")
                .accept("*/*")
                .body("{\n" +
                        "    \"name\": \"Amy Yanglang" + randomInt + "\"\n" +
                        "}")
                .when()
                .put("https://gorest.co.in/public/v2/users/994957")
                .then()
                .log().all()
                .body("name", Matchers.is("Amy Yanglang" + randomInt))
                .statusCode(200);
        report.endExecution();

    }

    @Test
    public void PUT_ShouldExceptionsForNullFileds() {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 25569952dfcaa58e1b35c0ad778dbcb10d91eba0e49f6490114627673b254078")
                .accept("*/*")
                .body("{\n" +
                        "    \"name\": \"\",\n" +
                        "    \"gender\": \"\",\n" +
                        "    \"id\": 0,\n" +
                        "    \"email\": \"\",\n" +
                        "    \"status\": \"\"\n" +
                        "}")
                .when()
                .put("https://gorest.co.in/public/v2/users/994957")
                .then()
                .log().all()
                .body("field", hasSize(4))
                .body("message", hasSize(4))
                .body("field", hasItems("email", "name", "gender", "status"))
                .body("message[-1]", is("can't be blank"))
                .statusCode(422);
        report.endExecution();


    }

    @Test
    public void PUT_ShouldExceptionsForIncorrectFields() {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 25569952dfcaa58e1b35c0ad778dbcb10d91eba0e49f6490114627673b254078")
                .accept("*/*")
                .body("{\n" +
                        "    \"name\": \"Amy Ysa\",\n" +
                        "    \"gender\": \"1545\",\n" +
                        "    \"id\": 994957,\n" +
                        "    \"email\": \"incorrect.com\",\n" +
                        "    \"status\": \"incorrect\"\n" +
                        "}")
                .when()
                .put("https://gorest.co.in/public/v2/users/994957")
                .then()
                .log().all()
                .body("field", hasSize(3))
                .body("message", hasSize(3))
                .body("field", hasItems("email", "gender", "status"))
                .body("message[-1]", is("is invalid"))
                .statusCode(422);
        report.endExecution();


    }

    @Test
    public void PUT_ShouldExceptionsForincorrectHeader() {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "25569952dfcaa58e1b35c0ad778dbcb10d91eba0e49f6490114627673b254078")
                .accept("*/*")
                .body(body)
                .when()
                .put("https://gorest.co.in/public/v2/users/994957")
                .then()
                .log().all()
                .statusCode(404)
                .body("message", is("Resource not found"));

        report.endExecution();

    }

    @Test
    public void PUT_ShouldExceptionsForincorrectClient() {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "25569952dfcaa58e1b35c0ad778dbcb10d91eba0e49f6490114627673b254078")
                .accept("*/*")
                .body(body)
                .when()
                .put("https://gorest.co.in/public/v2/users/994957")
                .then()
                .log().all()
                .statusCode(404)
                .body("message", is("Resource not found"));
        report.endExecution();


    }


}
