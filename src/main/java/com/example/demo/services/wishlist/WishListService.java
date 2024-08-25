package com.example.demo.services.wishlist;

import com.example.demo.dtos.product.ProductResponse;
import com.example.demo.models.Product;

import java.util.List;

public interface WishListService {

    List<ProductResponse> getWishList(Long userId);
    Product addProductToWishList(Long ProductId);
    Product RemoveProductToWishList(Long ProductId);

}
