package com.example.demo.dtos.product;

import com.example.demo.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long stock;
    private String  imageUri;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static ProductResponse fromProduct(Product product)
    {
        ProductResponse p = new ProductResponse();
        p.setName(product.getName());
        p.setDescription(product.getDescription());
        p.setPrice(product.getPrice());
        p.setId(product.getId());
        p.setStock(product.getStock());
        p.setImageUri(product.getImage());
        p.setCreatedAt(product.getCreatedAt());
        p.setUpdatedAt(product.getUpdatedAt());
        return  p;
    }

}