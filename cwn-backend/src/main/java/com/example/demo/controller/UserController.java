package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRegistrationDTO;
import com.example.demo.model.Restaurant;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/byUsername/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        // Assuming UserService has a method getUserByUsername
        User user = userService.getUserByUsername(username);

        if (user != null) {
            // If the user is found, return it with status 200 (OK)
            return ResponseEntity.ok(user);
        } else {
            // If the user is not found, return a 404 (Not Found)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        try {
            userService.createUser(userRegistrationDTO);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Add a restaurant to the user's favorites
    @PostMapping("/{userId}/favorites/{restaurantId}")
    public void addFavoriteRestaurant(@PathVariable Long userId, @PathVariable Long restaurantId) {
        userService.addFavoriteRestaurant(userId, restaurantId);
    }

    // Remove a restaurant from the user's favorites
    @DeleteMapping("/{userId}/favorites/{restaurantId}")
    public void removeFavoriteRestaurant(@PathVariable Long userId, @PathVariable Long restaurantId) {
        userService.removeFavoriteRestaurant(userId, restaurantId);
    }

    // Get a user's favorite restaurants
    @GetMapping("/{userId}/favorites")
    public List<Restaurant> getFavoriteRestaurants(@PathVariable Long userId) {
        return userService.getFavoriteRestaurants(userId);
    }
}
