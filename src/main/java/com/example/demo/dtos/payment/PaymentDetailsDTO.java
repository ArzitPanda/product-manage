package com.example.demo.dtos.payment;

import com.example.demo.models.payment.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PaymentDetailsDTO<T extends  PaymentDetailsDTO> {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public abstract PaymentType getPaymentType();

    public abstract  T PrimaryDetails();



}


