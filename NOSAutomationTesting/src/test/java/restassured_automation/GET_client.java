package restassured_automation;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import utils.ReportAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class GET_client {

    String token = "Bearer 25569952dfcaa58e1b35c0ad778dbcb10d91eba0e49f6490114627673b254078";
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

    @Test
    public void GET_ShouldReturnSuccessWithNoIdSpecification() {
        Response response =
                given()
                        .header("Authorization", "25569952dfcaa58e1b35c0ad778dbcb10d91eba0e49f6490114627673b254078")
                        .when()
                        .get(BaseUrl);
        response.then()
                .body("id", Matchers.hasSize(10))
                .log().all()
                .statusCode(200);
        report.endExecution();

    }

    @Test
    public void GET_ShouldReturnSpecificClientWithSuccess() {
        Response response = RestAssured.get(BaseUrl);

        int id = response
                .then()
                .extract()
                .path("id[0]");
        given()
                .header("Authorization", token)
                .when()
                .get(BaseUrl + id)
                .then()
                .log().all()
                .body("id", is(id))
                .statusCode(200);
        report.endExecution();

    }

    @Test
    public void GET_ShouldReturnErrorWIthInvalidQueryParams() {
        given()
                .header("Authorization", token)
                .when()
                .get(BaseUrl + "incorrectId")
                .then()
                .log().all()
                .statusCode(404);
        report.endExecution();

    }


    @Test
    public void GET_ShouldReturnErrorWithUserNotCreated() {
        given()
                .header("Authorization", token)
                .when()
                .get(BaseUrl + 985621)
                .then()
                .body("message", CoreMatchers.containsString("Resource not found"))
                .log().all()
                .statusCode(404);
        report.endExecution();

    }

}
