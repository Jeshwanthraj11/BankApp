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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Customer_Id")
    private Long id;

    private Long customerNumber;

    private String firstName;

    private String lastName;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address customerAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Contact customerContact;
}
