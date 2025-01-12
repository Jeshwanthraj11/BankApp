package com.raj.Bank_App.domain;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankDetails {

    private String branchName;

    private Long branchCode;

    private Long routingNumber;

    private AddressDetails bankAddressDetails;
}
