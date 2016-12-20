package platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * @author Wenyu
 * @since 12/19/16
 */
@SpringBootApplication
public class App extends SpringBootServletInitializer {

    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }
}
