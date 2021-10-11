import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class TestClass extends TestBase{


    @Test()
    public void getUsers() {
        Response postsResponse =
             given()
                    .get("/users")
                .then()
                    .statusCode(200)
                .and()
                    .log()
                    .body()
                    .extract()
                    .response();
    }

}
