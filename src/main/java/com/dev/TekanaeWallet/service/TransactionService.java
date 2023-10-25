package com.dev.TekanaeWallet.service;

import com.dev.TekanaeWallet.io.entity.TransactionEntity;
import com.dev.TekanaeWallet.ui.model.request.TransactionRequestModel;

import java.util.List;

public interface TransactionService {
    TransactionEntity createTransaction(TransactionRequestModel transactionDetails);
    List<TransactionEntity> getAllTransactions(String transactionType);
    List<TransactionEntity> getTransactionsOfACustomer(String walletId, String transactionType);
}
