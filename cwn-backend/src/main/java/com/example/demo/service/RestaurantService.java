package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RestaurantDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Restaurant;
import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    public Optional<RestaurantDTO> getRestaurantDTOById(Long id){
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return Optional.of(new RestaurantDTO(restaurant.getId(), restaurant.getName(), restaurant.getUsersWhoFavorited()));
    }


    public List<RestaurantDTO> getAllRestaurantsDTO(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(restaurant -> new RestaurantDTO(restaurant.getId(), restaurant.getName(), restaurant.getUsersWhoFavorited()))
                .collect(Collectors.toList());
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
            
    @Transactional
    public void deleteRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        RestaurantDTO restaurantDTO = new RestaurantDTO(restaurant.getId(), restaurant.getName(), restaurant.getUsersWhoFavorited());
            
        List<Review> reviews = reviewRepository.findByRestaurantId(restaurantId);
        System.out.println("\n\n\n\n\n\n\n"+reviews + "__________________________________________________________________________________-All of the review\n\n\n\n\n\n\n " );
        for(var e:reviews){
            System.out.println("!!!!!!!!!!!!!!!!!______________    review :    " + e.getComment());
            
        }
        reviewRepository.deleteAll(reviews);
        
        // if(restaurant.getUsersWhoFavorited() != null){
        // Remove restaurant from each user's favorite list
        List<User> users = restaurantDTO.getUsersWhoFavorited();
        System.out.println(users + "_________ users object");
        for(var e:users){
            System.out.println("\n\n\n--------------------------     =   " + e.getUsername());
        }
        for (User user : restaurantDTO.getUsersWhoFavorited()) {
            user.getFavoriteRestaurants().remove(restaurant);
        }
    
        // Optionally, save the users to update the favorites relationship in the database
        // Assuming you have a user repository to save the changes
        for (User user : restaurant.getUsersWhoFavorited()) {
            userRepository.save(user); // Update user with removed restaurant
        }
        
        // Finally, delete the restaurant
        restaurantRepository.delete(restaurant);
    }
}
