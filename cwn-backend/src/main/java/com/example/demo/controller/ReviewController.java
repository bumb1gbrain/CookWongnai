// package com.example.demo.controller;

// import java.util.List;
// import java.util.Optional;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.model.Review;
// import com.example.demo.service.ReviewService;
// @RestController
// @RequestMapping("/api/reviews")
// public class ReviewController {

//     @Autowired
//     private ReviewService reviewService;

//     @GetMapping
//     public List<Review> getAllReviews(){
//         return reviewService.getAllReviews();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Review> getReviewById(@PathVariable Long id){
//         Optional<Review> review = reviewService.getReviewById(id);
//         return review.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//     }

//     @PostMapping
//     public Review creatReview(@RequestBody Review review){
//         return reviewService.createReview(review);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
//         try {
//             Review updatedReview = reviewService.updateReview(id, reviewDetails);
//             return ResponseEntity.ok(updatedReview);
//         } catch (RuntimeException e) {
//             return ResponseEntity.notFound().build();
//         }
//     }
    
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id){
//         reviewService.deleteReview(id);
//         return ResponseEntity.noContent().build();
//     }

//     //•	GET /api/reviews/restaurant/{restaurantId} - Get reviews by restaurant.
//     @GetMapping("/restaurant/{restaurantId}")
//     public List<Review> getReviewsByRestaurant(@PathVariable Long restaurantId) {
//         return reviewService.getReviewsByRestaurant(restaurantId);
//     }


// 	//•	GET /api/reviews/user/{userId} - Get reviews by user.
//     @GetMapping("/user/{userId}")
//     public List<Review> getReviewsByUser(@PathVariable Long userId){
//         return reviewService.getReviewsByUser(userId);
//     }
        
// }//ผมหาterminal ไม่เจอ หมายถึงอะไรครับ Terminal 21 อนู่โคราชเครั คนที่โดนกราดยิงต่อไปอาจเป็นคุณ 

