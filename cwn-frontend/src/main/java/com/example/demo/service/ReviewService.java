package com.example.demo.service;

import java.util.List;
import java.util.Arrays;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;



import com.example.demo.dto.ReviewResponseDTO;
import com.example.demo.dto.ReviewDTO;


@Service
public class ReviewService {
    
    @Autowired
    private RestTemplate restTemplate;

    private final String baseUrl = "http://localhost:8080/api/restaurants"; // Update baseUrl as needed

    public ReviewService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Fetch reviews for a specific restaurant
    public List<ReviewResponseDTO> getReviewsByRestaurant(Long restaurantId) {
        // Using UriComponentsBuilder to construct the URL
        URI uri = UriComponentsBuilder
                .fromHttpUrl(baseUrl)
                .path("/{restaurantId}/reviews")
                .buildAndExpand(restaurantId)
                .toUri();

        ResponseEntity<ReviewResponseDTO[]> response = restTemplate.getForEntity(uri, ReviewResponseDTO[].class);
        return Arrays.asList(response.getBody());
    }

    // Create a review for a restaurant
    public void createReview(Long restaurantId, ReviewDTO reviewDTO) {
        // Using UriComponentsBuilder to construct the URL
        URI uri = UriComponentsBuilder
                .fromHttpUrl(baseUrl)
                .path("/{restaurantId}/reviews")
                .buildAndExpand(restaurantId)
                .toUri();

        restTemplate.postForEntity(uri, reviewDTO, ReviewDTO.class);
    }

    // Delete a review by its ID
    public void deleteReview(Long reviewId) {
        // Using UriComponentsBuilder to construct the URL
        URI uri = UriComponentsBuilder
                .fromHttpUrl(baseUrl)
                .path("/review/{reviewId}")
                .buildAndExpand(reviewId)
                .toUri();

        restTemplate.delete(uri);
    }
}
