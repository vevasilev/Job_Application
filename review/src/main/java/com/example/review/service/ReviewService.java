package com.example.review.service;

import com.example.review.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll(Long companyId);

    boolean addReview(Long companyId, Review review);

    Review getReviewById(Long reviewId);

    boolean updateReview(Long reviewId, Review updatedReview);

    boolean deleteReview(Long reviewId);
}
