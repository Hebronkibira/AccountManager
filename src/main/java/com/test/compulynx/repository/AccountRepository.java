package com.test.compulynx.repository;

import com.test.compulynx.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT acc.currentBalance FROM Account acc WHERE acc.customerId =:customerId")
    Double getCustomerBalance(String customerId);


    @Modifying
    @Transactional
    @Query("UPDATE Account acc set acc.currentBalance =:newBalance WHERE acc.customerId=:customerId ")
   void debitCustomerAccount(Double newBalance,String customerId);

    @Modifying
    @Transactional
    @Query("UPDATE Account acc set acc.currentBalance =:newBalance WHERE acc.customerId=:customerId ")
    void creditCustomerAccount(Double newBalance,String customerId);


}
