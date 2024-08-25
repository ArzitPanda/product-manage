package com.example.demo.dtos.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.demo.models.Address}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest implements Serializable {

    String street;
    String city;
    String state;
    String zipCode;
    String country;
    String phoneNumber;
}
