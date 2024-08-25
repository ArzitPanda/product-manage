package com.example.demo.dtos.review;

import lombok.Data;

@Data
public class ReviewResponseDTO {
    private Long id;
    private String review;
    private String userName; // Assuming User has a name field
    private String productName; // Assuming Product has a name field
    private Double rating;
}