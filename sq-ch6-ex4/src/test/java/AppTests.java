import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import aspects.LoggingAspect;
import config.ProjectConfig;
import model.Comment;
import services.CommentService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ProjectConfig.class })
public class AppTests {

    private Logger serviceLogger;
    private Logger aspectLogger;

    @Autowired
    private LoggingAspect loggingAspect;

    @Autowired
    private CommentService commentService;

    @BeforeEach
    public void before() {
        this.aspectLogger = mock(Logger.class);
        loggingAspect.setLogger(aspectLogger);

        this.serviceLogger = mock(Logger.class);
        commentService.setLogger(serviceLogger);
    }

    @Test
    @DisplayName("Test that the aspect intercepts and alters the execution" +
        " of the deleteComment() method.")
    public void testAspectInterceptsDeleteCommentMethod() {
        Comment comment = new Comment();
        comment.setText("Test comment text");
        comment.setAuthor("Test comment author");

        commentService.deleteComment(comment);

        verify(serviceLogger).info("Deleting comment:" + comment.getText());
        verify(aspectLogger).info("Method deleteComment with parameters [Comment{text='Test comment text', author='Test comment author'}] will execute");
        verify(aspectLogger).info("Method executed and returned null");
    }

    @Test
    @DisplayName("Test that the aspect doesn't intercept the execution" +
        " of the publishComment() method.")
    public void testAspectDoesntInterceptPublishCommentMethod() {
        Comment comment = new Comment();
        comment.setText("Test comment text");
        comment.setAuthor("Test comment author");

        commentService.publishComment(comment);

        verify(serviceLogger).info("Publishing comment:" + comment.getText());
        verify(aspectLogger, never()).info(anyString());
        verify(aspectLogger, never()).info(anyString());
    }

    @Test
    @DisplayName("Test that the aspect doesn't intercept the execution" +
        " of the editComment() method.")
    public void testAspectDoesntInterceptEditCommentMethod() {
        Comment comment = new Comment();
        comment.setText("Test comment text");
        comment.setAuthor("Test comment author");

        commentService.editComment(comment);

        verify(serviceLogger).info("Editing comment:" + comment.getText());
        verify(aspectLogger, never()).info(anyString());
        verify(aspectLogger, never()).info(anyString());
    }
}
