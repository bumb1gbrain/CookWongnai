package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.demo.model.Restaurant;
import com.example.demo.model.User;
import java.util.Optional;

import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    public void addFavoriteRestaurant(Long userId, Long restaurantId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found"));

        if (!user.getFavoriteRestaurants().contains(restaurant)) {
            user.getFavoriteRestaurants().add(restaurant);
            userRepository.save(user); 
        }
    }

    public void removeFavoriteRestaurant(Long userId, Long restaurantId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found"));

        if (user.getFavoriteRestaurants().contains(restaurant)) {
            user.getFavoriteRestaurants().remove(restaurant);
            userRepository.save(user);
        }
    }

    public List<Restaurant> getFavoriteRestaurants(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFavoriteRestaurants();
    }
}


    



