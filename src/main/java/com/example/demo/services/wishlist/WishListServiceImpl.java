package com.example.demo.services.wishlist;

import com.example.demo.dtos.product.ProductResponse;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.LoggedUserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class WishListServiceImpl implements   WishListService{
    @Autowired
    UserRepository userRepository;


    @Autowired
    ProductRepository productRepository;

    @Autowired
    LoggedUserContext loggedUserContext;

    @Override
    public List<ProductResponse> getWishList(Long userId) {
        User user  = userRepository.findById(userId).orElseThrow(()->new RuntimeException("not found"));

        List<Product> products = user.getWishList();
        return  products.stream().map((ele)->ProductResponse.fromProduct(ele)).collect(Collectors.toList());

    }

    @Override
    public Product addProductToWishList(Long ProductId) {
        User user =loggedUserContext.getUser();
        if(user!=null)
        {
            Product product = productRepository.findById(ProductId)
                    .orElseThrow(()->new RuntimeException("not found"));

            List<Product> userWishList =user.getWishList();
            if( userWishList==null)
            {
                user.setWishList(new ArrayList<>());
            }
            user.getWishList().add(product);
            userRepository.save(user);
        return  product;

        }
        return null;
    }

    @Override
    public Product RemoveProductToWishList(Long ProductId) {
        User user =loggedUserContext.getUser();
        if(user!=null)
        {
            Product product = productRepository.findById(ProductId)
                    .orElseThrow(()->new RuntimeException("not found"));


            List<Product> userWishList =user.getWishList();
            if(userWishList!=null && !userWishList.isEmpty() && user.getWishList().contains(product))
            {
                user.getWishList().remove(product);
            }

            userRepository.save(user);
            return  product;

        }
        return null;
    }
}
