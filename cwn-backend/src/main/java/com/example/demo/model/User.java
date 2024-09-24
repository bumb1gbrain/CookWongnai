package com.example.demo.model;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(nullable = false)

    private String password;

    private String name;
    @Column(nullable = false)
    private String email;

    private String role;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_favorite_restaurants",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "restaurant_id"))
    // @JsonIgnoreProperties("favoriteRestaurants")
    @JsonIgnore
    private List<Restaurant> favoriteRestaurants;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Review> reviews;

    public User(){
        
    }

    // @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JoinTable
    // (
    //     name = "users_role",
    //     joinColumns = @JoinColumn(
    //         name = "user_id", referencedColumnName = "id"),
    //     inverseJoinColumns = @JoinColumn(
    //         name = "role_id", referencedColumnName = "id")
    //     )
    // private List<Role> role = new ArrayList<>();

    // public User(String username, String password, String email, List<Role> role) {
    //     this.username = username;
    //     this.password = password;
    //     this.role = role;
    //     this.email = email;
    // }
    
    // public User(String username, String password, List<Role> role) {
    //     this.username = username;
    //     this.password = password;
    //     this.role = role;
        
    // }

    


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Replace with actual logic to retrieve roles for the user.
        // Make sure this never returns null.
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
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


    @Override
public boolean isAccountNonExpired() {
    return true;
}

@Override
public boolean isAccountNonLocked() {
    return true;
}

@Override
public boolean isCredentialsNonExpired() {
    return true;
}

@Override
public boolean isEnabled() {
    return true;
}

    
    







}