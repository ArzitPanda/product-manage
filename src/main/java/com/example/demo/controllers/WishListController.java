package com.example.demo.controllers;

import com.example.demo.dtos.product.ProductResponse;
import com.example.demo.models.Product;
import com.example.demo.services.wishlist.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    // Get wishlist for a user
    @GetMapping("{userId}")
    public ResponseEntity<List<ProductResponse>> getWishList(@PathVariable Long userId) {
        List<ProductResponse> wishList = wishListService.getWishList(userId);
        return new ResponseEntity<>(wishList, HttpStatus.OK);
    }

    // Add a product to the wishlist
    @PostMapping("add")
    public ResponseEntity<String> addProductToWishList(@RequestParam Long productId) {
        Product productResponse = wishListService.addProductToWishList(productId);
        return new ResponseEntity<>("sucessfully created ", HttpStatus.CREATED);
    }

    // Remove a product from the wishlist
    @DeleteMapping("remove")
    public ResponseEntity<Void> removeProductFromWishList(@RequestParam Long productId) {
        wishListService.RemoveProductToWishList(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
