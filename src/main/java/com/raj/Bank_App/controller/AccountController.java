package com.raj.Bank_App.controller;

import com.raj.Bank_App.domain.AccountDetails;
import com.raj.Bank_App.domain.TransactionDetails;
import com.raj.Bank_App.entity.Account;
import com.raj.Bank_App.entity.AccountAndTransaction;
import com.raj.Bank_App.service.BankService;
import com.raj.Bank_App.service.impl.BankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private BankService bankService;

    @PostMapping("/add/{customerNumber}")
    public ResponseEntity<Object> addAccount(@RequestBody AccountDetails accountDetails, @PathVariable Long customerNumber){

        return bankService.saveAccount(accountDetails,customerNumber);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Object> getByAccountNumber(@PathVariable Long accountNumber){

        return bankService.findByAccountNumber(accountNumber);
    }


    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<Object> deleteAccountByAccountNumber(@PathVariable  Long accountNumber){

        return bankService.deleteByAccountNumber(accountNumber);
    }

    @PostMapping("/transaction")
    public ResponseEntity<Object> transaction(@RequestBody TransactionDetails transactionDetails){

        return bankService.transactionHelper(transactionDetails);
    }


    @GetMapping("/transaction/{accountNumber}")
    public ResponseEntity<Object> transactionByAccountNumber(@PathVariable Long accountNumber){

        return bankService.transactionByAccountNumber(accountNumber);

    }
}
