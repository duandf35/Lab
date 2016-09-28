package com.wenyu.brain.design.platform.config;

import com.wenyu.brain.design.platform.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Wenyu
 * @since 9/24/16
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthService authService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .anyRequest().authenticated()
                        .and()
                    .formLogin()
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/welcome", true)
                        .and()
                    .httpBasic()
                        .and()
                    .logout().permitAll();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(authService);
    }
}
