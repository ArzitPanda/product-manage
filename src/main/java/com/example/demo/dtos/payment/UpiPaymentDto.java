package com.example.demo.dtos.payment;

import com.example.demo.models.payment.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpiPaymentDto extends  PaymentDetailsDTO<UpiPaymentDto>{

    private String upiId;

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.UPI;
    }

    @Override
    public UpiPaymentDto PrimaryDetails() {
        return (UpiPaymentDto)this;
    }
}
