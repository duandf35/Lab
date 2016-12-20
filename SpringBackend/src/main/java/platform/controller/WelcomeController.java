package platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Wenyu
 * @since 9/28/16
 */
@Controller
public class WelcomeController {

    @RequestMapping(method= RequestMethod.GET, value="/welcome")
    public String show(final Model model) {
        return "welcome";
    }
}
