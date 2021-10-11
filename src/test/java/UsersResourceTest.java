import static common.APIResources.users;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.hasSize;

import common.TestBase;
import io.restassured.response.Response;
import java.io.File;
import java.util.List;
import models.User;
import org.junit.jupiter.api.Test;

public class UsersResourceTest extends TestBase {

    @Test()
    public void validateUsersJsonSchemaUser(){
        File jsonSchema = new File(System.getProperty("user.dir") + "/src/main/resources/json-schema/get-users-json-schema.json");

        given()
            .spec(requestSpec)
        .and()
            .get(users)
        .then()
            .spec(responseOkSpec)
        .and()
            .body(matchesJsonSchema(jsonSchema));
    }

    @Test()
    public void getUsers() {
        Response response =
            (Response) given()
                .and()
                    .spec(requestSpec)
                .and()
                   .get(users)
               .then()
                   .spec(responseOkSpec)
               .and()
                    .body("findAll{it.username.equals('Delphine')}", hasSize(1))
                    .extract();

        List<User> users =  response.body().jsonPath().getList(".", User.class);
    }
}
