package com.dev.TekanaeWallet.io.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


// This class is used to map the wallet table in the database
@Entity(name = "wallets")
@Table(indexes = @Index(name = "index_customer_id", columnList = "customer_id")) // This is used to create an index on the customer_id column
public class WalletEntity implements Serializable {
    private static final long serialVersionUID = 9002732080495900809L;

    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @Column(length = 30, nullable = false)
    private String walletId;
    private double balance = 0.0;

    private Date date = new Date();

    @ManyToOne
    @JoinColumn(name="customer_id")
    private CustomerEntity customerDetails;

    @OneToMany(mappedBy = "walletDetails", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public CustomerEntity getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerEntity customerDetails) {
        this.customerDetails = customerDetails;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
