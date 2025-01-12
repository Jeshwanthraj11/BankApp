package com.raj.Bank_App.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Transaction_Id")
    private Long id;

    private Long fromAccountNumber;

    private Long toAccountNumber;

    private Double transferAmount;

    private Date transferTime;


}
