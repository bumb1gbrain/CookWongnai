package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String oc_time;
    private String location;
    private String tel;
    private List<String> type;
    private String price_range;
    private String description;
    @ElementCollection
    private List<String> photos = new ArrayList<>();

    @ManyToMany(mappedBy = "favoriteRestaurants")
    //@JsonIgnoreProperties("favoriteRestaurants")
    @JsonIgnore
    private List<User> usersWhoFavorited = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    public Restaurant(Long id, String name, String oc_time, String location, String tel, List<String> type,
            String price_range, String description, List<String> photos, List<User> usersWhoFavorited,
            List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.oc_time = oc_time;
        this.location = location;
        this.tel = tel;
        this.type = type;
        this.price_range = price_range;
        this.description = description;
        this.photos = photos;
        this.usersWhoFavorited = usersWhoFavorited;
        this.reviews = reviews;
    }

    public Restaurant() {
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

    public String getOc_time() {
        return oc_time;
    }

    public void setOc_time(String oc_time) {
        this.oc_time = oc_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getPrice_range() {
        return price_range;
    }

    public void setPrice_range(String price_range) {
        this.price_range = price_range;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<User> getUsersWhoFavorited() {
        return usersWhoFavorited;
    }

    public void setUsersWhoFavorited(List<User> usersWhoFavorited) {
        this.usersWhoFavorited = usersWhoFavorited;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    
    

    
}
