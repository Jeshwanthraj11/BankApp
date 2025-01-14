package com.raj.Bank_App.controller;

import com.raj.Bank_App.Mapper.BankingServiceMapper;
import com.raj.Bank_App.domain.CustomerDetails;
import com.raj.Bank_App.entity.Customer;
import com.raj.Bank_App.service.BankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customer Controller API endpoints")
public class CustomerController {

    @Autowired
    BankService bankService;

    @PostMapping("/add")
    @Operation(summary = "Add new customer ",
    description = "Adds new customer")
    public ResponseEntity<Object> addNewCustomer(@RequestBody CustomerDetails customerDetails){

        return bankService.addNewCustomer(customerDetails);
    }

    @GetMapping("/{customerNumber}")
    @Operation(summary = "Find customer by customer number ",
    description = "Finds customer with the given customer number")
    public ResponseEntity<Object> findByCustomerNumber(@PathVariable Long customerNumber){

        return bankService.findByCustomerNumber(customerNumber);
    }


    @GetMapping()
    @Operation(summary = "Find all customer ",
    description = "Returns the total list of customers")
    public List<CustomerDetails> findAllCustomers(){

        return bankService.findAllCustomers();
    }

    @DeleteMapping("/delete/{customerNumber}")
    @Operation(summary = "Delete by customer number ",
    description = "Deletes a customer by customer number")
    public ResponseEntity<Object> deleteByCustomerNumber(@PathVariable Long customerNumber){

        return bankService.deleteByCustomerNumber(customerNumber);
    }


    @PutMapping("/update/{customerNumber}")
    @Operation(summary = "Update by customer number",
    description = "Updates a customer details by customer number")
    public ResponseEntity<Object> updateByCustomerNumber(@PathVariable Long customerNumber ,@RequestBody CustomerDetails customerDetails){

        return bankService.updateByCustomerNumber(customerNumber, customerDetails);
    }
}
