package com.example.demo.repositories.payments;

import com.example.demo.models.payment.NetBankingPaymentDetails;
import com.example.demo.models.payment.UPIPaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NetBankingRepository extends JpaRepository<NetBankingPaymentDetails,Long> {
    boolean existsByAccountNumberIgnoreCase(String accountNumber);
}