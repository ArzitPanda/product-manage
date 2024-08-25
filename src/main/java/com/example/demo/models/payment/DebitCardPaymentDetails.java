package com.example.demo.models.payment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DiscriminatorValue("DEBIT_CARD")
public class DebitCardPaymentDetails extends PaymentDetails {



    private String cardNumber;
    private String expiryDate;
    private String cardHolderName;
    
}
