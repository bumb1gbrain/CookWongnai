package com.example.demo.dto;

public class ReviewDTO {
    private Long id;
    private int rating;
    private String comment;
    private Long userId;
    private Long restaurantId;
    
    public ReviewDTO(){
        
    }

    
    public ReviewDTO(Long id, int rating, String comment, Long userId, Long restaurantId) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.userId = userId;
        this.restaurantId = restaurantId;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public int getRating() {
        return rating;
    }


    public void setRating(int rating) {
        this.rating = rating;
    }


    public String getComment() {
        return comment;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }


    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getRestaurantId() {
        return restaurantId;
    }


    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    
}