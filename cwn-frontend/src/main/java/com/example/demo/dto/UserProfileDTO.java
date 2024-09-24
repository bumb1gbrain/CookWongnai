package com.example.demo.dto;

import lombok.Data;

@Data
public class UserProfileDTO {
    private Long id;
    private String name;
    private String username;
    private String email;

    public UserProfileDTO(Long id, String name, String username, String email){
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;

    }

    
}
