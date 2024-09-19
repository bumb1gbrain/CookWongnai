package com.example.demo.service;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Long id){
        super("Restaurant not found with id: "+ id);
    }

    
}