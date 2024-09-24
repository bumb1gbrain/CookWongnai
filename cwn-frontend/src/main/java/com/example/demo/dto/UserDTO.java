package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Restaurant;

public class UserDTO {
    private Long id;
    private String username;
    private List<Restaurant> favoriteRestaurants;

    public UserDTO(){

    }

    public UserDTO(Long id, String username, List<Restaurant> favoriteRestaurants) {
        this.id = id;
        this.username = username;
        this.favoriteRestaurants = favoriteRestaurants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Restaurant> getFavoriteRestaurants() {
        return favoriteRestaurants;
    }

    public void setFavoriteRestaurants(List<Restaurant> favoriteRestaurants) {
        this.favoriteRestaurants = favoriteRestaurants;
    }

    
    
}
