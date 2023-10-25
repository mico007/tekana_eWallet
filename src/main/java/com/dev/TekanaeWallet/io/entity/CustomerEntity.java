package com.dev.TekanaeWallet.io.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


// This class is used to map the customer table in the database
@Entity(name = "customers")

// this is used to create an index on the customer_id column
@Table(indexes = @Index(name = "index_customer_id", columnList = "customerId"))
public class CustomerEntity implements Serializable {
    private static final long serialVersionUID = 2511091629056507505L;

    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;
    @Column(length = 30, nullable = false)
    private String customerId;
    @Column(length = 100, nullable = false)
    private String fullName;
    @Column(length = 120, nullable = false)
    private String email;
    @Column(length = 30, nullable = false)
    private String phone;
    @Column(length = 10, nullable = false)
    private String sex;
    @Column(nullable = false)
    private int age;
    private String address;

    private Date date = new Date();

    @OneToMany(mappedBy = "customerDetails", cascade = CascadeType.ALL)
    private  List<WalletEntity> wallets;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
