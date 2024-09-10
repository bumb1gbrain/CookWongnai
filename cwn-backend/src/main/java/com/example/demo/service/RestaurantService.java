package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Restaurant;
import com.example.demo.repository.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long id, Restaurant updatedRestaurant) {
        return restaurantRepository.findById(id)
            .map(restaurant -> {
                restaurant.setName(updatedRestaurant.getName());
                restaurant.setLocation(updatedRestaurant.getLocation());
                restaurant.setCuisine(updatedRestaurant.getCuisine());
                return restaurantRepository.save(restaurant);
            }).orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}
