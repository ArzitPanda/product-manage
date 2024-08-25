package com.example.demo.dtos.cart;

import lombok.Data;

@Data
public class CartItemResponseDto {
    private Long id;
    private Long productId;
    private int quantity;
}
