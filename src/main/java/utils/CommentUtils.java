package utils;

import static common.APIResources.postIdComments;
import static common.TestBase.responseOkSpec;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import java.util.List;
import models.Comment;

public class CommentUtils {

    public static List<Comment> getCommentsPerPostId(int postId) {
        Response response =
            given().pathParam("postId", postId).get(postIdComments).then().spec(responseOkSpec).extract().response();
        List<Comment> commentList = response.body().jsonPath().getList(".", Comment.class);
        return commentList;
    }
}