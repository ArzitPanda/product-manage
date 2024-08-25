package com.example.demo.controllers;

import com.example.demo.dtos.address.AddressRequest;
import com.example.demo.dtos.address.AddressResponse;
import com.example.demo.models.Address;
import com.example.demo.services.userDetails.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Address>> getAddressesByUserId(@PathVariable Long userId) {
        List<Address> addresses = addressService.getAddress(userId);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable Long addressId) {
        AddressResponse addressResponse = addressService.getAddressById(addressId);
        return new ResponseEntity<>(addressResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AddressResponse> addAddress(@RequestBody AddressRequest addressRequest) {
        AddressResponse addressResponse = addressService.addAddress(addressRequest);
        return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AddressResponse> updateAddress(@RequestBody AddressResponse addressResponse) {
        AddressResponse updatedAddressResponse = addressService.updateAddress(addressResponse);
        return new ResponseEntity<>(updatedAddressResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
