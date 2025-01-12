package com.raj.Bank_App.Mapper;

import com.raj.Bank_App.domain.*;
import com.raj.Bank_App.entity.*;
import org.springframework.stereotype.Component;

@Component
public class BankingServiceMapper {

    public Account accountMapper(AccountDetails accountDetails) {
        return Account.builder()
                .accountNumber(accountDetails.getAccountNumber())
                .accountStatus(accountDetails.getAccountStatus())
                .accountBalance(accountDetails.getAccountBalance())
                .accountType(accountDetails.getAccountType())
                .bank(bankMapper(accountDetails.getBankDetails()))
                .build();
    }

    public AccountDetails accountDetailsMapper(Account account) {
        return AccountDetails.builder()
                .accountNumber(account.getAccountNumber())
                .accountStatus(account.getAccountStatus())
                .accountBalance(account.getAccountBalance())
                .accountType(account.getAccountType())
                .bankDetails(bankDetailsMapper(account.getBank()))
                .build();
    }

    public Customer customerMapper(CustomerDetails customerDetails) {
        return Customer.builder()
                .customerNumber(customerDetails.getCustomerNumber())
                .firstName(customerDetails.getFirstName())
                .lastName((customerDetails.getLastName()))
                .customerAddress(addressMapper(customerDetails.getCustomerAddressDetails()))
                .customerContact(contactMapper(customerDetails.getCustomerContactDetails()))
                .build();
    }

    public CustomerDetails customerDetailsMapper(Customer customer) {
        return CustomerDetails.builder()
                .customerNumber(customer.getCustomerNumber())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .customerAddressDetails(addressDetailsMapper(customer.getCustomerAddress()))
                .customerContactDetails(contactDetailsMapper(customer.getCustomerContact()))
                .build();
    }

    public Bank bankMapper(BankDetails bankDetails){
        return Bank.builder()
                .branchName(bankDetails.getBranchName())
                .branchCode(bankDetails.getBranchCode())
                .routingNumber(bankDetails.getRoutingNumber())
                .bankAddress(addressMapper(bankDetails.getBankAddressDetails()))
                .build();
    }

    public BankDetails bankDetailsMapper(Bank bank){
        return BankDetails.builder()
                .branchName(bank.getBranchName())
                .branchCode(bank.getBranchCode())
                .routingNumber(bank.getRoutingNumber())
                .bankAddressDetails(addressDetailsMapper(bank.getBankAddress()))
                .build();
    }

    public Address addressMapper(AddressDetails addressDetails){
        return Address.builder()
                .addressLine1(addressDetails.getAddressLine1())
                .addressLine2(addressDetails.getAddressLine2())
                .city(addressDetails.getCity())
                .state(addressDetails.getState())
                .country(addressDetails.getCountry())
                .zip(addressDetails.getZip())
                .build();
    }

    public AddressDetails addressDetailsMapper(Address address){
        return AddressDetails.builder()
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .zip(address.getZip())
                .build();
    }

    public Contact contactMapper(ContactDetails contactDetails){
        return Contact.builder()
                .phone(contactDetails.getPhone())
                .email(contactDetails.getEmail())
                .build();
    }

    public ContactDetails contactDetailsMapper(Contact contact){
        return ContactDetails.builder()
                .phone(contact.getPhone())
                .email(contact.getEmail())
                .build();
    }

    public Transaction transactionMapper(TransactionDetails transactionDetails){

        return Transaction.builder()
                .fromAccountNumber(transactionDetails.getFromAccountNumber())
                .toAccountNumber(transactionDetails.getToAccountNumber())
                .transferAmount(transactionDetails.getTransferAmount())
                .build();
    }

    public TransactionDetails transactionDetailsMapper(Transaction transaction){

        return TransactionDetails.builder()
                .fromAccountNumber(transaction.getFromAccountNumber())
                .toAccountNumber(transaction.getToAccountNumber())
                .transferAmount(transaction.getTransferAmount())
                .build();
    }

    public AccountAndTransaction accountAndTransactionMapper(AccountAndTransactionDetails accountAndTransactionDetails){

        return AccountAndTransaction.builder()
                .accountNumber(accountAndTransactionDetails.getAccountNumber())
                .transactionType(accountAndTransactionDetails.getTransactionType())
                .amount(accountAndTransactionDetails.getAmount())
                .transactionTime(accountAndTransactionDetails.getTransactionTime())
                .build();

    }

    public AccountAndTransactionDetails accountAndTransactionDetailsMapper(AccountAndTransaction accountAndTransaction){

        return AccountAndTransactionDetails.builder()
                .accountNumber(accountAndTransaction.getAccountNumber())
                .transactionType(accountAndTransaction.getTransactionType())
                .amount(accountAndTransaction.getAmount())
                .transactionTime(accountAndTransaction.getTransactionTime())
                .build();
    }

    public AccountAndTransaction accountAndTransactionDatabase(Transaction transaction ,Long accountNumber,String txType){

        return AccountAndTransaction.builder()
                .accountNumber(accountNumber)
                .transactionTime(transaction.getTransferTime())
                .amount(transaction.getTransferAmount())
                .transactionType(txType)
                .build();
    }

}
