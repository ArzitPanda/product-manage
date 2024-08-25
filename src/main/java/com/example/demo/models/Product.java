package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Review> reviewList;


    @ManyToOne
    @JsonIgnore
    private User creator;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String image;

    private Long stock;


}
