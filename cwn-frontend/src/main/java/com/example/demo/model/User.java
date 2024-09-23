package com.example.demo.model;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private String name;
    private String email;

    private String role;


    // @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JoinTable(
    //     name = "users_role",
    //     joinColumns = @JoinColumn(
    //         name = "user_id", referencedColumnName = "id"),
    //     inverseJoinColumns = @JoinColumn(
    //         name = "role_id", referencedColumnName = "id")
    //     )
    // private List<Role> roles;

    
    @ManyToMany
    @JoinTable(
        name = "user_favorite_restaurants",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "restaurant_id"))
    private List<Restaurant> favoriteRestaurants;

    @OneToMany(mappedBy = "user")
    
    @JsonIgnore
    private List<Review> reviews;


    // public User(Long id, String username, String password, String name, String email, List<Role> roles,
    //         List<Restaurant> favoriteRestaurants, List<Review> reviews) {
    //     this.id = id;
    //     this.username = username;
    //     this.password = password;
    //     this.name = name;
    //     this.email = email;
    //     this.roles = roles;
    //     this.favoriteRestaurants = favoriteRestaurants;
    //     this.reviews = reviews;
    // }

    public User(){
        
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Restaurant> getFavoriteRestaurants() {
        return favoriteRestaurants;
    }

    public void setFavoriteRestaurants(List<Restaurant> favoriteRestaurants) {
        this.favoriteRestaurants = favoriteRestaurants;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

     public void addFavoriteRestaurant(Restaurant restaurant) {
        this.favoriteRestaurants.add(restaurant);
    }

    public void removeFavoriteRestaurant(Restaurant restaurant) {
        this.favoriteRestaurants.remove(restaurant);
    }

    // public List<Role> getRoles() {
    //     return roles;
    // }

    // public void setRoles(List<Role> roles) {
    //     this.roles = roles;
    // }
    
    







}