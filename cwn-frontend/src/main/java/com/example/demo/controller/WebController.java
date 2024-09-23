package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.ReviewResponseDTO;
import com.example.demo.model.Restaurant;
import com.example.demo.model.User;
import com.example.demo.service.RestaurantService;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/restaurants")
public class WebController {
     
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    

    // Display all restaurants
    @GetMapping
    public String getAllRestaurants(Model model, @RequestParam(value = "username", required = false) String username) {
        List<Restaurant> restaurants = restaurantService.getAllRestaurant();
        if (username != null) {
            User user = userService.getUserByUsername(username);
            System.out.println(user.getUsername());
        }
        model.addAttribute("restaurants", restaurants);
        System.out.println("\n\n----------------------------------------\n");
        System.out.println("user name : " + username);
        model.addAttribute("username", username);
        //model.addAttribute("user", user);
        return "restaurant-list";
    }

    // Display a single restaurant
    @GetMapping("/{id}")
    public String getRestaurantById(@PathVariable Long id, @RequestParam String username, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByRestaurant(id);
        System.out.println("\n\n----------------------------------------\n");
        System.out.println("us name : "+username);
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("reviews", reviews);
        
        model.addAttribute("username", username);
        return "restaurant-detail";
    }

    // Create new restaurant form
    @GetMapping("/new")
    public String showCreateRestaurantForm(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        return "restaurant-form";
    }

    // Submit new restaurant
    @PostMapping("/new")
    public String createRestaurant(@ModelAttribute Restaurant restaurant) {
        restaurantService.createRestaurant(restaurant);
        return "redirect:/restaurants";
    }

    // Edit restaurant form
    @GetMapping("/{id}/edit")
    public String showEditRestaurantForm(@PathVariable Long id, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        model.addAttribute("restaurant", restaurant);
        return "restaurant-edit";
    }

    // Update restaurant
    @PostMapping("/{id}/edit")
    public String updateRestaurant(@PathVariable Long id, @ModelAttribute Restaurant restaurant) {
        restaurantService.updateRestaurant(id, restaurant);
        return "redirect:/restaurants";
    }

    // Delete restaurant
    @GetMapping("/{id}/delete")
    public String deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurantById(id);
        return "redirect:/restaurants";
    }

    // Create new review for a restaurant
    @PostMapping("/{restaurantId}/reviews")
    public String createReview(@PathVariable Long restaurantId, @ModelAttribute ReviewDTO reviewDTO, @RequestParam String username) {
        Long userId = userService.getUserByUsername(username).getId();
        reviewService.createReview(restaurantId, reviewDTO, userId);
        return "redirect:/restaurants/" + restaurantId + "?username=" + username ;
    }

    // Delete a review
    @GetMapping("/reviews/{reviewId}/delete")
    public String deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/restaurants";
    }

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
    public String addFavoriteRestaurant(@PathVariable Long userId, @PathVariable Long restaurantId) {
        userService.addFavoriteRestaurant(userId, restaurantId);
        return "redirect:/users/" + userId;
    }

    // Remove favorite restaurant
    @PostMapping("/users/{userId}/favorites/{restaurantId}/remove")
    public String removeFavoriteRestaurant(@PathVariable Long userId, @PathVariable Long restaurantId) {
        userService.removeFavoriteRestaurant(userId, restaurantId);
        return "redirect:/users/" + userId;
    }
}
    

