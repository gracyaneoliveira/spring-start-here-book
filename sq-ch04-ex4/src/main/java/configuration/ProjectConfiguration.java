package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import proxies.CommentNotificationProxy;
import proxies.EmailCommentNotificationProxy;
import repositories.CommentRepository;
import repositories.DBCommentRepository;
import services.CommentService;

@Configuration
public class ProjectConfiguration {

  @Bean
  public CommentRepository commentRepository() {
    return new DBCommentRepository();
  }

  @Bean
  public CommentNotificationProxy commentNotificationProxy() {
    return new EmailCommentNotificationProxy();
  }

  @Bean
  public CommentService commentService(CommentRepository commentRepository,
                                       CommentNotificationProxy commentNotificationProxy) {
    return new CommentService(commentRepository, commentNotificationProxy);
  }
}
