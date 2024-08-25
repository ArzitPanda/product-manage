package com.example.demo.services.review;

import com.example.demo.dtos.review.ReviewRequestDTO;
import com.example.demo.dtos.review.ReviewResponseDTO;
import com.example.demo.models.Review;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.ReviewRepository;
import com.example.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ReviewResponseDTO addReview(ReviewRequestDTO reviewRequestDTO) {
        Review review = convertToEntity(reviewRequestDTO);

        review.setUser(userRepository.findById(reviewRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        review.setProduct(productRepository.findById(reviewRequestDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found")));

        Review savedReview = reviewRepository.save(review);

        return convertToDTO(savedReview);
    }

    @Override
    @Transactional
    public ReviewResponseDTO updateReview(Long reviewId, ReviewRequestDTO reviewRequestDTO) {
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        existingReview.setReview(reviewRequestDTO.getReview());
        existingReview.setRating(reviewRequestDTO.getRating());

        Review updatedReview = reviewRepository.save(existingReview);

        return convertToDTO(updatedReview);
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        reviewRepository.delete(existingReview);
    }

    @Override
    public ReviewResponseDTO getReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        return convertToDTO(review);
    }

    @Override
    public List<ReviewResponseDTO> getAllReviewsForProduct(Long productId) {
        List<Review> reviews = reviewRepository.findAll().stream()
                .filter(review -> review.getProduct().getId().equals(productId))
                .collect(Collectors.toList());

        return reviews.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Review convertToEntity(ReviewRequestDTO reviewRequestDTO) {
        return modelMapper.map(reviewRequestDTO, Review.class);
    }

    private ReviewResponseDTO convertToDTO(Review review) {
        ReviewResponseDTO reviewResponseDTO = modelMapper.map(review, ReviewResponseDTO.class);
        reviewResponseDTO.setUserName(review.getUser().getName());  // Assuming User has a name field
        reviewResponseDTO.setProductName(review.getProduct().getName());  // Assuming Product has a name field
        return reviewResponseDTO;
    }
}
