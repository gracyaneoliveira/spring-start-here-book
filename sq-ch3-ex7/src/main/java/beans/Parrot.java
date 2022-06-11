package beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Parrot {

  private String name = "Koko";

  private final Person person;

  @Autowired
  public Parrot(Person person) {
    this.person = person;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Person getPerson() {
    return person;
  }

  @Override
  public String toString() {
    return "Parrot : " + name;
  }
}
