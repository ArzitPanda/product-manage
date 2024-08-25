package com.example.demo.services.product;

import com.example.demo.dtos.product.ProductRequest;
import com.example.demo.dtos.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    Page<ProductResponse> getProducts(String name, String description, Pageable pageable);
    ProductResponse createProduct(ProductRequest productRequest, MultipartFile imageFile);
    ProductResponse updateProduct(Long id, ProductRequest productRequest, MultipartFile imageFile);
    void deleteProduct(Long id);
}
