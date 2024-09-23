package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserRegistrationDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;


@Controller
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute("user")
    public UserRegistrationDTO userRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDTO,
            RedirectAttributes redirectAttributes) {
        try {
            System.out.println("justwannaprint");
            userService.createUser(registrationDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful! You can now log in.");
            return "redirect:/login";
        } catch (Exception e) {
            System.out.println("Error");
            redirectAttributes.addFlashAttribute("errorMessage", "Registration failed: " + e.getMessage());
            return "redirect:/registration";
        }
    }

    @GetMapping("/login")
    public String getlogin(Model model) {
        model.addAttribute("user", new LoginDTO());
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @ModelAttribute LoginDTO loginDTO) {
        User user = userService.getUserByUsername(username);

        // Check if the user exists
        if (user != null) {
            // Validate the provided password with the hashed password in the database
            if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                // Password is correct
                return "redirect:/restaurants?username=" + username;
            } else {
                // Password is incorrect
                return "redirect:/login?error=invalidPassword";
            }
        } else {
            // User does not exist
            return "redirect:/login?error=userNotFound";
        }
    }

}
