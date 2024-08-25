package com.example.demo.dtos.order;

import lombok.Data;

@Data
public class OrderItemResponseDTO {
    private Long productId;
    private String productName;
    private Long quantity;
}
