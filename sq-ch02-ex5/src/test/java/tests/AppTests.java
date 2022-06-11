package tests;

import config.ProjectConfig;
import main.Parrot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ProjectConfig.class })
public class AppTests {

    @Autowired
    private ApplicationContext context;

    @Test
    @DisplayName("Test that the Parrot instance parrot2 named Miki is primary")
    public void testParrot2IsPrimary() {
        Parrot p = context.getBean(Parrot.class);

        assertEquals("Miki", p.getName());
    }

}
