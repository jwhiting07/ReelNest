package com.reelnest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Marks this class as a Spring configuration class
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10); // strength 10
    }

    /**
     * Defines the Spring Security filter chain that applies to all HTTP requests.
     *
     * @param http the HttpSecurity object used to configure web-based security
     * @return the configured SecurityFilterChain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF (Cross-Site Request Forgery) protection.
                // CSRF is mainly a concern for browser-based sessions with cookies.
                // For APIs (espectially stateless REST APIs), it's common to disable this.
                .csrf(csrf -> csrf.disable())

                // Configure request authorization rules
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // allow all requests
                );

        // Build and return the configured filter chain
        return http.build();
    }
}