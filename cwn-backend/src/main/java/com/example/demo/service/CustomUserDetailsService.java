// package com.example.demo.service;

// import java.util.Collections;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;


// import com.example.demo.model.User;
// import com.example.demo.repository.UserRepository;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//     @Autowired
//     private UserRepository userRepository;

//     private User user;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = userRepository.findByUsername(username);
//         if (user == null) {
//             throw new UsernameNotFoundException("User not found");
//         }

//         // Ensure authorities are not null
//         List<GrantedAuthority> authorities = getAuthorities(user);
//         return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
//     }

//     private List<GrantedAuthority> getAuthorities(User user) {
//         // Example: Assign a single role "ROLE_USER". You can change this to reflect your application's roles.
//         return Collections.singletonList(new SimpleGrantedAuthority("USER"));
//     }

//     public User getUser() {
//         return user;
//     }

//     public void setUser(User user) {
//         this.user = user;
//     }

    
// }
