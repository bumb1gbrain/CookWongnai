// package com.example.demo.config;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import com.example.demo.service.UserService;

// @Component
// public class DataLoader implements CommandLineRunner {

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @Override
//     public void run(String... args) throws Exception {
//         // Check if the user already exists
//         if (userService.getAllUser().isEmpty()) {
//             // Create and save default user
//             User user = new User();
//             user.setUsername("admin");
//             user.setPassword(passwordEncoder.encode("defaultPassword")); // Hash the password
//             user.setEmail("admin@example.com");
//             // Set other fields if necessary

//             userService.createUser(user); // Save the user
//         }
//     }
// }
