import static common.APIResources.posts;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertTrue;
import static utils.UserUtils.getUserIdByUsername;

import common.Constants;
import common.TestBase;
import java.util.List;
import models.Comment;
import models.Post;
import org.junit.Test;
import utils.CommentUtils;
import utils.LoggerUtils;
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
        List<Comment> commentsWithInvalidEmail = CommentUtils.getInvalidEmailInPostComments(userPostList);
        LoggerUtils.logInvalidEmails(commentsWithInvalidEmail, getUserIdByUsername(Constants.delphineUserSearchTerms));
        assertTrue("There is/are invalid email formats", commentsWithInvalidEmail.isEmpty());
    }
}
