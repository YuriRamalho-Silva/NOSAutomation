package restassured_automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import utils.ReportAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DELETE_client {

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
    public void DELETE_Integration_GET_ShouldReturnExclusionWithSuccess() {
        Response response = RestAssured.get(BaseUrl);

        int id = response
                .then()
                .extract()
                .path("id[0]");
        response = given()
                .header("Authorization", "Bearer 25569952dfcaa58e1b35c0ad778dbcb10d91eba0e49f6490114627673b254078")
                .when()
                .delete(BaseUrl + id);
        response.then()
                .statusCode(204);
        response = given()
                .header("Authorization", "Bearer 25569952dfcaa58e1b35c0ad778dbcb10d91eba0e49f6490114627673b254078")
                .when()
                .get(BaseUrl + id);
        response.then()
                .log().all()
                .statusCode(404);
        report.endExecution();


    }

    @Test
    public void DELETE_ShouldReturnErrorWithNoAuth() {
        Response response =
                given()
                        .header("Authorization", "incorrect")
                        .when()
                        .delete(BaseUrl);
        response.then()
                .statusCode(404);
        report.endExecution();

    }

    @Test
    public void DELETE_ShouldReturnErrorForNonexistentClient() {
        Response response =
                given()
                        .header("Authorization", "Bearer 25569952dfcaa58e1b35c0ad778dbcb10d91eba0e49f6490114627673b254078")
                        .when()
                        .delete(BaseUrl + "000000");
        response.then()
                .body("message", is("Resource not found"))
                .statusCode(404);
        report.endExecution();

    }


}
