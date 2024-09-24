package com.example.demo.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.dto.RestaurantDTO;
import com.example.demo.model.Restaurant;

@Service
public class RestaurantService {
    @Autowired
    private RestTemplate restTemplate;
    
    URI baseUrl = UriComponentsBuilder
            .fromHttpUrl("http://localhost:8080")
            .path("/api/restaurants")
            .build()
            .toUri();

    // Get all Restaurants
    public List<Restaurant> getAllRestaurant() {
        ResponseEntity<List<Restaurant>> response = 
            restTemplate.exchange(baseUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Restaurant>>() {});

        return response.getBody();
    }

    public List<RestaurantDTO> getAllRestaurantDTOs() {
        String url = baseUrl +"/registersDTO";
        ResponseEntity<List<RestaurantDTO>> response = 
            restTemplate.exchange(url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<RestaurantDTO>>() {});

        return response.getBody();
    }

    // Get Restaurant by id
    public Restaurant getRestaurantById(Long id) {
        String url = baseUrl + "/" + id;
        System.out.println(url);
        ResponseEntity<Restaurant> response = 
            restTemplate.exchange(url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Restaurant>(){});

        return response.getBody();
    }

    public RestaurantDTO getRestaurantDTOById(Long id) {
        String url = baseUrl + "/" + id;
        System.out.println(url);
        ResponseEntity<RestaurantDTO> response = 
            restTemplate.exchange(url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<RestaurantDTO>(){});

        return response.getBody();
    }

    // Create Restaurant
    public Restaurant createRestaurant(Restaurant restaurant) {
        ResponseEntity<Restaurant> response = 
        restTemplate.postForEntity(baseUrl,
            restaurant,
            Restaurant.class);
        
        return response.getBody();
    }
    
    // Update Restaurant detail
    public void updateRestaurant(Long id, Restaurant restaurant){
        String url = baseUrl + "/" + id;
        restTemplate.put(url, restaurant);
    }

    // Delete Restaurant by id
    public void deleteRestaurantById(Long id){
        String url = baseUrl + "/" + id;
        restTemplate.delete(url);
    }


}

