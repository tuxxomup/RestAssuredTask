import static common.APIResources.posts;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertTrue;

import common.Constants;
import common.TestBase;
import java.util.List;
import models.Comment;
import models.Post;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;
import utils.CommentUtils;
import utils.PostUtils;

public class PostResourceTest extends TestBase {

    @Test()
    public void validateComment() {

        given()
            .spec(requestSpec)
        .and()
            .get(posts)
        .then()
            .spec(responseOkSpec)
        .and()
            .body("data.size()",
                greaterThanOrEqualTo(1))//Basic check that there is at least one post
        .and()
            .extract()
            .response();

        List<Post> userPostList = PostUtils.getPostPerUsername(Constants.delphineUserSearchTerms);

        for(Post post: userPostList){

            List<Comment> userCommentList = CommentUtils.getCommentsPerPostId(post.getUserId());
            for(Comment comment: userCommentList){
                String errorMessage =
                    String.format("Post ID:%d by user with ID:%d and comment ID:%d has a email:%s, which is wrong!!!",
                        post.getId(), post.getUserId(), comment.getId(), comment.getEmail());

                assertTrue(errorMessage, EmailValidator.getInstance().isValid(comment.getEmail()));
            }
        }
    }
}
