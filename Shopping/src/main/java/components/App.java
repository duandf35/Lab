package components;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Wenyu
 * @since 12/19/16
 */
@SpringBootApplication
@EnableAutoConfiguration
public class App {

    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }
}
