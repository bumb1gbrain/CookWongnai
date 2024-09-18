// package com.example.demo.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.model.User;
// import com.example.demo.service.UserService;

// @RestController
// @RequestMapping("/api/auth")
// public class AuthController {
//     @Autowired
//     private UserService userService;

//     @PostMapping("/register")
//     public ResponseEntity<?> registerUser(@RequestBody User userDTO) {
//         userService.registerUser(userDTO);
//         return ResponseEntity.ok("User registered successfully");
//     }
// }
