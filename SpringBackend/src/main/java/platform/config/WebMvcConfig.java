package platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Wenyu
 * @since 9/24/16
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * If no LoginController defined, Spring will register the DEFAULT login controller created by Spring.
     *
     * @param registry the registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
         registry.addViewController("/login").setViewName("login");
         registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
