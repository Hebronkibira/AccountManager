package com.test.compulynx.repository;

import com.test.compulynx.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TRANSACTION tx where tx.customer_id=:customerId order by tx.trx_date limit 10")
    List<Transaction> getMiniStatement(String customerId);

    @Query("FROM Transaction tx WHERE tx.trxId=:trxId")
    Transaction findByTxnId(String trxId);

}
