import static common.APIResources.users;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class TestClass extends TestBase{


    @Test()
    public void getUsers() {
        Response postsResponse =
            (Response) given()
                   .get(users)
               .then()
                   .statusCode(200)
               .and()
                    .body("findAll{it.username.equals('Delphine')}", hasSize(1))
                    .extract();
    }
}
