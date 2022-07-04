package one.digitalinnovation.personapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PersonApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(PersonApiApplication.class, args);

    }
}
