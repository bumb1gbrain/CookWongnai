package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String location;
    private String cuisine;

    @ManyToMany(mappedBy = "favoriteRestaurants")
    private List<User> usersWhoFavorited;

    public Restaurant() {}

    public Restaurant(Long id, String name, String location, String cuisine, List<User> usersWhoFavorited) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.cuisine = cuisine;
        this.usersWhoFavorited = usersWhoFavorited;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public List<User> getUsersWhoFavorited() {
        return usersWhoFavorited;
    }

    public void setUsersWhoFavorited(List<User> usersWhoFavorited) {
        this.usersWhoFavorited = usersWhoFavorited;
    }

    

    
}
