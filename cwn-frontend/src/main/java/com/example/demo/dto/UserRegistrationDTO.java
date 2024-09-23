package com.example.demo.dto;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRegistrationDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    @Size(min = 4, message = "Minimum Password Length is 4 characters")
    private String password;

    @NotEmpty
    @Email
    private String email;

    private String role;
    

    

   

    

}