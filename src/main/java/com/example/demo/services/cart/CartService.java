package com.example.demo.services.cart;

import com.example.demo.models.Cart;

import java.util.List;

public interface CartService {

    void addToCart(Long userId, Long productId, int quantity);

    void deleteCartItem(Long cartItemId);

    void updateCartItemQuantity(Long cartItemId, int quantity);

    void removeAllItemsFromCart(Long userId);

    Cart getCartByUserId(Long userId);
}

