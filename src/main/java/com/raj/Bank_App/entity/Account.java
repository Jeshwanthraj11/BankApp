package com.raj.Bank_App.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Account_Id")
    private Long id;

    private Long accountNumber;

    private String accountStatus;

    private Double accountBalance;

    private String accountType;

    @OneToOne(cascade = CascadeType.ALL)
    private Bank bank;
}
