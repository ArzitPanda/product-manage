package com.example.demo.services.userDetails.payment;

import com.example.demo.dtos.payment.DebitCardPaymentRequestDto;
import com.example.demo.dtos.payment.NetBankingRequestDto;
import com.example.demo.dtos.payment.PaymentDetailsDTO;
import com.example.demo.dtos.payment.UpiPaymentDto;
import com.example.demo.models.User;
import com.example.demo.models.UserDetails;
import com.example.demo.models.payment.DebitCardPaymentDetails;
import com.example.demo.models.payment.NetBankingPaymentDetails;
import com.example.demo.models.payment.PaymentDetails;
import com.example.demo.models.payment.UPIPaymentDetails;
import com.example.demo.repositories.UserDetailsRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.payments.CardPaymentRepository;
import com.example.demo.repositories.payments.NetBankingRepository;
import com.example.demo.repositories.payments.PaymentDetailsRepository;
import com.example.demo.repositories.payments.UPIDetailsRepository;
import com.example.demo.services.LoggedUserContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    private  UserDetailsRepository userDetailsRepository;

    @Autowired
    private LoggedUserContext loggedUserContext;

    @Autowired
    private UPIDetailsRepository upiDetailsRepository;

    @Autowired
    private CardPaymentRepository debitCardRepository;

    @Autowired
    private NetBankingRepository netBankingRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public PaymentDetails  addPaymentDetails(PaymentDetailsDTO paymentDetailsDTO) {

        User user = loggedUserContext.getUser();

      UserDetails userDetails= userDetailsRepository.findByUser_Id(user.getId()).orElse(null);
        System.out.println(user.getId());

      if(userDetails==null)
      {


          userDetails =new UserDetails();
          userDetails.setUser(user);


          userDetails.setPaymentDetails(new ArrayList<>());
          userDetails.setAddress(new ArrayList<>());


          userDetails= userDetailsRepository.save(userDetails);
          userRepository.save(user);
          user.setUserDetails(userDetails);
      }


        switch (paymentDetailsDTO.getPaymentType())
        {
            case UPI -> {
                UpiPaymentDto upi = (UpiPaymentDto) paymentDetailsDTO.PrimaryDetails();

                if(!upiDetailsRepository.existsByUpiId(upi.getUpiId()))
                {
                    UPIPaymentDetails upiPaymentDetails = new UPIPaymentDetails();
                    upiPaymentDetails.setUpiId(upi.getUpiId());
                    upiPaymentDetails.setUserDetails(userDetails);
                    upiPaymentDetails.setDeleted(false);
                  return   upiDetailsRepository.save(upiPaymentDetails);
                }
            }
            case DEBIT_CARD ->
            {
                DebitCardPaymentRequestDto details = (DebitCardPaymentRequestDto) paymentDetailsDTO.PrimaryDetails();
                if(!debitCardRepository.existsByCardNumberIgnoreCase(details.getCardNumber()))
                {
                    DebitCardPaymentDetails debitCardPaymentDetails = mapper.map(details,DebitCardPaymentDetails.class);
                    debitCardPaymentDetails.setUserDetails(userDetails);
                  return   debitCardRepository.save(debitCardPaymentDetails);
                }
            }
            case NET_BANKING ->
            {
                NetBankingRequestDto details = (NetBankingRequestDto) paymentDetailsDTO.PrimaryDetails();
                if(!netBankingRepository.existsByAccountNumberIgnoreCase(details.getAccountNumber()))
                {
                    NetBankingPaymentDetails netBankingPaymentDetails = mapper.map(details,NetBankingPaymentDetails.class);
                    netBankingPaymentDetails.setUserDetails(userDetails);
                    return   netBankingRepository.save(netBankingPaymentDetails);
                }
            }


        }

        return  null;



    }

    @Override
    public PaymentDetails updatePaymentDetails(Long paymentId, PaymentDetailsDTO paymentDetailsDTO) {
        Optional<PaymentDetails> existingPayment = paymentDetailsRepository.findById(paymentId);
        if (existingPayment.isPresent()) {
            PaymentDetails currentPayment = existingPayment.get();
            currentPayment.setDeleted(true);
            currentPayment.setUpdatedAt(LocalDateTime.now());
            paymentDetailsRepository.save(currentPayment);

            return  addPaymentDetails(paymentDetailsDTO);
        } else {
            throw new RuntimeException("Payment details not found");
        }
    }

    @Override
    public void deletePaymentDetails(Long paymentId) {
        Optional<PaymentDetails> existingPayment = paymentDetailsRepository.findById(paymentId);
        if (existingPayment.isPresent()) {
            PaymentDetails currentPayment = existingPayment.get();
            currentPayment.setDeleted(true);
            paymentDetailsRepository.save(currentPayment);
        } else {
            throw new RuntimeException("Payment details not found");
        }
    }

    @Override
    public PaymentDetailsDTO getPaymentDetailsById(Long paymentId) {
        PaymentDetails paymentDetails = paymentDetailsRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment details not found"));
        return mapper.map(paymentDetails, PaymentDetailsDTO.class);
    }

    @Override
    public List<PaymentDetails> getAllPaymentDetails() {

        User user= loggedUserContext.getUser();
        List<PaymentDetails> paymentDetailsList = paymentDetailsRepository.findByUserDetails_User_Id(user.getId());
        return paymentDetailsList;
    }

}
