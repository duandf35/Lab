package com.wenyu.brain.design.platform.controller;

import com.wenyu.brain.design.platform.dao.UserDAO;
import com.wenyu.brain.design.platform.model.LoginForm;
import com.wenyu.brain.design.platform.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

/**
 * @author Wenyu
 * @since 9/22/16
 */
@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(method=RequestMethod.GET, value="/login")
    public String getForm(final Model model) {
        model.addAttribute("loginForm", new LoginForm());

        return "login";
    }

    @RequestMapping(method=RequestMethod.POST, value="/login")
    public String handle(@ModelAttribute("loginForm") final LoginForm loginForm, final HttpSession session, final HttpServletRequest request) {

        LOG.info("Look up user by " + loginForm.toString());

        List<User> users = userDAO.findByUsername(loginForm.getUsername());

        if (users.isEmpty() || users.size() > 1) {
            return "redirect:/login?error";
        } else {
            return "redirect:/welcome";
        }
    }
}
