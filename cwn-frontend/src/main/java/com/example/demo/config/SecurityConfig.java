// package com.example.demo.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Autowired
//     private UserDetailsService userDetailsService;

//     private JwtAuthEntryPoint authEntryPoint;

//     // จาดคลิป
//     // @Bean
//     // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//     //     http.csrf().disable()
//     //     .exceptionHandling()
//     //     .authenticationEntryPoint(authEntryPoint)
//     //     .and()
//     //     .sessionManagement()
//     //     .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//     //     .and()
//     //     .authorizeRequests()
//     //     .requestMatchers("/login","/registration").permitAll()
//     //     .anyRequest().authenticated()
//     //     .and()
//     //     .httpBasic();

//     //     return http.build(); 
//     // }


//     //อันที่ใช้ได้
//     // @Bean
//     // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//     //     http
//     //             .csrf(csrf -> csrf.disable())
//     //             .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(authEntryPoint))
//     //             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//     //             .authorizeHttpRequests(auth -> auth
//     //                     .requestMatchers("/login", "/registration").permitAll() // Fixed typo
//     //                     .anyRequest().authenticated());

//     //     http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

//     //     return http.build();
//     // }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(authEntryPoint))
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/login", "/registration").permitAll()
//                 .anyRequest().authenticated())
//             .httpBasic(Customizer.withDefaults()) // Use Customizer.withDefaults() to keep it Lambda style
//             .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Add JWT filter
    
//         return http.build();
//     }

//     @Bean
//     public JWTAuthenticationFilter jwtAuthenticationFilter() {
//         return new JWTAuthenticationFilter();
//     }

//     // @Bean
//     // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//     //     http
//     //         .authorizeHttpRequests((requests) -> requests
//     //             .requestMatchers("/", "/registration").permitAll()  // Define public endpoints
//     //             .anyRequest().authenticated()           // Require authentication for others
//     //         )
//     //         .formLogin((form) -> form
//     //             .loginPage("/login")  // Custom login page
//     //             .permitAll()
//     //         )
//     //         .logout((logout) -> logout.permitAll());

//     //     return http.build();
//     // }


//     // อันนี้คืออันที่ทำไว้
//     // @Bean
//     // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//     // return http
//     //         .authorizeHttpRequests(auth -> auth
//     //                 .requestMatchers("/login", "/registration").permitAll()
//     //                 .anyRequest().permitAll())
//     //         .formLogin(form -> form
//     //                 .loginPage("/login")
//     //                 .defaultSuccessUrl("/restaurants",true)
//     //                 .permitAll())
//     //         .logout((logout) -> logout
//     //                 .logoutUrl("/logout")
//     //                 .logoutSuccessUrl("/login?logout=true")
//     //                 .invalidateHttpSession(true)
//     //                 .deleteCookies("JSESSIONID")
//     //                 .permitAll())
//     //         .csrf(csrf -> csrf.disable()) // Disable CSRF for testing
//     //         .build();
//     // }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
//         return authenticationConfiguration.getAuthenticationManager();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

    

    
    
// }