package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Restaurant;
import com.example.demo.repository.RestaurantRepository;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Restaurant updateRestaurant(Long id, Restaurant restaurantDetails) {
        Restaurant updateRestaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
        updateRestaurant.setName(restaurantDetails.getName());
        updateRestaurant.setOc_time(restaurantDetails.getOc_time());
        updateRestaurant.setLocation(restaurantDetails.getLocation());
        updateRestaurant.setTel(restaurantDetails.getTel());
        updateRestaurant.setType(restaurantDetails.getType());
        updateRestaurant.setPrice_range(restaurantDetails.getPrice_range());
        updateRestaurant.setDescription(restaurantDetails.getDescription());
        updateRestaurant.setPhotos(restaurantDetails.getPhotos());
        return restaurantRepository.save(updateRestaurant);
    }
            

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}
