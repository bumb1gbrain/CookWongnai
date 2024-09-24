package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.example.demo.model.Restaurant;
import com.example.demo.model.User;
import com.example.demo.model.Review;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.ReviewDTO;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    // // Model method to create a review
    // @Transactional
    // public Review createReview(Long restaurantId, Long userId, Review review) {
    //     Restaurant restaurant = restaurantRepository.findById(restaurantId)
    //             .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

    //     User user = userRepository.findById(userId)
    //             .orElseThrow(() -> new UserNotFoundException(userId));

    //     review.setRestaurant(restaurant);
    //     review.setUser(user);
    //     user.setId(userId);

    //     return reviewRepository.save(review);
    // }

    // DTO method to create a review
    public ReviewDTO createReview(Long restaurantId, ReviewDTO reviewDTO) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        User user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException(reviewDTO.getUserId()));
        Review review = new Review();
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        review.setRestaurant(restaurant);
        review.setUser(user);

        reviewRepository.save(review);
        return reviewDTO;
    }

    // Method to get reviews by restaurant
    public List<Review> getReviewsByRestaurant(Long restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new RestaurantNotFoundException(restaurantId);
        }
        return reviewRepository.findByRestaurantId(restaurantId);
    }

    // Method to get reviews by user
    public List<Review> getReviewsByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        return reviewRepository.findByUserId(userId);
    }

    // Model method to get a review by ID
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException(reviewId));
    }

    // DTO method to get a review by ID
    // public ReviewDTO getReviewByIdDTO(Long reviewId) {
    //     Review review = getReviewById(reviewId);
    //     return convertToDTO(review);
    // }

    // Model method to update a review
    @Transactional
    public Review updateReview(Long id, Review reviewDetails) {
        Review review = getReviewById(id);
        review.setComment(reviewDetails.getComment());
        review.setRating(reviewDetails.getRating());
        return reviewRepository.save(review);
    }

    // DTO method to update a review
    // @Transactional
    // public ReviewDTO updateReviewDTO(Long id, ReviewDTO reviewDTO) {
    //     Review review = getReviewById(id);
    //     review.setComment(reviewDTO.getComment());
    //     review.setRating(reviewDTO.getRating());
    //     reviewRepository.save(review);
    //     return convertToDTO(review);
    // }

    // Method to delete a review
    @Transactional
    public void deleteReview(Long id) {  
        Review review = getReviewById(id);
        reviewRepository.delete(review);
    }

    // Utility method to convert Review to ReviewDTO
    // private ReviewDTO convertToDTO(Review review) {
    //     return new ReviewDTO(
    //         review.getId(),
    //         review.getComment(),
    //         review.getRating(),
    //         review.getUser().getId(),
    //         review.getRestaurant().getId()
    //     );
    // }
}
