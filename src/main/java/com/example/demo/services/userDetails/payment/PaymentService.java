package com.example.demo.services.userDetails.payment;

import com.example.demo.dtos.payment.PaymentDetailsDTO;
import com.example.demo.models.payment.PaymentDetails;

import java.util.List;

public interface PaymentService {
    PaymentDetails addPaymentDetails(PaymentDetailsDTO paymentDetailsDTO);
    PaymentDetails updatePaymentDetails(Long paymentId, PaymentDetailsDTO paymentDetailsDTO);
    void deletePaymentDetails(Long paymentId);
    PaymentDetailsDTO getPaymentDetailsById(Long paymentId);
    List<PaymentDetails> getAllPaymentDetails();
}
