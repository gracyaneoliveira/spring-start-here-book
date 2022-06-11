package beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {

  private String name = "Ella";

  private Parrot parrot;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Parrot getParrot() {
    return parrot;
  }

  @Autowired
  public void setParrot(Parrot parrot) {
    this.parrot = parrot;
  }
}
