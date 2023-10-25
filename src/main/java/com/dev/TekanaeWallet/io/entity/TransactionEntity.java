package com.dev.TekanaeWallet.io.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


// This class is used to map the transaction table in the database
@Entity(name = "transactions")

// This is used to create a multiple indexes on the transactionType and wallet_id columns and on the transactionType column
@Table(indexes = {
        @Index(name = "index_transaction_type", columnList = "transactionType"),
        @Index(name = "multiIndex_transactionType_wallet_id", columnList = "transactionType, wallet_id")
})
public class TransactionEntity implements Serializable {
    private static final long serialVersionUID = 9002732080595900709L;

    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;
    @Column(length = 30, nullable = false)
    private String transactionId;
    @Column(nullable = false)
    private String transactionType;
    @Column(nullable = false)
    private double amount;
    private String description;
    private Date date = new Date();

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private WalletEntity walletDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public WalletEntity getWalletDetails() {
        return walletDetails;
    }

    public void setWalletDetails(WalletEntity walletDetails) {
        this.walletDetails = walletDetails;
    }
}
