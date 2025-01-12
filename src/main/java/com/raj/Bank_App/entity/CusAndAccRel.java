package com.raj.Bank_App.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CusAndAccRel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Cus_Acc_Rel_Id")
    private Long id;

    private Long accountNumber;

    private Long customerNumber;
}
