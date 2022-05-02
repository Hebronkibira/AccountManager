package com.test.compulynx.controller;

import com.test.compulynx.model.Transaction;
import com.test.compulynx.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping("/transactions/{customerId}")
    public List<Transaction> getCustomerMiniStatement(@PathVariable("customerId")String customerId){
        return transactionService.getMiniStatement(customerId);
    }

    @GetMapping("/transactions/search/{txnId}")
    public Transaction getTransactionById(@PathVariable("txnId")String txnId){
        return transactionService.getTransactionById(txnId);
    }
}
