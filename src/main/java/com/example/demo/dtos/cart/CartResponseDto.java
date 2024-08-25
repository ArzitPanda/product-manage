package com.example.demo.dtos.cart;

import lombok.Data;

import java.util.List;

@Data
public class CartResponseDto {
    private Long id;
    private Double total;
    private Long userId;
    private List<CartItemResponseDto> cartItems;
}
