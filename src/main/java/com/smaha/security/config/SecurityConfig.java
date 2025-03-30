package com.smaha.security.config;

/*
    @author taras
    @project security
    @class SecurityConfig
    @version 1.0.0
    @since 30.03.25 - 14.50
*/

import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("Security configuration is being applied");

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/index.html").permitAll()
                                .requestMatchers("/api/v1/students/hello/unknown").permitAll()
                                .requestMatchers("/api/v1/students/hello/admin").hasAnyRole("ADMIN", "SUPERADMIN")
                                .requestMatchers("/api/v1/students/hello/user").hasAnyRole("USER", "ADMIN", "SUPERADMIN")
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        Dotenv dotenv = Dotenv.load();

        String adminPassword = dotenv.get("ADMIN_PASSWORD");
        String userPassword = dotenv.get("USER_PASSWORD");
        String superAdminPassword = dotenv.get("SUPER_ADMIN_PASSWORD");

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode(adminPassword))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode(userPassword))
                .roles("USER")
                .build();

        UserDetails superadmin = User.builder()
                .username("superadmin")
                .password(passwordEncoder().encode(superAdminPassword))
                .roles("SUPERADMIN")
                .build();


        return new InMemoryUserDetailsManager(admin, user, superadmin);
    }

}
