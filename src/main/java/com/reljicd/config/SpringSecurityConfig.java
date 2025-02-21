package com.reljicd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Enable method security
public class SpringSecurityConfig {

    @Value("${spring.admin.username}")
    private String adminUsername;

    @Value("${spring.admin.password}")
    private String adminPassword;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser(adminUsername)
            .password("{noop}" + adminPassword) // No encoding for in-memory for simplicity
            .roles("ADMIN");
    }
}
