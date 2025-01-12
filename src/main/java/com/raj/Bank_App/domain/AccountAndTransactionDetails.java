package com.raj.Bank_App.domain;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AccountAndTransactionDetails {

    private Long accountNumber;

    private String transactionType;

    private Double amount;

    private Date transactionTime;
}
