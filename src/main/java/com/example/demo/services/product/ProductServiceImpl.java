package com.example.demo.services.product;

import com.example.demo.dtos.product.ProductRequest;
import com.example.demo.dtos.product.ProductResponse;
import com.example.demo.models.Product;

import com.example.demo.models.User;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.LoggedUserContext;
import com.example.demo.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LoggedUserContext loggedUserContext;



    private final String IMAGE_DIRECTORY = "http://localhost:8080/images/";

    @Override
    public Page<ProductResponse> getProducts(String name, String description, Pageable pageable) {
        Page<Product> products;
        if ((name != null && !name.isEmpty()) || (description != null && !description.isEmpty())) {
            products = productRepository.searchProducts(name, description, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }

        return products.map(this::mapToProductResponse);
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest, MultipartFile imageFile) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        // Handle image file
        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImage(FileUtils.storeImage(imageFile));
        }

        User user = loggedUserContext.getUser();
        product.setCreator(user);


        Product savedProduct = productRepository.save(product);
        return mapToProductResponse(savedProduct);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest, MultipartFile imageFile) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Product not found with id " + id)
        );

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setUpdatedAt(LocalDateTime.now());

        // Handle image file update
        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImage(FileUtils.storeImage(imageFile));
        }

        Product updatedProduct = productRepository.save(product);
        return mapToProductResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());
        response.setImageUri(product.getImage());

        return response;
    }
}

