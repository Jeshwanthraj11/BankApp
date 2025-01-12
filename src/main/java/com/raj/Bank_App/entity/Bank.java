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
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Bank_Id")
    private Long id;

    private String branchName;

    private Long branchCode;

    private Long routingNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Address bankAddress;
}
