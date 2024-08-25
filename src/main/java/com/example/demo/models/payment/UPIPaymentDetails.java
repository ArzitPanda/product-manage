package com.example.demo.models.payment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("UPI")
public class UPIPaymentDetails extends PaymentDetails  {


    private String upiId;
    
}
