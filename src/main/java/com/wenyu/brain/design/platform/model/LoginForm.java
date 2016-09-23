package com.wenyu.brain.design.platform.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Wenyu
 * @since 9/27/16
 */
public class LoginForm {

    @NotNull
    @Size(min=2, max=30)
    private String username;

    @NotNull
    @Size(min=2, max=50)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String toString() {
        return "LoginForm: [username: " + username + ", password: " + password + "]";
    }
}
