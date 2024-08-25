package com.example.demo.models.payment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("NET_BANKING")
public class NetBankingPaymentDetails  extends PaymentDetails{


    private String bankName;
    private String accountNumber;
    
}
