package com.example.demo.dto;

import lombok.Data;

@Data
public class ReviewResponseDTO{
    private Long id;
    private int rating;
    private String comment;
    private String username;
    private Long userId;

    

}