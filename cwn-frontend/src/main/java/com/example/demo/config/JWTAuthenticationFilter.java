// package com.example.demo.config;

// import java.io.IOException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.util.StringUtils;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.example.demo.service.UserService;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// public class JWTAuthenticationFilter extends OncePerRequestFilter{

//     @Autowired
//     private JWTGenerator tokenGenerator;

//     @Autowired
//     private UserService userService;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         String token = getJWTFromRequest(request);
//         System.out.println("smth");
//         if(StringUtils.hasText(token) && tokenGenerator.validateToken(token)) {
//             System.out.println("smth");
//             String username = tokenGenerator.getUsernameFromJWT(token);
//             //System.out.println(username + " From Logging");
//             UserDetails userDetails = userService.loadUserByUsername(username);
//             UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
//                 userDetails.getAuthorities());
//             authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//             SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//         }
//         filterChain.doFilter(request, response);
//         //throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
//     }

//     private String getJWTFromRequest(HttpServletRequest request) {
//         String bearerToken = request.getHeader("Authorization");
//         if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//             return bearerToken.substring(7, bearerToken.length());
//         }
//         return null;
//     }
    
// }
