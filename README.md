# Spring Start Here
![Isso é uma imagem](./spring-start-here.jpg)
&nbsp;
<p>&nbsp;</p>

## 2 The Spring context: Defining beans
#### You can add beans in the context in the following ways
- Using the @Bean annotation
- Using stereotype annotations
- Programmatically (example below)

```
public class Parrot {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

```
public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Parrot x = new Parrot();
        x.setName("Kiki");

        Supplier<Parrot> parrotSupplier = () -> x;

        //context.registerBean("parrot1", Parrot.class, parrotSupplier);

        context.registerBean("parrot1",
                Parrot.class,
                parrotSupplier,
                bc -> bc.setPrimary(true));

        Parrot p = context.getBean(Parrot.class);

        System.out.println(p.getName());
    }
}

```