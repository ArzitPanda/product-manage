package com.example.demo.controllers;

import com.example.demo.dtos.review.ReviewRequestDTO;
import com.example.demo.dtos.review.ReviewResponseDTO;
import com.example.demo.services.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> addReview(@RequestBody ReviewRequestDTO reviewRequestDTO) {
        ReviewResponseDTO reviewResponseDTO = reviewService.addReview(reviewRequestDTO);
        return new ResponseEntity<>(reviewResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDTO> updateReview(@PathVariable Long reviewId, @RequestBody ReviewRequestDTO reviewRequestDTO) {
        ReviewResponseDTO reviewResponseDTO = reviewService.updateReview(reviewId, reviewRequestDTO);
        return new ResponseEntity<>(reviewResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDTO> getReviewById(@PathVariable Long reviewId) {
        ReviewResponseDTO reviewResponseDTO = reviewService.getReviewById(reviewId);
        return new ResponseEntity<>(reviewResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviewsForProduct(@PathVariable Long productId) {
        List<ReviewResponseDTO> reviewResponseDTOs = reviewService.getAllReviewsForProduct(productId);
        return new ResponseEntity<>(reviewResponseDTOs, HttpStatus.OK);
    }
}
