import static common.APIResources.users;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.hasSize;

import io.restassured.response.Response;
import java.io.File;
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

    @Test()
    public void validateUsersJsonSchemaUser(){
        File jsonSchema = new File(System.getProperty("user.dir") + "/src/main/resources/json-schema/get-users-json-schema.json");

        given()
            .and()
                .get(users)
            .then()
                .body(matchesJsonSchema(jsonSchema));
    }
}
