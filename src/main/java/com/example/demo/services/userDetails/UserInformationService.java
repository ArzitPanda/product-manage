package com.example.demo.services.userDetails;

import com.example.demo.services.userDetails.address.AddressService;
import com.example.demo.services.userDetails.payment.PaymentService;

public interface UserInformationService {

     PaymentService getPaymentService();
     AddressService getAddressService();

}
