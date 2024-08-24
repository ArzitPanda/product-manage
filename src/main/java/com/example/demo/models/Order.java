package com.example.demo.models;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.UUID;

import com.example.demo.models.Payment.PaymentDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {  
 @Id
 @GeneratedValue(strategy = GenerationType.UUID)         
    private UUID Id;


@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)    
private Cart cart;


@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)    
private User userOrder;

@Embedded
private Address userDetails;

@Embedded
private PaymentDetails paymentDetails;


private LocalDateTime createdAt = LocalDateTime.now();
private LocalDateTime updatedAt= LocalDateTime.now();

@Enumerated(EnumType.STRING)
private OrderStatus status;








}
