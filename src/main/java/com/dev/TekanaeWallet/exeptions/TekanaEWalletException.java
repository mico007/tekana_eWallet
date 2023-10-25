package com.dev.TekanaeWallet.exeptions;


// This class is used to define the exception for the system
public class TekanaEWalletException extends RuntimeException{
    private static final long serialVersionUID = 9162514725884564932L;

    private String message;
    private int statusCode;

    public TekanaEWalletException(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
