package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.dto.UserRegistrationDTO;
import com.example.demo.model.Restaurant;
import com.example.demo.model.User;

import jakarta.transaction.Transactional;

@Service
public class UserService {
// implements UserDetailsService 
    @Autowired
    private RestTemplate restTemplate;

    private String baseUrl = "http://localhost:8080/api/users";

    public List<User> getAllUser(){
        ResponseEntity<List<User>> response =
        restTemplate.exchange(baseUrl,HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<User>>() {}
        );
        
    return response.getBody();
    }

    public User getUserById(Long id){
        String url = baseUrl + "/" + id;
        ResponseEntity<User> response =  restTemplate.exchange(url,HttpMethod.GET,null
                ,new ParameterizedTypeReference<User>() {});
        return response.getBody();
    }

    public User getUserByUsername(String username) {
        String url = baseUrl + "/byUsername/" + username;
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
        return response.getBody();
    }
    // @Transactional
    // public User createUser(UserRegistrationDTO user) {
    //     // Ensure baseUrl points to the correct endpoint
    //     System.out.println("ebum");
    //     String url = "http://localhost:8080/api/users";  // Example URL, replace with your actual API URL
    //     ResponseEntity<User> response = restTemplate.postForEntity(url, user, User.class);
    //     System.out.println("check");
        
    //     // Check if the response is successful
    //     if (response.getStatusCode().is2xxSuccessful()) {
    //         return response.getBody();
    //     } else {
    //         throw new RuntimeException("Failed to create user: " + response.getStatusCode());
    //     }
    // }

    @Transactional
    public User createUser(UserRegistrationDTO userRegistrationDTO) {
        // Define the API URL where the user is to be created
        String url = "http://localhost:8080/api/users";  // Replace with your actual API URL

        try {
            // Send a POST request to the API to create a user
            ResponseEntity<User> response = restTemplate.postForEntity(url, userRegistrationDTO, User.class);

            // Check if the response is successful (status 2xx)
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();  // Return the created user object
            } else {
                // Handle non-2xx responses with an informative exception
                throw new RuntimeException("Failed to create user. Status Code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            // Log or handle any other exceptions (like connection errors)
            throw new RuntimeException("An error occurred while creating the user: " + e.getMessage(), e);
        }
    }

    public void updateUser(Long id, User user){
        String url = baseUrl + "/" + id;
        restTemplate.put(url, user);
    }

    public void deleteUserById(Long id){
        String url = baseUrl + "/" + id;
        restTemplate.delete(url);
    }

    public void addFavoriteRestaurant(Long userId, Long restaurantId){
        String url = baseUrl + "/" + userId + "/favorites/" + restaurantId;
        restTemplate.postForLocation(url, null);
    }

    public void removeFavoriteRestaurant(Long userId, Long restaurantId){
        String url = baseUrl + "/" + userId + "/favorites/" + restaurantId + "/remove";
        restTemplate.delete(url);
    }

    public List<Restaurant> getFavoriteRestaurants(Long userId){
        String url = baseUrl + "/" + userId + "/favorites";
        ResponseEntity<List<Restaurant>> response =
            restTemplate.exchange(url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Restaurant>>() {});

        return response.getBody();
    }

    public String getUsernameById(Long userId) {
    String url = baseUrl + "/" + userId; // กำหนด URL สำหรับดึงข้อมูลผู้ใช้
    ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.GET, null, User.class);
    User user = response.getBody();
    
    return user != null ? user.getUsername() : null; // คืนชื่อผู้ใช้ถ้าพบ ไม่เช่นนั้นคืน null
}


    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     String url = baseUrl + "/byUsername/" + username; // ต้องเป็น / + username เสยๆ
    //     UserResponseDTO userResponseDTO = restTemplate.getForObject(url, UserResponseDTO.class);
    //     if (userResponseDTO == null) {
    //         throw new UsernameNotFoundException("Invalid username or password");
    //     }

    //     return org.springframework.security.core.userdetails.User
    //             .withUsername(userResponseDTO.getUsername())
    //             .password(userResponseDTO.getPassword())
    //             .roles("USER") // Adjust roles if needed
    //             .build();
    // }

    
    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     String url = baseUrl + "/byUsername/" + username; // Construct the URL
    //     User user = restTemplate.getForObject(url, User.class);


    //     if (user == null) {
    //         throw new UsernameNotFoundException("User not found");
    //     }

    //     // Ensure authorities are not null
    //     List<GrantedAuthority> authorities = getAuthorities(user); // Fetch roles dynamically
    //     return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    // }

    // private List<GrantedAuthority> getAuthorities(User user) {
    //     // Example: Assign a single role "ROLE_USER". You can change this to reflect your application's roles.
    //     return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    // }

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//     String url = baseUrl + "/byUsername/" + username; // Construct the URL
//     UserResponseDTO user = restTemplate.getForObject(url, UserResponseDTO.class);

//     if (user == null) {
//         throw new UsernameNotFoundException("User not found");
//     }

//     System.out.println(user.getUsername());

//     // Ensure authorities are not null
//     List<GrantedAuthority> authorities = getAuthorities(user.getRole()); // Fetch roles dynamically
//     return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
// }

//     private List<GrantedAuthority> getAuthorities(List<Role> roles) {
//     if (roles == null || roles.isEmpty()) {
//         return Collections.singletonList(new SimpleGrantedAuthority("USER")); // Default role if none exist
//     }
//     // Map roles to GrantedAuthority
//     return roles.stream()
//                 .map(role -> new SimpleGrantedAuthority("USER")) // Prefix "ROLE_" for roles
//                 .collect(Collectors.toList());
// }

    
// @Override
// public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//     String url = baseUrl + "/byUsername/" + username; // Construct the URL
//     UserResponseDTO user = restTemplate.getForObject(url, UserResponseDTO.class);

//     if (user == null) {
//         throw new UsernameNotFoundException("User not found");
//     }

//     System.out.println("User found: " + user.getUsername());
//     System.out.println("User roles: " + user.getRole());
//     System.out.println("User pass " + user.getPassword());

//     // Ensure authorities are not null
//     List<GrantedAuthority> authorities = getAuthorities(user.getRole()); // Fetch roles dynamically
//     // Ensure password is not null or empty
//     if (user.getPassword() == null || user.getPassword().isEmpty()) {
//         throw new InternalAuthenticationServiceException("User password cannot be null or empty");
//     }

//     return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
// }
}



