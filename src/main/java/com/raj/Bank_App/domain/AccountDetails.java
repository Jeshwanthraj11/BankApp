package com.raj.Bank_App.domain;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDetails {

    private Long accountNumber;

    private String accountStatus;

    private Double accountBalance;

    private String accountType;

    private BankDetails bankDetails;
}
