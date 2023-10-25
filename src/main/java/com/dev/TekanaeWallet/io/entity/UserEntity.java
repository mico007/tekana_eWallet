package com.dev.TekanaeWallet.io.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;


// This class is used to map the user table in the database
@Entity(name = "users")

// This is used to create an index on the email column
@Table(indexes = @Index(name = "index_user_email", columnList = "email"))
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 2511091629056507506L;

    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, length = 120)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String encryptedPassword;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
}
