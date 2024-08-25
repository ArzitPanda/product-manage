package com.example.demo.repositories.payments;

import com.example.demo.models.payment.DebitCardPaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardPaymentRepository extends JpaRepository<DebitCardPaymentDetails,Long> {
    boolean existsByCardNumberIgnoreCase(String cardNumber);
}
