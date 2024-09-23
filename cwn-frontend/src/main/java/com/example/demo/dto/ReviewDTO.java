package com.example.demo.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private int rating;
    private String comment;
    private Long userId;
    private String username;
    
}