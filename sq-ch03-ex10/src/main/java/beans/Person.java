package beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person {

  private String name = "Ella";

  private final Parrot parrot;

  public Person(@Qualifier("parrot2") Parrot parrot) {
    this.parrot = parrot;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Parrot getParrot() {
    return parrot;
  }

}
