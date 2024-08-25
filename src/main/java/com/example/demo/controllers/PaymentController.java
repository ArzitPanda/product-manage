package com.example.demo.controllers;



import com.example.demo.dtos.payment.DebitCardPaymentRequestDto;
import com.example.demo.dtos.payment.UpiPaymentDto;
import com.example.demo.models.payment.PaymentDetails;
import com.example.demo.services.userDetails.payment.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {


    @Autowired
    private  PaymentService paymentService;
    @Autowired
    private  ModelMapper modelMapper;


    @PostMapping("debitCard")
    public ResponseEntity<DebitCardPaymentRequestDto> addPaymentDetails(@RequestBody DebitCardPaymentRequestDto paymentDetailsDTO) {
        PaymentDetails createdPaymentDetails = paymentService.addPaymentDetails(paymentDetailsDTO);


        DebitCardPaymentRequestDto createdPaymentDetailsDTO =  modelMapper.map(createdPaymentDetails,DebitCardPaymentRequestDto.class);
        return new ResponseEntity<>(createdPaymentDetailsDTO, HttpStatus.CREATED);
    }

    @PostMapping("upi")
    public ResponseEntity<UpiPaymentDto> addPaymentDetails(@RequestBody UpiPaymentDto paymentDetailsDTO) {
        PaymentDetails createdPaymentDetails = paymentService.addPaymentDetails(paymentDetailsDTO);


        UpiPaymentDto createdPaymentDetailsDTO =  modelMapper.map(createdPaymentDetails,UpiPaymentDto.class);
        return new ResponseEntity<>(createdPaymentDetailsDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity getAllPayments()
    {
      return   ResponseEntity.ok(paymentService.getAllPaymentDetails());
    }




    // Helper method to convert PaymentDetails to PaymentDetailsDTO

}

