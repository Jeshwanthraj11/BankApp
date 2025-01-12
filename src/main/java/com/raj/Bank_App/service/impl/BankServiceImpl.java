package com.raj.Bank_App.service.impl;

import com.raj.Bank_App.Mapper.BankingServiceMapper;
import com.raj.Bank_App.domain.AccountAndTransactionDetails;
import com.raj.Bank_App.domain.AccountDetails;
import com.raj.Bank_App.domain.CustomerDetails;
import com.raj.Bank_App.domain.TransactionDetails;
import com.raj.Bank_App.entity.*;
import com.raj.Bank_App.repository.*;
import com.raj.Bank_App.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CusAccRelRepository cusAccRelRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountAndTransactionRepository accountAndTransactionRepository;



    @Autowired
    private BankingServiceMapper bankingServiceMapper;

    @Override
    public ResponseEntity<Object> addNewCustomer(CustomerDetails customerDetails) {

        Customer newCustomer = bankingServiceMapper.customerMapper(customerDetails);

        customerRepository.save(newCustomer);

        return ResponseEntity.status(HttpStatus.CREATED).body("Customer is added");

    }

    @Override
    public ResponseEntity<Object> findByCustomerNumber(Long customerNumber) {
        Optional<Customer> customerOptional= customerRepository.findByCustomerNumber(customerNumber) ;

        if(customerOptional.isPresent()){

            Customer customer = customerOptional.get();

            CustomerDetails customerDetails= bankingServiceMapper.customerDetailsMapper(customer);

            return ResponseEntity.status(HttpStatus.FOUND).body( customerDetails);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not found");
    }

    @Override
    public List<CustomerDetails> findAllCustomers() {
        List<CustomerDetails> customerDetailsList = new ArrayList<>();

        Iterable<Customer> customerList= customerRepository.findAll();

        for(Customer customer : customerList){

            customerDetailsList.add(bankingServiceMapper.customerDetailsMapper(customer));
        }

        return customerDetailsList;
    }

    @Override
    public ResponseEntity<Object> deleteByCustomerNumber(Long customerNumber) {

        Optional<Customer> customerOptional = customerRepository.findByCustomerNumber(customerNumber);

        if(customerOptional.isPresent()){

            Customer customer = customerOptional.get();

            customerRepository.delete(customer);

            return ResponseEntity.status(HttpStatus.OK).body("Customer is deleted");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer is not found");

    }

    @Override
    public ResponseEntity<Object> updateByCustomerNumber(Long customerNumber, CustomerDetails customerDetails) {

        Optional<Customer> customerOptional = customerRepository.findByCustomerNumber(customerNumber);

        if(customerOptional.isPresent()){

            Customer customer = customerOptional.get();

            Customer updatedCustomer = bankingServiceMapper.customerMapper(customerDetails);

            if(Optional.ofNullable(updatedCustomer.getCustomerContact()).isPresent()) {

                if (Optional.ofNullable(customer.getCustomerContact()).isPresent()) {

                    customer.getCustomerContact().setPhone(updatedCustomer.getCustomerContact().getPhone());

                    customer.getCustomerContact().setEmail(updatedCustomer.getCustomerContact().getEmail());
                } else {
                    customer.setCustomerContact(updatedCustomer.getCustomerContact());
                }
            }

            if(Optional.ofNullable(updatedCustomer.getCustomerAddress()).isPresent()) {

                if (Optional.ofNullable(customer.getCustomerAddress()).isPresent()) {

                    customer.getCustomerAddress().setAddressLine1(updatedCustomer.getCustomerAddress().getAddressLine1());

                    customer.getCustomerAddress().setAddressLine2(updatedCustomer.getCustomerAddress().getAddressLine2());

                    customer.getCustomerAddress().setCity(updatedCustomer.getCustomerAddress().getCity());

                    customer.getCustomerAddress().setState(updatedCustomer.getCustomerAddress().getState());

                    customer.getCustomerAddress().setZip(updatedCustomer.getCustomerAddress().getZip());
                } else {

                    customer.setCustomerAddress(updatedCustomer.getCustomerAddress());
                }
            }


            customer.setFirstName(updatedCustomer.getFirstName());

            customer.setLastName(updatedCustomer.getLastName());


            customerRepository.save(customer);

            return ResponseEntity.status(HttpStatus.OK).body("Customer details is updated");

        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer does not exists");
        }
    }

    @Override
    public ResponseEntity<Object> saveAccount(AccountDetails accountDetails, Long customerNumber) {

        Optional<Customer> customerOptional = customerRepository.findByCustomerNumber(customerNumber);

        if(customerOptional.isPresent()){

            Account account = bankingServiceMapper.accountMapper(accountDetails);

            accountRepository.save(account);

            CusAndAccRel cusAndAccRel = CusAndAccRel.builder()
                    .accountNumber(account.getAccountNumber())
                    .customerNumber(customerNumber)
                    .build();

            cusAccRelRepository.save(cusAndAccRel);


            return ResponseEntity.status(HttpStatus.OK).body("Account is created");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No customer is found");
    }

    @Override
    public ResponseEntity<Object> findByAccountNumber(Long accountNumber) {

        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);

        if(accountOptional.isPresent()){

            Account account = accountOptional.get();

            AccountDetails accountDetails = bankingServiceMapper.accountDetailsMapper(account);

            return ResponseEntity.status(HttpStatus.OK).body(accountDetails);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account not found");
    }

    @Override
    public ResponseEntity<Object> deleteByAccountNumber(Long accountNumber) {

        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);

        if(accountOptional.isPresent()){

            Account account = accountOptional.get();

            accountRepository.delete(account);

            return ResponseEntity.status(HttpStatus.OK).body("Account is deleted");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account does not exist");
    }

    @Override
    public ResponseEntity<Object> transactionHelper(TransactionDetails transactionDetails) {

        Optional<Account> fromAccountOptional= accountRepository.findByAccountNumber(transactionDetails.getFromAccountNumber());

        Optional<Account> toAccountOptional = accountRepository.findByAccountNumber(transactionDetails.getToAccountNumber());

        AccountAndTransaction accountAndTransaction = new AccountAndTransaction();

        if(fromAccountOptional.isPresent() && toAccountOptional.isPresent()){

            Account senderAccount = fromAccountOptional.get();

            Account receiverAccount = toAccountOptional.get();

            if(senderAccount.getAccountBalance() > transactionDetails.getTransferAmount()){

                Transaction transaction =  bankingServiceMapper.transactionMapper(transactionDetails);

                transaction.setTransferTime(new Date());

                transactionRepository.save(transaction);

                accountAndTransaction = bankingServiceMapper.accountAndTransactionDatabase(transaction,transaction.getFromAccountNumber(),"Sender");

                accountAndTransactionRepository.save(accountAndTransaction);

                accountAndTransaction = bankingServiceMapper.accountAndTransactionDatabase(transaction,transaction.getToAccountNumber(),"Receiver");

                accountAndTransactionRepository.save(accountAndTransaction);

                senderAccount.setAccountBalance(senderAccount.getAccountBalance() - transactionDetails.getTransferAmount());

                accountRepository.save(senderAccount);

                receiverAccount.setAccountBalance(receiverAccount.getAccountBalance() + transactionDetails.getTransferAmount());

                accountRepository.save(receiverAccount);



                return ResponseEntity.status(HttpStatus.OK).body("Transaction is successfull");

            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient Balance");
            }

        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid account number");
        }
    }

    @Override
    public ResponseEntity<Object> transactionByAccountNumber(Long accountNumber) {

        List<AccountAndTransactionDetails> accountAndTransactionDetailsList = new ArrayList<>();

        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);

        if(accountOptional.isPresent()){

            Optional<List<AccountAndTransaction>> optionalAccountAndTransactionList = accountAndTransactionRepository.findByAccountNumber(accountNumber);

            if(optionalAccountAndTransactionList.isPresent()){

                List<AccountAndTransaction> accountAndTransactionList = optionalAccountAndTransactionList.get();

                for(AccountAndTransaction accountAndTransaction : accountAndTransactionList){

                    accountAndTransactionDetailsList.add(bankingServiceMapper.accountAndTransactionDetailsMapper(accountAndTransaction));
                }

                return ResponseEntity.status(HttpStatus.OK).body(accountAndTransactionDetailsList);

            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No transactions found for given account number");

        }

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid account Number");

    }

}
