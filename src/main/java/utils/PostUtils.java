package utils;

import static common.APIResources.posts;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

import io.restassured.response.Response;
import java.util.List;
import java.util.stream.Collectors;
import models.Post;

public class PostUtils {

    public static List<Post> getPostPerUsername(String username){

        int userId = UserUtils.getUserIdByUsername(username);
        List<Post> posts = getAllPosts();
        List<Post> userPosts = posts.stream().filter(c -> c.getUserId() == (userId)).collect(Collectors.toList());
        return userPosts;
    }

    public static List<Post> getAllPosts(){
        Response postsResponse = given().get(posts).then().statusCode(SC_OK).extract().response();
        List<Post> posts = postsResponse.body().jsonPath().getList(".", Post.class);
        return posts;
    }
}
