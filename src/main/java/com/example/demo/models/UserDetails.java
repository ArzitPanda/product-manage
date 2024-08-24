package com.example.demo.models;

import java.util.List;

import com.example.demo.models.Payment.PaymentDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {


    @OneToOne
    private User user;
    @OneToMany
    private List<Address> address;

 @OneToMany
    private List<PaymentDetails> paymentDetails;
    
}
