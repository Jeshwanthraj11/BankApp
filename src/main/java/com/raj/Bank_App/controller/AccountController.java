package com.raj.Bank_App.controller;

import com.raj.Bank_App.domain.AccountDetails;
import com.raj.Bank_App.domain.TransactionDetails;
import com.raj.Bank_App.entity.Account;
import com.raj.Bank_App.entity.AccountAndTransaction;
import com.raj.Bank_App.service.BankService;
import com.raj.Bank_App.service.impl.BankServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Account Controller API endpoints")
public class AccountController {

    @Autowired
    private BankService bankService;


    @PostMapping("/add/{customerNumber}")
    @Operation(summary ="Add Account " ,
    description = "Adds account details with an already existing customer number")
    @ApiResponses(value = {@ApiResponse(responseCode = "200" , description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    public ResponseEntity<Object> addAccount(@RequestBody AccountDetails accountDetails, @PathVariable Long customerNumber){

        return bankService.saveAccount(accountDetails,customerNumber);
    }

    @GetMapping("/{accountNumber}")
    @Operation(summary = "Get by account number ",
    description = "Find account by given account number")
    @ApiResponses(value = {@ApiResponse(responseCode = "200" , description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    public ResponseEntity<Object> getByAccountNumber(@PathVariable Long accountNumber){

        return bankService.findByAccountNumber(accountNumber);
    }


    @DeleteMapping("/delete/{accountNumber}")
    @Operation(summary = "Delete account by account number",
    description = "Deletes an account by given account number")
    @ApiResponses(value = {@ApiResponse(responseCode = "200" , description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    public ResponseEntity<Object> deleteAccountByAccountNumber(@PathVariable  Long accountNumber){

        return bankService.deleteByAccountNumber(accountNumber);
    }

    @PostMapping("/transaction")
    @Operation(summary = "Transaction ",
    description = "Creates transaction between two account number")
    @ApiResponses(value = {@ApiResponse(responseCode = "200" , description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    public ResponseEntity<Object> transaction(@RequestBody TransactionDetails transactionDetails){

        return bankService.transactionHelper(transactionDetails);
    }


    @GetMapping("/transaction/{accountNumber}")
    @Operation(summary = "Transaction by account number ",
    description = "Shows transaction done by the give account number")
    @ApiResponses(value = {@ApiResponse(responseCode = "200" , description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    public ResponseEntity<Object> transactionByAccountNumber(@PathVariable Long accountNumber){

        return bankService.transactionByAccountNumber(accountNumber);

    }
}
