package com.example.demo.services.userDetails.address;

import com.example.demo.dtos.address.AddressRequest;
import com.example.demo.dtos.address.AddressResponse;
import com.example.demo.models.Address;

import java.util.List;

public interface AddressService {

    public List<Address>  getAddress(Long userId);
    public  AddressResponse updateAddress(AddressResponse addressResponse);
    public  AddressResponse addAddress(AddressRequest addressResponse);
    public  AddressResponse deleteAddress(Long addressId);

    public  AddressResponse getAddressById(Long addressId);


}
