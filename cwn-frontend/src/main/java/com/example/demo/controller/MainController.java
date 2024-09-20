package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.demo.service.UserService;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.model.User;

@Controller
public class MainController {

    @Autowired
    public UserService userService;
    // @GetMapping("/login")
    // public String login(@RequestParam(value = "error", required = false) String error,
    //                     @RequestParam(value = "logout", required = false) String logout,
    //                     Model model) {
    //     if (error != null) {
    //         model.addAttribute("error", "Invalid username or password.");
    //     }
    //     if (logout != null) {
    //         model.addAttribute("logout", "You have been logged out.");
    //     }
    //     return "login"; // Return the name of the HTML template (login.html)
    // }


    @GetMapping("/login")
    public String loginPage() {
        return "login";  // Return the login page (login.html)'
    }

    // @PostMapping("/login")
    // public ResponseEntity<User> login(@RequestBody UserResponseDTO dto) {
    //     // Handle authentication logic here
    //     return ResponseEntity.ok(userService);
    // }
}
    


