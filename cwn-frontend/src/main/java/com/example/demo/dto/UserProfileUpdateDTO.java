 package com.example.demo.dto;

import lombok.Data;

@Data
 public class UserProfileUpdateDTO {
    private Long id;
    private String name;

    public UserProfileUpdateDTO(Long id,String name){
        this.id = id;
        this.name = name;
    }

    
}