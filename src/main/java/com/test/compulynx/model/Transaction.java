package com.test.compulynx.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @SequenceGenerator(name = "txnGenerator", allocationSize = 1, sequenceName = "txnGenerator")
    @GeneratedValue(generator = "txnGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "trxId")
    private String trxId;
    @Column(name = "customerId")
    private String customerId;
    @Column(name = "trxType")
    private String trxType;
    @Column(name = "trxDate")
    private Date trxDate;
    @Column(name = "trxAmount")
    private Double trxAmount;

    public Transaction() {
    }

    public Transaction(String trxId, String customerId, String trxType, Date trxDate, Double trxAmount) {
        this.trxId = trxId;
        this.customerId = customerId;
        this.trxType = trxType;
        this.trxDate = trxDate;
        this.trxAmount = trxAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }

    public Date getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(Date trxDate) {
        this.trxDate = trxDate;
    }

    public Double getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(Double trxAmount) {
        this.trxAmount = trxAmount;
    }
}
