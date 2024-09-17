package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.demo.model.Restaurant;
import com.example.demo.model.User;
import com.example.demo.model.Review;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.*;


@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    public Review createReview(Long restaurantId, Long userId, Review review) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + restaurantId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        review.setRestaurant(restaurant);
        review.setUser(user);

        return reviewRepository.save(review);
    }

    public ReviewDTO createReview(Long restaurantId, Long userId, ReviewDTO reviewDTO) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + restaurantId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        Review review = new Review();
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        review.setRestaurant(restaurant);
        review.setUser(user);
        reviewRepository.save(review);
        
        reviewDTO.setId(review.getId());
        return reviewDTO;
    }

    public List<Review> getReviewsByRestaurant(Long restaurantId) {
            // Optionally, verify if restaurant exists
            if (!restaurantRepository.existsById(restaurantId)) {
                throw new RuntimeException("Restaurant not found with id " + restaurantId);
            }
            return reviewRepository.findByRestaurantId(restaurantId);
        }
        public List<Review> getReviewsByUser(Long userId) {
        // Optionally, verify if user exists
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id " + userId);
        }
        return reviewRepository.findByUserId(userId);
    }
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with id " + reviewId));
    }

    public Review updateReview(Long id, Review reviewDetails){
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));;
        review.setComment(reviewDetails.getComment());
        review.setRating(reviewDetails.getRating());
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id){  
        Review review = getReviewById(id);
        reviewRepository.delete(review);
    }

        // public List<Review> getAllReviews(){
    //     return reviewRepository.findAll();
    // }

    // public Optional<Review> getReviewById(Long id){
    //     return reviewRepository.findById(id);
    // }

    // public List<Review> getReviewsByRestaurant(Long restaurantId) {
    //     return reviewRepository.findByRestaurantId(restaurantId);
    // }

    // public List<Review> getReviewsByUser(Long userId){
    //     return reviewRepository.findByUserId(userId);
    // }

    // public Review createReview(Review review){
    //     return reviewRepository.save(review);
    // }
}
