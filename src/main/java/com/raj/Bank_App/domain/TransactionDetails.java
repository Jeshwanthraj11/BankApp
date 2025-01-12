package com.raj.Bank_App.domain;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TransactionDetails {

    private Long fromAccountNumber;

    private Long toAccountNumber;

    private Double transferAmount;

    private Date transferTime;
}
