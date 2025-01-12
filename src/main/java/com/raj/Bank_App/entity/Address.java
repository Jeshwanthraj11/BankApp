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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Address_Id")
    private Long id;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private String zip;
}
