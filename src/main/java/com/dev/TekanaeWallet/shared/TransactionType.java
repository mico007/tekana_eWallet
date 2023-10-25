package com.dev.TekanaeWallet.shared;


// This enum class is used to define the type of transaction
public enum TransactionType {
    DEPOSIT("1"),
    WITHDRAWAL("2");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
