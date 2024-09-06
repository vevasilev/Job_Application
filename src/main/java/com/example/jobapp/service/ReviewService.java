package com.example.jobapp.service;

import com.example.jobapp.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll(Long companyId);

    boolean addReview(Long companyId, Review review);

    Review getReviewById(Long companyId, Long reviewId);

    boolean updateReview(Long companyId, Long reviewId, Review updatedReview);

    boolean deleteReview(Long companyId, Long reviewId);
}
