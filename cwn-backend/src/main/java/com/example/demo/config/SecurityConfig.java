package com.example.demo.config;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    @Autowired
    private UserDetailsService userDetailsService;

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        
    //     return http
    //             .authorizeHttpRequests(auth -> auth
    //                     .requestMatchers("/").permitAll()
    //                     .requestMatchers("/restaurants").permitAll()
    //                     .requestMatchers("/registration").permitAll()
    //                     .requestMatchers("/login").permitAll()
    //                     .requestMatchers("/logout").permitAll()
    //                     .anyRequest().authenticated())
    //             .formLogin(form -> form
    //                     .defaultSuccessUrl("/", true))
    //             .logout(config -> config.logoutSuccessUrl("/"))
    //             .csrf(csrf -> csrf.disable()) // Disable CSRF for testing, but enable it in production
    //             .build();
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth ->
                auth
                    .anyRequest().permitAll() // Allow all requests without authentication
            )
            .formLogin(formLogin ->
                formLogin
                    .disable() // Disable form login if not needed
            )
            .logout(logout ->
                logout
                    .disable() // Disable logout if not needed
            )
            .csrf(csrf ->
                csrf.disable() // Disable CSRF protection for simplicity (be cautious with this in production)
            );

        return http.build();
    }



    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;

    }

    @Bean
    public UserDetailsService userDetailsService() {
        String password = "password"; // Generate random password
        System.out.println("Generated password: " + password); // Print to terminal


        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode(password))
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("USER")))
                .build();
        
        return new InMemoryUserDetailsManager(user);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}