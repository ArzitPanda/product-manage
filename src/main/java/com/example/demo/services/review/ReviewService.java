package com.example.demo.services.review;

import com.example.demo.dtos.review.ReviewRequestDTO;
import com.example.demo.dtos.review.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {
    ReviewResponseDTO addReview(ReviewRequestDTO reviewRequestDTO);
    ReviewResponseDTO updateReview(Long reviewId, ReviewRequestDTO reviewRequestDTO);
    void deleteReview(Long reviewId);
    ReviewResponseDTO getReviewById(Long reviewId);
    List<ReviewResponseDTO> getAllReviewsForProduct(Long productId);
}