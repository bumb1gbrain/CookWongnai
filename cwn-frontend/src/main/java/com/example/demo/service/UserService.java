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

import com.example.demo.dto.RestaurantDTO;
import com.example.demo.dto.UserDTO;
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

    public List<UserDTO> getAllUserDTOs(){
        String url = baseUrl + "/usersDTO";
        ResponseEntity<List<UserDTO>> response =
        restTemplate.exchange(url,HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<UserDTO>>() {}
        );
        
    return response.getBody();
    }

    public UserDTO getUserDTOById(Long id){
        String url = baseUrl + "/" + id;
        ResponseEntity<UserDTO> response =  restTemplate.exchange(url,HttpMethod.GET,null
                ,new ParameterizedTypeReference<UserDTO>() {});
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

    public List<RestaurantDTO> getFavoriteRestaurants(Long userId){
        String url = baseUrl + "/" + userId + "/favorites";
        ResponseEntity<List<RestaurantDTO>> response =
            restTemplate.exchange(url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<RestaurantDTO>>() {});

        return response.getBody();
    }

    public String getUsernameById(Long userId) {
    String url = baseUrl + "/" + userId; // กำหนด URL สำหรับดึงข้อมูลผู้ใช้
    ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.GET, null, User.class);
    User user = response.getBody();
    
    return user != null ? user.getUsername() : null; // คืนชื่อผู้ใช้ถ้าพบ ไม่เช่นนั้นคืน null
}




   
}



