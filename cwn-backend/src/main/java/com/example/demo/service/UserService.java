package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import com.example.demo.dto.UserRegistrationDTO;
import com.example.demo.model.Restaurant;
import com.example.demo.model.Role;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }   

    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        // Check if the username or email already exists
        if (userRepository.findByUsername(userRegistrationDTO.getUsername()) == null){
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(userRegistrationDTO.getEmail()) == null) {
            throw new RuntimeException("Email already exists");
        }

        // Create a new User entity
        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword())); // Encrypt the password
        user.setEmail(userRegistrationDTO.getEmail());
        user.setRole(Arrays.asList(new Role("USER"))) ;

        // Save the user
        userRepository.save(user);
        
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

    //private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // public void registerUser(String username, String password) {
    //     if (userRepository.findByUsername(username)) {
    //         throw new RuntimeException("User already exists");
    //     }

    //     User user = new User();
    //     user.setUsername(username);
    //     user.setPassword(passwordEncoder.encode(password));
    //     userRepository.save(user);
    // }

    // public User loginUser(String username, String password) {
    //     Optional<User> userOptional = userRepository.findByUsername(username);

    //     if (userOptional.isEmpty() || !passwordEncoder.matches(password, userOptional.get().getPassword())) {
    //         throw new RuntimeException("Invalid username or password");
    //     }

    //     return userOptional.get();
    // }
}


    



