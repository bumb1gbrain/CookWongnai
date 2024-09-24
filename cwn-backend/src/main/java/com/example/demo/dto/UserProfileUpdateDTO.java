package com.example.demo.dto;

import lombok.Data;

@Data
public class UserProfileUpdateDTO {
    private String name;

    public UserProfileUpdateDTO(String name){
        this.name = name;
    }
}
