package com.wenyu.brain.design.platform.service;

import com.wenyu.brain.design.platform.dao.UserDAO;
import com.wenyu.brain.design.platform.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Wenyu
 * @since 9/28/16
 */
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userDAO.findByUsername(username);

        if (users.size() == 0 || users.size() > 1) {
            throw new UsernameNotFoundException("Username: " + username + " not found!");
        }

        User user = users.get(0);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
