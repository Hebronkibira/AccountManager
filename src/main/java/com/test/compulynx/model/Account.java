package com.test.compulynx.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Account {
    @Id
    @SequenceGenerator(name = "accGenerator", allocationSize = 1, sequenceName = "accGenerator")
    @GeneratedValue(generator = "accGenerator", strategy = GenerationType.SEQUENCE)
    private Long id;
    private double currentBalance;
    private String accountType;
    private Date dateCreated;
    private String accountNo;
    private String customerId;


    public Account() {
    }

    public Account(double currentBalance, String accountType, Date dateCreated, String accountNo, String customerId) {
        this.currentBalance = currentBalance;
        this.accountType = accountType;
        this.dateCreated = dateCreated;
        this.accountNo = accountNo;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}

