package com.example.demo.models;

import com.example.demo.models.payment.PaymentType;
import jakarta.persistence.Embeddable;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.demo.models.payment.PaymentDetails}
 */
@Embeddable
public class OrderPaymentDetails  implements Serializable {
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    PaymentType paymentType;
    String accountNumber;
    String bankName;
    String CardHolderName;
    String CardNumber;
    String upiId;
}