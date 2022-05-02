package com.test.compulynx.service;

import com.test.compulynx.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;
    private final CustomerService customerService;

    public AccountService(AccountRepository accountRepository, TransactionService transactionService, CustomerService customerService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
        this.customerService = customerService;
    }

    public Double getCustomerBalance(String customerId) {
        return accountRepository.getCustomerBalance(customerId);
    }

    public Double debitCustomerAccount(String customerId, Double amount) {
        //Get customer balance by customerId;
        //TODO check is the customer with that id exists
         if(!this.customerIdExists(customerId)){
             throw new IllegalArgumentException(String.format("Failed ! Customer with customer id %s does not exist", customerId));
         }
        Double currentBalance = this.getCustomerBalance(customerId);
        if (currentBalance < amount) {
            throw new IllegalStateException(String.format("Failed ! Your do not have enough money in your account to withdraw %s ", amount));
        }
        Double newBalance = currentBalance - amount;
        accountRepository.debitCustomerAccount(newBalance, customerId);
        transactionService.saveTransaction("Debit", amount, customerId);
        return this.getCustomerBalance(customerId);
    }

    public Double creditCustomerAccount(String customerId, Double amount) {
        //Get customer balance by customerId;
        Double currentBalance = this.getCustomerBalance(customerId);
        Double newBalance = currentBalance + amount;
        accountRepository.creditCustomerAccount(newBalance, customerId);
        //Save transaction to the db
        transactionService.saveTransaction("Credit", amount, customerId);
        return this.getCustomerBalance(customerId);
    }

    public Double transferFunds(Double amount, String sourceCustomerId, String receiverCustomerId) {
        this.debitCustomerAccount(sourceCustomerId, amount);
        this.creditCustomerAccount(receiverCustomerId, amount);
        return this.getCustomerBalance(sourceCustomerId);
    }

    private Boolean customerIdExists(String customerId){
        System.out.println("Search customer id"+customerId);
        if(customerId.isEmpty()){
            return false;
        }
        return customerService.findCustomerByCustomerId(customerId);
    }
}
