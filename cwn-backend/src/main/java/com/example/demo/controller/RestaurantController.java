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
    // @PostMapping("/{id}/reviews")
    // public ResponseEntity<ReviewDTO> createReview(@PathVariable Long id,
    //                                                @RequestParam Long userId,
    //                                                @RequestBody ReviewDTO reviewDTO) {
    //     try {
    //         ReviewDTO createdReviewDTO = reviewService.createReview(id, userId, reviewDTO);
    //         return new ResponseEntity<>(createdReviewDTO, HttpStatus.CREATED);
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    //     }
    // }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewDTO> createReview(@PathVariable Long id,
                                                   @RequestBody ReviewDTO reviewDTO) {
        Long userId = 1L;                                            
        try {
            ReviewDTO createdReviewDTO = reviewService.createReview(id, userId, reviewDTO);
            return new ResponseEntity<>(createdReviewDTO, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }                                                             

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<Review>> getReviewsByRestaurant(@PathVariable Long id) {
        List<Review> reviews = reviewService.getReviewsByRestaurant(id);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

//     @PostMapping("/{restaurantId}/reviews")
//     public ResponseEntity<ReviewResponseDTO> createReview(
//             @PathVariable Long restaurantId,
//             @RequestParam Long userId,
//             @RequestBody ReviewRequestDTO reviewRequestDTO) {
        
//         Review review = new Review();
//         review.setRating(reviewRequestDTO.getRating());
//         review.setComment(reviewRequestDTO.getComment());

//         Review createdReview = reviewService.createReview(restaurantId, userId, review);

//         ReviewResponseDTO responseDTO = new ReviewResponseDTO(
//                 createdReview.getId(),
//                 createdReview.getRating(),
//                 createdReview.getComment(),
//                 createdReview.getUser().getUsername()
//         );

//         return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
//     }

//     // Get all reviews for a specific restaurant
//     @GetMapping("/{restaurantId}/reviews")
//     public ResponseEntity<List<ReviewResponseDTO>> getReviewsByRestaurant(@PathVariable Long restaurantId) {
//         List<Review> reviews = reviewService.getReviewsByRestaurant(restaurantId);
//         List<ReviewResponseDTO> responseDTOs = reviews.stream().map(review -> 
//             new ReviewResponseDTO(
//                 review.getId(),
//                 review.getRating(),
//                 review.getComment(),
//                 review.getUser().getUsername()
//             )
//         ).collect(Collectors.toList());

//         return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
//     }

//     // Get all reviews by a specific user
//     @GetMapping("/users/{userId}/reviews")
//     public ResponseEntity<List<ReviewResponseDTO>> getReviewsByUser(@PathVariable Long userId) {
//         List<Review> reviews = reviewService.getReviewsByUser(userId);
//         List<ReviewResponseDTO> responseDTOs = reviews.stream().map(review -> 
//             new ReviewResponseDTO(
//                 review.getId(),
//                 review.getRating(),
//                 review.getComment(),
//                 review.getUser().getUsername()
//             )
//         ).collect(Collectors.toList());

//         return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
//     }

//     // Get a specific review by ID
//     @GetMapping("/reviews/{reviewId}")
//     public ResponseEntity<ReviewResponseDTO> getReviewById(@PathVariable Long reviewId) {
//         Review review = reviewService.getReviewById(reviewId);
//         ReviewResponseDTO responseDTO = new ReviewResponseDTO(
//                 review.getId(),
//                 review.getRating(),
//                 review.getComment(),
//                 review.getUser().getUsername()
//         );
//         return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//     }

//     // Update a review
//     @PutMapping("/reviews/{reviewId}")
//     public ResponseEntity<ReviewResponseDTO> updateReview(
//             @PathVariable Long reviewId,
//             @RequestBody ReviewRequestDTO reviewRequestDTO) {
        
//         Review reviewDetails = new Review();
//         reviewDetails.setRating(reviewRequestDTO.getRating());
//         reviewDetails.setComment(reviewRequestDTO.getComment());

//         Review updatedReview = reviewService.updateReview(reviewId, reviewDetails);

//         ReviewResponseDTO responseDTO = new ReviewResponseDTO(
//                 updatedReview.getId(),
//                 updatedReview.getRating(),
//                 updatedReview.getComment(),
//                 updatedReview.getUser().getUsername()
//         );

//         return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//     }

//     // Delete a review
//     @DeleteMapping("/reviews/{reviewId}")
//     public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
//         reviewService.deleteReview(reviewId);
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     }
}



