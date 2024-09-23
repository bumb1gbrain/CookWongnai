package com.example.demo.dto;
import lombok.Data;

@Data
public class UserResponseDTO {
    private String username;
    
    private String password;

    private String role;

    public UserResponseDTO() {
    }

    

    // public UserResponseDTO(String username, String password, List<Role> role) {
    //     this.username = username;
    //     this.password = password;
    //     this.role = role;
    // }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // public List<Role> getRole() {
    //     return role;
    // }

    // public void setRole(List<Role> role) {
    //     this.role = role;
    // }
    
}
