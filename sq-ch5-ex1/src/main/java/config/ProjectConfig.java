package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import services.CommentService;

@Configuration
public class ProjectConfig {

  @Bean
  public CommentService commentService() {
    return new CommentService();
  }
}
