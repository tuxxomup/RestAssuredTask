package utils;

import static common.APIResources.postIdComments;
import static common.TestBase.responseOkSpec;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import models.Comment;
import models.Post;
import org.apache.commons.validator.routines.EmailValidator;

public class CommentUtils {

    public static List<Comment> getCommentsPerPostId(int postId) {
        Response response =
            given().pathParam("postId", postId).get(postIdComments).then().spec(responseOkSpec).extract().response();
        List<Comment> commentList = response.body().jsonPath().getList(".", Comment.class);
        return commentList;
    }

    public static List<Comment> getInvalidEmailInPostComments(List<Post> posts){

        List<Comment> allInvalidComments =
            collectAllCommentsPerPosts(posts).stream().filter(u -> !EmailValidator.getInstance().isValid(u.getEmail())).collect(Collectors.toList());
        return allInvalidComments;
    }

    public static List<Comment> collectAllCommentsPerPosts(List<Post> posts){
        List<Comment> allCommentPerPost = new ArrayList<>();
        for(Post post: posts){
            allCommentPerPost.addAll(getCommentsPerPostId(post.getId()));
        }
        return allCommentPerPost;
    }
}