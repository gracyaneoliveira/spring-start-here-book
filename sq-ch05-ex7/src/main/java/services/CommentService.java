package services;

import model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import processors.CommentProcessor;
import repositories.CommentRepository;

@Service
public class CommentService {

  @Autowired
  private ApplicationContext context;

  public void sendComment(Comment c) {
    CommentProcessor p = context.getBean(CommentProcessor.class);

    p.setComment(c);
    p.processComment(c);
    p.validateComment(c);

    c = p.getComment();
    // do something further
  }

}
