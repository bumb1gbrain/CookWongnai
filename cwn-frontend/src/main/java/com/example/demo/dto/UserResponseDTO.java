package com.example.demo.dto;
import java.util.List;
import com.example.demo.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserResponseDTO {
    private String username;
    
    private String password;

    private List<Role> role;

    public UserResponseDTO() {
    }

    

    public UserResponseDTO(String username, String password, List<Role> role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }



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

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }
    
}
