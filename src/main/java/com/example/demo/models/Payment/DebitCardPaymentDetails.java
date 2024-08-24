package com.example.demo.models.Payment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "debit_card_payment_details")
public class DebitCardPaymentDetails extends PaymentDetails {



    private String cardNumber;
    private String expiryDate;
    private String cardHolderName;
    
}
