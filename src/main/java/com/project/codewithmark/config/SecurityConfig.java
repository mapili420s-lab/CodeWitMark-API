package com.project.codewithmark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                // Disable CSRF for APIs/testing
                                .csrf(csrf -> csrf.disable())
                                // Authorization rules
                                .authorizeHttpRequests(auth -> auth
                                                // Landing page and static resources accessible without login
                                                .requestMatchers("/", "/index", "/terms", "/privacy", "/contact_us",
                                                                "/user",
                                                                "/therapist",
                                                                "/admin",
                                                                "/css/**",
                                                                "/images/**",
                                                                "/js/**")
                                                .permitAll()
                                                // Open API endpoints (like user registration)
                                                .requestMatchers("/api/users/**").permitAll()
                                                .requestMatchers("/api/therapists/**").permitAll()
                                                .requestMatchers("/api/appointments/**").permitAll()
                                                .requestMatchers("/api/service-types/**").permitAll()
                                                .requestMatchers(HttpMethod.PATCH, "/your-endpoint/**").permitAll()
                                                // Everything else requires authentication
                                                .anyRequest().authenticated())
                                // Enable form login for browser UI
                                .formLogin(form -> form
                                                .loginPage("/") // optional custom login page
                                                .permitAll());
                // // Enable HTTP Basic for API testing
                // .httpBasic(httpBasic -> {
                // });

                return http.build();
        }
}
