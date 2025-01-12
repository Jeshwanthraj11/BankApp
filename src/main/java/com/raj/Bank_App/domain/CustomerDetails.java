package com.raj.Bank_App.domain;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDetails {

    private Long customerNumber;

    private String firstName;

    private String lastName;

    private AddressDetails customerAddressDetails;

    private ContactDetails customerContactDetails;
}
