package com.dev.TekanaeWallet.ui.model.request;


//this class is the User Login Request Model, used to receive the user login details from the client
public class UserLoginRequestModel {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
