package com.example.demo.dto;


public class RestaurantDTO {

    private Long id;
    private String name;

    public RestaurantDTO(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public RestaurantDTO(){

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

    
    
}
