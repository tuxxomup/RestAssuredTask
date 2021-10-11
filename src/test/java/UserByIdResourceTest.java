import static common.APIResources.userById;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.equalTo;
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

public class UserByIdResourceTest extends TestBase {

    //@Test
    public void validateUserJsonSchemaUser(){
        File jsonSchema = new File(System.getProperty("user.dir") + "/src/main/resources/json-schema/get-user-json-schema.json");

        List<User> userList = getAllUsers();
        //Get first met userId
        int firstUserId = userList.stream().findFirst().get().getId();

        given()
            .spec(requestSpec)
        .and()
            .pathParam("userId", firstUserId)
        .then()
            .spec(responseOkSpec)
        .and()
            .body(matchesJsonSchema(jsonSchema));
    }

   // @Test
    public void validateUserByUserIdAPI() throws JSONException {

        User user = getUserDataByUsername(Constants.delphineUserSearchTerms);

        Response response = given()
                .spec(requestSpec)
            .and()
                .pathParam("userId", user.getId())
            .and()
                .get(userById)
            .then()
                .spec(responseOkSpec)
                .body("username", equalTo(Constants.delphineUserSearchTerms))
            .and()
                .extract()
                .response();

        User userResponse = response.body().as(User.class);

        String responseUserJson = gson.toJson(userResponse);
        String expectedUserJson = gson.toJson(User.prepareDelphineUser());

        //Validate both objects as JSONs
        MatcherAssert.assertThat(JsonUtils.assertEquals(responseUserJson, expectedUserJson), is(Boolean.TRUE));
    }


}
