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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.example.demo.service.UserService;
//import com.example.demo.config.JWTGenerator;
import com.example.demo.dto.AuthResponseDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.model.User;

@Controller
public class MainController {

    @Autowired
    public UserService userService;

    // @Autowired
    // private JWTGenerator jwtGenerator;

    //public AuthenticationManager authenticationManager;
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


// @GetMapping("/login")
// public String loginPage() {
//     // Retrieve the authentication object from SecurityContextHolder
//     Authentication auth = SecurityContextHolder.getContext().getAuthentication();

//     // Check if the user is authenticated and not an anonymous user
//     if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
//         return "redirect:/home";  // Redirect to home if already logged in
//     }
//     return "login";  // Return the login page if not authenticated
// }

    @GetMapping("/login")
        public String getlogin(Model model) {
            model.addAttribute("user", new LoginDTO());
            return "login";
        }
        

    // @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    // public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
    //     Authentication authentication = authenticationManager.authenticate(
    //             new UsernamePasswordAuthenticationToken(loginDTO.getUsername()
    //             ,loginDTO.getPassword()));
    //     SecurityContextHolder.getContext().setAuthentication(authentication);
    //     String token = jwtGenerator.generateToken(authentication);
    //     System.out.println(token);
    //     return new ResponseEntity<>(new AuthResponseDTO(token),HttpStatus.OK);
    // }


    @PostMapping("/login")
    public String login(@RequestParam("username") String username) {
        User user = userService.getUserByUsername(username);
        if(user != null){
            return "redirect:/restaurants?username=" + username;
        }
        else{
            return "redirect:/login";
        }
    }
}
        
    
    

    


