package com.test.compulynx.service;

import com.test.compulynx.model.Transaction;
import com.test.compulynx.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getMiniStatement(String customerId){
        return transactionRepository.getMiniStatement(customerId);
    }

    public void saveTransaction(String txnType,Double txnAmount,String customerId){
        String txnid=UUID.randomUUID().toString(); //generate a unique transaction id
        System.out.println("Random UUID "+txnid);
        txnid=txnid.substring(txnid.length()-10,txnid.length()).toUpperCase();
        System.out.println("Transaction Id "+txnid);
        Transaction transaction= new Transaction(txnid,customerId,txnType, new Date(),txnAmount);
        transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(String transactionId) {
        return transactionRepository.findByTxnId(transactionId);
    }


}
