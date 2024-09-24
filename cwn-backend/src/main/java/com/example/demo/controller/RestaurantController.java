package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RestaurantDTO;
import com.example.demo.dto.ReviewDTO;
import com.example.demo.model.Restaurant;
import com.example.demo.model.Review;
import com.example.demo.service.RestaurantService;
import com.example.demo.service.ReviewService;



@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/restaurantsDTO")
    public List<RestaurantDTO> getAllRestaurantDTOs(){
        return restaurantService.getAllRestaurantsDTO();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(id);
        return restaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id,
            @RequestBody Restaurant restaurantDetails) {
        try {
            Restaurant updatedRestaurant = restaurantService.updateRestaurant(id, restaurantDetails);
            return ResponseEntity.ok(updatedRestaurant);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }
    
    // ที่จะลองใช้ DTO created
        @PostMapping("/{id}/reviews")
        public ResponseEntity<ReviewDTO> createReview(@PathVariable Long id,
                                                    @RequestBody ReviewDTO reviewDTO) {
            try {
                ReviewDTO createdReviewDTO = reviewService.createReview(id,reviewDTO);
                return new ResponseEntity<>(createdReviewDTO, HttpStatus.CREATED);
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }

    // @PostMapping("/{id}/reviews")
    // public ResponseEntity<ReviewDTO> createReview(@PathVariable Long id,
    //                                                @RequestBody ReviewDTO reviewDTO) {
    //     Long userId = 1L;                                            
    //     try {
    //         ReviewDTO createdReviewDTO = reviewService.createReview(id, userId, reviewDTO);
    //         return new ResponseEntity<>(createdReviewDTO, HttpStatus.CREATED);
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    //     }
    // }                                                             

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<Review>> getReviewsByRestaurant(@PathVariable Long id) {
        List<Review> reviews = reviewService.getReviewsByRestaurant(id);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }


}



