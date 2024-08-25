package com.example.demo.dtos.payment;

import com.example.demo.models.payment.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetBankingRequestDto extends  PaymentDetailsDTO<NetBankingRequestDto> {

    private String bankName;
    private String accountNumber;

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.NET_BANKING;
    }

    @Override
    public NetBankingRequestDto PrimaryDetails() {
        return (NetBankingRequestDto) this;
    }
}
