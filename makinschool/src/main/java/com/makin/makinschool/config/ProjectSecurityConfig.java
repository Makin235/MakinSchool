package com.makin.makinschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth -> auth
//                .anyRequest().permitAll())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());

        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF protection
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "/home").permitAll()
                    .requestMatchers("/about").permitAll()
                    .requestMatchers("/courses").denyAll()
                    .requestMatchers("/holidays/**").permitAll()
                    .requestMatchers("/contact").authenticated()
                    .requestMatchers("/assets/**").permitAll()
            )
            .formLogin(Customizer.withDefaults()) // Enable form-based login with default settings
            .httpBasic(Customizer.withDefaults()); // Enable HTTP Basic authentication with default settings

        return http.build();
    }
}
