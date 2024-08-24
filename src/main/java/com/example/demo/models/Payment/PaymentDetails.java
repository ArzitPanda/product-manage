package com.example.demo.models.Payment;

import java.time.LocalDateTime;

import com.example.demo.models.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data   
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "payment_details")
public class PaymentDetails {

    @Id
    @GeneratedValue
    protected Long payment_id;

    @ManyToOne
    private User user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
