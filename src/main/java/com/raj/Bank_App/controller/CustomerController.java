package com.raj.Bank_App.controller;

import com.raj.Bank_App.Mapper.BankingServiceMapper;
import com.raj.Bank_App.domain.CustomerDetails;
import com.raj.Bank_App.entity.Customer;
import com.raj.Bank_App.service.BankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    BankService bankService;


    @PostMapping("/add")
    public ResponseEntity<Object> addNewCustomer(@RequestBody CustomerDetails customerDetails){

        return bankService.addNewCustomer(customerDetails);
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<Object> findByCustomerNumber(@PathVariable Long customerNumber){

        return bankService.findByCustomerNumber(customerNumber);
    }


    @GetMapping()
    public List<CustomerDetails> findAllCustomers(){

        return bankService.findAllCustomers();
    }

    @DeleteMapping("/delete/{customerNumber}")
    public ResponseEntity<Object> deleteByCustomerNumber(@PathVariable Long customerNumber){

        return bankService.deleteByCustomerNumber(customerNumber);
    }


    @PutMapping("/update/{customerNumber}")
    public ResponseEntity<Object> updateByCustomerNumber(@PathVariable Long customerNumber ,@RequestBody CustomerDetails customerDetails){

        return bankService.updateByCustomerNumber(customerNumber, customerDetails);
    }
}
