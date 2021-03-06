import static common.APIResources.users;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.hasSize;
import static utils.FileUtils.getJsonSchema;
import static utils.UserUtils.getUserDataByUsername;

import common.Constants;
import common.TestBase;
import enums.SchemaEnum;
import java.io.File;
import models.User;
import org.json.JSONException;
import org.junit.Test;
import utils.JsonUtils;

public class UsersResourceTest extends TestBase {

    @Test()
    public void validateUsersJsonSchemaUser() {
        File jsonSchema = getJsonSchema(SchemaEnum.Users.value);

        given()
            .spec(requestSpec)
        .and()
            .get(users)
        .then()
            .spec(responseOkSpec)
        .assertThat()
            .body(matchesJsonSchema(jsonSchema));
    }

    @Test()
    public void validateUserByUsersAPI() throws JSONException {

          given()
            .and()
                .spec(requestSpec)
            .and()
                .get(users)
            .then()
                .spec(responseOkSpec)
            .and()
                .body(String.format("findAll{it.username.equals('%s')}", Constants.delphineUserSearchTerms), hasSize(1));

        User user = getUserDataByUsername(Constants.delphineUserSearchTerms);
        String responseUserJson = gson.toJson(user);
        String expectedUserJson = gson.toJson(User.prepareDelphineUser());

        //Validate both objects as JSONs
        JsonUtils.assertEquals(responseUserJson, expectedUserJson);
    }
}
