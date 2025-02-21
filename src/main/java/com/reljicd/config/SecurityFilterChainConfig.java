package com.reljicd.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Security Filter Chain Configuration
 */
@Configuration
@EnableWebSecurity
public class SecurityFilterChainConfig {

    private final AccessDeniedHandler accessDeniedHandler;
    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Autowired
    public SecurityFilterChainConfig(AccessDeniedHandler accessDeniedHandler, DataSource dataSource, PasswordEncoder passwordEncoder) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for demo purposes (enable in production)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/home", "/registration", "/error", "/blog/**", "/post/**", "/h2-console/**").permitAll()
                        .requestMatchers("/newPost/**", "/commentPost/**", "/createComment/**").hasRole("USER")
                        .anyRequest().authenticated()
                    )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home")
                        .permitAll()
                    )
                .logout(logout -> logout.permitAll())
                .exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler))
                // Fix for H2 console
                .headers(headers -> headers.frameOptions().disable());

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .usersByUsernameQuery(usersQuery)
            .authoritiesByUsernameQuery(rolesQuery)
            .dataSource(dataSource)
            .passwordEncoder(passwordEncoder);
    }
}
