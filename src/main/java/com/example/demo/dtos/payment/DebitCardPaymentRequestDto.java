package com.example.demo.dtos.payment;

import com.example.demo.models.payment.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebitCardPaymentRequestDto extends  PaymentDetailsDTO<DebitCardPaymentRequestDto>{

    private String cardNumber;
    private String expiryDate;
    private String cardHolderName;

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.DEBIT_CARD;
    }

    @Override
    public DebitCardPaymentRequestDto PrimaryDetails() {
        return  (DebitCardPaymentRequestDto) this;
    }


}
