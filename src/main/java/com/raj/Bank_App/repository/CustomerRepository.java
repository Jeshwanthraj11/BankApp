package com.raj.Bank_App.repository;

import com.raj.Bank_App.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Long> {

    public Optional<Customer> findByCustomerNumber(Long customerNumber);
}
