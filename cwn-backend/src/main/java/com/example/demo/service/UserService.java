package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import com.example.demo.dto.RestaurantDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserRegistrationDTO;
import com.example.demo.model.Restaurant;
import com.example.demo.model.User;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
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
    public List<UserDTO> getAllUsersDTO(){
        List<User> users = userRepository.findAll();
        return users.stream()
        .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getFavoriteRestaurants()))
        .collect(Collectors.toList());
    }


    // public Optional<UserDTO> getUserDTOById(Long id){
    //     User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    //     return Optional.of(new UserDTO(user.getId(), user.getUsername(), user.getFavoriteRestaurants()));
    // }   

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public void createUser(UserRegistrationDTO userRegistrationDTO) {
        // Check if the username or email already exists
        if (userRepository.findByUsername(userRegistrationDTO.getUsername()) != null){
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(userRegistrationDTO.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        // Create a new User entity
        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword())); // Encrypt the password
        user.setEmail(userRegistrationDTO.getEmail());
        user.setRole("USER");
        //user.setRole(Arrays.asList(new Role("USER"))) ;

        // Save the user
        userRepository.save(user);
        
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if(userDetails.getUsername() != null) {
            user.setUsername(userDetails.getUsername());
            } 
            if(userDetails.getPassword() != null) {
            user.setPassword(userDetails.getPassword());
            }
            if(userDetails.getName() != null) {
            user.setName(userDetails.getName());
            }
            if(userDetails.getEmail() != null) {
            user.setEmail(userDetails.getEmail());
            }
            if(userDetails.getRole() != null) {
            user.setRole(userDetails.getRole());
            }
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
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Ensure authorities are not null
        List<GrantedAuthority> authorities = getAuthorities(user);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    private List<GrantedAuthority> getAuthorities(User user) {
        // Example: Assign a single role "ROLE_USER". You can change this to reflect your application's roles.
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }

     public String getUsernameById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUsername();
    }



   
}


    



