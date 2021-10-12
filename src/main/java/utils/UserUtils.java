package utils;

import static common.APIResources.users;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertTrue;

import common.TestBase;
import io.restassured.response.Response;
import java.util.List;
import models.User;

public class UserUtils extends TestBase {

    public static List<User> getAllUsers() {
        Response response = (Response) given().get(users).then().statusCode(SC_OK).extract();
        List<User> users = response.body().jsonPath().getList(".", User.class);
        return users;
    }

    public static User getUserDataByUsername(String username) {
        Response response = given().queryParam("username", username).get(users).then().statusCode(SC_OK).extract().response();
        List<User> users = response.body().jsonPath().getList(".", User.class);
        assertTrue("There is more than user with name:" + username, users.size()==1);
        return users.get(0);
    }

    public static int getUserIdByUsername(String username){
        return getUserDataByUsername(username).getId();
    }
}
