package utils;

import static common.APIResources.users;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

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
        List<User> allUsers = getAllUsers();
        User user = allUsers.stream().filter(u -> u.getUsername().equals(username)).findFirst().get();
        return user;
    }
}
