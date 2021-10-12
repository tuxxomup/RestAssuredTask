package utils;

import java.util.List;
import models.Comment;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoggerUtils {

    public static final Logger logger = LogManager.getLogger(LoggerUtils.class);

    public static void logInvalidEmails(List<Comment> invalidEmail, int userId) {
        for (Comment comment : invalidEmail) {
            String logInvalidEmail = String.format(
                "Post ID:%s by user with ID:%d and comment ID:%s has an email:%s, which is wrong!!!",
                comment.getPostId(), userId, comment.getId(), comment.getEmail());
            logger.info(logInvalidEmail);
        }
    }
}
