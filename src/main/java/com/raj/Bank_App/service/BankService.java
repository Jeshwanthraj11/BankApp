package com.raj.Bank_App.service;

import com.raj.Bank_App.domain.AccountDetails;
import com.raj.Bank_App.domain.CustomerDetails;
import com.raj.Bank_App.domain.TransactionDetails;
import com.raj.Bank_App.entity.AccountAndTransaction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankService {

    public ResponseEntity<Object> addNewCustomer(CustomerDetails customerDetails);

    ResponseEntity<Object> findByCustomerNumber(Long customerNumber);

    List<CustomerDetails> findAllCustomers();

    ResponseEntity<Object> deleteByCustomerNumber(Long customerNumber);

    ResponseEntity<Object> updateByCustomerNumber(Long customerNumber, CustomerDetails customerDetails);

    public ResponseEntity<Object> saveAccount(AccountDetails accountDetails, Long customerNumber);

    public ResponseEntity<Object> findByAccountNumber(Long accountNumber);

    ResponseEntity<Object> deleteByAccountNumber(Long accountNumber);

    ResponseEntity<Object> transactionHelper(TransactionDetails transactionDetails);


    ResponseEntity<Object> transactionByAccountNumber(Long accountNumber);
}
