package com.raj.Bank_App.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDetails {

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private String zip;
}
