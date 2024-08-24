package com.example.demo.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Double price;

    @ManyToOne
    private User creator;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private byte[] image;

    private Long stock;


}
