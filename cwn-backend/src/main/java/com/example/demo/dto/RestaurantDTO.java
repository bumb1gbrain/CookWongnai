package com.example.demo.dto;

import java.util.List;

public class RestaurantDTO {

    private Long id;
    private String name;
    private String oc_time;
    private String location;
    private String tel;
    private List<String> type;
    private String price_range;
    private String description;
    private List<String> photos;

    // Constructors
    public RestaurantDTO() {}

    public RestaurantDTO(Long id, String name, String oc_time, String location, String tel, List<String> type,
                         String price_range, String description, List<String> photos) {
        this.id = id;
        this.name = name;
        this.oc_time = oc_time;
        this.location = location;
        this.tel = tel;
        this.type = type;
        this.price_range = price_range;
        this.description = description;
        this.photos = photos;
    }

    // Getters and Setters
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
}
