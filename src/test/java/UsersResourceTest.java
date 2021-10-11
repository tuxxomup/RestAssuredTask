import static common.APIResources.users;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static utils.UserUtils.getAllUsers;
import static utils.UserUtils.getUserDataByUsername;

import common.Constants;
import common.JsonUtils;
import common.TestBase;
import io.restassured.response.Response;
import java.io.File;
import java.util.List;
import models.User;
import org.hamcrest.MatcherAssert;
import org.json.JSONException;
import org.junit.Test;

public class UsersResourceTest extends TestBase {

    @Test()
    public void validateUsersJsonSchemaUser() {
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
    public void getUsers() throws JSONException {
        Response response =
            (Response) given()
            .and()
                .spec(requestSpec)
            .and()
                .get(users)
            .then()
                .spec(responseOkSpec)
            .and()
                .body(String.format("findAll{it.username.equals('%s')}", Constants.delphineUserSearchTerms), hasSize(1))
                .extract();

        List<User> users = getAllUsers();

        User user = getUserDataByUsername(Constants.delphineUserSearchTerms);
        String responseUserJson = gson.toJson(user);
        String expectedUserJson = gson.toJson(User.prepareDelphineUser());

        //Validate both objects as JSONs
        MatcherAssert.assertThat(JsonUtils.assertEquals(responseUserJson, expectedUserJson),
            is(Boolean.TRUE));

    }
}
