package com.example.demo.services.userDetails.address;


import com.example.demo.dtos.address.AddressRequest;
import com.example.demo.dtos.address.AddressResponse;
import com.example.demo.models.Address;
import com.example.demo.models.User;
import com.example.demo.models.UserDetails;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.UserDetailsRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.LoggedUserContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceimpl implements AddressService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    LoggedUserContext loggedUserContext;


    @Override
    public List<Address> getAddress(Long userId) {

        Optional<UserDetails> userDetails = userDetailsRepository.findByUser_Id(userId);

        if (userDetails.isEmpty()) {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("not found"));
            UserDetails userDetails1 = new UserDetails();
            userDetails1.setUser(user);

            userDetailsRepository.save(userDetails1);
            return new ArrayList<>();
        }
        List<Address> addresses = userDetails.get().getAddress() != null ? userDetails.get().getAddress() : new ArrayList<>();


        return addresses;

    }

    @Override
    public AddressResponse updateAddress(AddressResponse addressResponse) {
        return null;
    }

    @Override
    public AddressResponse addAddress(AddressRequest addressRequest) {

        User user = loggedUserContext.getUser();
        UserDetails userDetails = user.getUserDetails();


        Address newAddress = mapper.map(addressRequest, Address.class);
        if (userDetails.getAddress() == null) {
            userDetails.setAddress(new ArrayList<>());

        }
        userDetails.getAddress().add(newAddress);
        UserDetails SavedUser = userDetailsRepository.save(userDetails);

        return mapper.map(addressRequest, AddressResponse.class);
    }

    @Override
    public AddressResponse deleteAddress(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        addressRepository.delete(address);
        return mapper.map(address, AddressResponse.class);
    }

    @Override
    public AddressResponse getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        return mapper.map(address, AddressResponse.class);
    }
}
