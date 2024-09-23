package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.ReviewResponseDTO;
import com.example.demo.model.Restaurant;
import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.service.RestaurantService;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            List<Restaurant> favoriteRestaurants = userService.getFavoriteRestaurants(user.getId());

            // Create a list of favorite restaurant IDs
            List<Long> favoriteRestaurantIds = favoriteRestaurants.stream()
                    .map(Restaurant::getId)
                    .collect(Collectors.toList());

            // Add attributes to the model
            model.addAttribute("user", user);
            model.addAttribute("restaurants", restaurants);
            model.addAttribute("username", username);

            model.addAttribute("favoriteRestaurantIds", favoriteRestaurantIds); // Pass the IDs to the model

            // Debugging output
            System.out.println(user.getUsername());
            System.out.println(user.getRole());
            System.out.println("\n\n----------------------------------------\n");
            System.out.println("user name : " + username);
            for (var e : favoriteRestaurants) {
                System.out.println(e.getName());
            }

            return "restaurant-list";
        } else {
            model.addAttribute("user", new User());
            model.addAttribute("restaurants", restaurants);
            model.addAttribute("username", username);
            model.addAttribute("favoriteRestaurantIds", new ArrayList<>() {

            });

            return "restaurant-list";
        }

    }

    // Display a single restaurant
    @GetMapping("/{id}")
    public String getRestaurantById(@PathVariable Long id, Model model,
            @RequestParam(value = "username", required = false) String username) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        List<Review> reviews = reviewService.getReviewsByRestaurant(id);
        System.out.println("\n\n----------------------------------------\n");
        System.out.println("us name : " + username);
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("reviews", reviews);
        model.addAttribute("username", username);

        return "restaurant-detail";
    }

    // Create new restaurant form
    @GetMapping("/new")
    public String showCreateRestaurantForm(Model model,
            @RequestParam(value = "username", required = false) String username) {
        model.addAttribute("restaurant", new Restaurant());
        model.addAttribute("username", username);
        return "restaurant-form";
    }

    // Submit new restaurant
    @PostMapping("/new")
    public String createRestaurant(@ModelAttribute Restaurant restaurant,
            @RequestParam(value = "username", required = false) String username) {
        restaurantService.createRestaurant(restaurant);
        return "redirect:/restaurants" + "?username=" + username;
    }

    // Edit restaurant form
    @GetMapping("/{id}/edit")
    public String showEditRestaurantForm(@PathVariable Long id, Model model,
            @RequestParam(value = "username", required = false) String username) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("username", username);
        return "restaurant-edit";
    }

    // Update restaurant
    @PostMapping("/{id}/edit")
    public String updateRestaurant(@PathVariable Long id, @ModelAttribute Restaurant restaurant,
            @RequestParam(value = "username", required = false) String username) {
        restaurantService.updateRestaurant(id, restaurant);
        return "redirect:/restaurants" + "?username=" + username;
    }

    // Delete restaurant
    @GetMapping("/{id}/delete")
    public String deleteRestaurant(@PathVariable Long id,
            @RequestParam(value = "username", required = false) String username) {
        restaurantService.deleteRestaurantById(id);
        return "redirect:/restaurants" + "?username=" + username;
    }

    // Create new review for a restaurant
    @PostMapping("/{restaurantId}/reviews")
    public String createReview(@PathVariable Long restaurantId, @ModelAttribute ReviewDTO reviewDTO,
            @RequestParam String username) {
        Long userId = userService.getUserByUsername(username).getId();
        reviewService.createReview(restaurantId, reviewDTO, userId);
        return "redirect:/restaurants/" + restaurantId + "?username=" + username;
    }

    // Delete a review
    @GetMapping("/reviews/{reviewId}/delete")
    public String deleteReview(@PathVariable Long reviewId,
            @RequestParam(value = "username", required = false) String username) {
        reviewService.deleteReview(reviewId);
        return "redirect:/restaurants" + "?username=" + username;
    }

}
