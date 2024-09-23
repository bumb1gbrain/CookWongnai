package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Restaurant;
import com.example.demo.model.User;
import com.example.demo.service.RestaurantService;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;

@Controller
public class WebUserController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;


    // Display user details and favorite restaurants
    @GetMapping("/users/{id}")
    public String getUserDetails(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        List<Restaurant> favoriteRestaurants = userService.getFavoriteRestaurants(id);
        model.addAttribute("user", user);
        model.addAttribute("favorites", favoriteRestaurants);
        return "user-detail";
    }

    // Add favorite restaurant for a user
    @PostMapping("/users/{userId}/favorites/{restaurantId}")
    public String addFavoriteRestaurant(@PathVariable Long userId, @PathVariable Long restaurantId,@RequestParam(value = "username", required = false) String username){
        userService.addFavoriteRestaurant(userId, restaurantId);
        return "redirect:/restaurants" +  "?username=" + username;
    }

    // Remove favorite restaurant
    @PostMapping("/users/{userId}/favorites/{restaurantId}/remove")
    public String removeFavoriteRestaurant(@PathVariable Long userId, @PathVariable Long restaurantId, @RequestParam(value = "username", required = false) String username) {
        userService.removeFavoriteRestaurant(userId, restaurantId);
        return "redirect:/restaurants" +  "?username=" + username;
    }

}