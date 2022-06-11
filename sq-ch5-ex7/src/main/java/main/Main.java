package main;

import config.ProjectConfig;
import model.Comment;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.CommentService;

public class Main {

  public static void main(String[] args) {
    var c = new AnnotationConfigApplicationContext(ProjectConfig.class);

    var commentService = c.getBean(CommentService.class);

    commentService.sendComment(new Comment());
    commentService.sendComment(new Comment());
  }
}
