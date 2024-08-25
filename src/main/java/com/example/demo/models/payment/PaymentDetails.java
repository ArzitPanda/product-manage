package com.example.demo.models.payment;

import java.time.LocalDateTime;

import com.example.demo.models.User;

import com.example.demo.models.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data   
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "employee_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "payment_details")
public abstract  class PaymentDetails {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "payment_id")  // Explicitly specifying the column name
 private Long paymentId;

    @ManyToOne
    @JsonIgnore
    private UserDetails userDetails;


    private  boolean isDeleted;

    private LocalDateTime createdAt=LocalDateTime.now();
    private LocalDateTime updatedAt=LocalDateTime.now();



}
