import config.ProjectConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.CommentRepository;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ProjectConfig.class })
public class AppTests {

    @Autowired
    private ApplicationContext context;

    @Test
    @DisplayName("Verify that CommentRepository every time you request an instance" +
            " from the Spring context, you get a new instance")
    public void testCommentRepositoryIsPrototype() {
        var cr1 = context.getBean("commentRepository", CommentRepository.class);
        var cr2 = context.getBean("commentRepository", CommentRepository.class);

        assertNotEquals(cr1, cr2);
    }

}
