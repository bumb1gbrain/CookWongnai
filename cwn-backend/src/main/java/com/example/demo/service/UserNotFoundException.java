package com.example.demo.service;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id){
        super("User not found with id:" + id);
    }

    
}