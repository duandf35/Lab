package components.portal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyu
 * @since 9/28/16
 */
@RestController
public class PortalController {

    @RequestMapping(method = RequestMethod.GET, value = "/ok")
    public String ok() {
        return "ok";
    }
}
