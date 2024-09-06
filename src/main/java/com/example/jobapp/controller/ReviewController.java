package com.example.jobapp.controller;

import com.example.jobapp.entity.Review;
import com.example.jobapp.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> findAll(@PathVariable Long companyId) {
        return ResponseEntity.ok(reviewService.findAll(companyId));
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
        return reviewService.addReview(companyId, review) ?
                new ResponseEntity<>("Review added successfully", HttpStatus.OK) :
                new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(companyId, reviewId);
        return review != null ?
                new ResponseEntity<>(review, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review updatedReview) {
        return reviewService.updateReview(companyId, reviewId, updatedReview) ?
                new ResponseEntity<>("Review updated successfully", HttpStatus.OK) :
                new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        return reviewService.deleteReview(companyId, reviewId) ?
                new ResponseEntity<>("Review deleted successfully", HttpStatus.OK) :
                new ResponseEntity<>("Review not deleted", HttpStatus.NOT_FOUND);
    }
}
