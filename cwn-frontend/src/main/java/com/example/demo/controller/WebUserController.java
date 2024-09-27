package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.RestaurantDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.model.Restaurant;
import com.example.demo.model.User;
import com.example.demo.service.UserService;


@Controller
public class WebUserController {
    @Autowired
    private UserService userService;

    


    // Display user details and favorite restaurants
    @GetMapping("/users/usersDTO/{id}")
    public String getUserDetails(@PathVariable Long id, Model model,@RequestParam(value = "username", required = false) String username) {
        UserDTO user = userService.getUserDTOById(id);
        List<RestaurantDTO> favoriteRestaurants = userService.getFavoriteRestaurants(id);
        for(var e: favoriteRestaurants){
            System.out.println("!!!!!!!!!!!!!!!!!! = = == = = = =====     "+ e.getName());
        }
        model.addAttribute("user", user);
        model.addAttribute("favorites", favoriteRestaurants);
        model.addAttribute("username",username );
        return "user-detail";
    }

    // Add favorite restaurant for a user
    @PostMapping("/users/{userId}/favorites/{restaurantId}")
    public String addFavoriteRestaurant(@PathVariable Long userId, @PathVariable Long restaurantId,@RequestParam(value = "username", required = false) String username){
        System.out.println(username + " form add fav res");
        userService.addFavoriteRestaurant(userId, restaurantId);
        return "redirect:/restaurants" +  "?username=" + username;
    }

    // Remove favorite restaurant
    @PostMapping("/users/{userId}/favorites/{restaurantId}/remove")
    public String removeFavoriteRestaurant(@PathVariable Long userId, @PathVariable Long restaurantId, @RequestParam(value = "username", required = false) String username) {
        userService.removeFavoriteRestaurant(userId, restaurantId);
        return "redirect:/restaurants" +  "?username=" + username;
    }

    @GetMapping("/users/{id}")
    public String getProfile(@PathVariable Long id, Model model,@RequestParam(value = "username", required = false)String username ) {
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        model.addAttribute("username",username);
        return "profile";
    }

    @PostMapping("/users/{id}/edit")
    public String updateProfile(@PathVariable Long id,Model model, @RequestParam(value = "username", required = false)String username, @ModelAttribute User user ){
        
    // Add the updated user to the model
        model.addAttribute("username", username);
        model.addAttribute("user", user);
        userService.updateUser(id, user);
        return "redirect:/users/" + id + "?username=" + username;
    }

    @GetMapping("/users/{id}/edit")
    public String showEditProfile(@PathVariable Long id, Model model, @RequestParam(value = "username", required = false)String username) {
        User user = userService.getUserById(id);
        System.out.println(user.getUsername());
        model.addAttribute("user",user);
        model.addAttribute("username",username);
        return "editProfile";
    }
    
    

}