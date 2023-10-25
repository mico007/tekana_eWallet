package com.dev.TekanaeWallet.service.impl;

import com.dev.TekanaeWallet.exeptions.TekanaEWalletException;
import com.dev.TekanaeWallet.io.entity.TransactionEntity;
import com.dev.TekanaeWallet.io.entity.WalletEntity;
import com.dev.TekanaeWallet.io.repository.TransactionRepository;
import com.dev.TekanaeWallet.io.repository.WalletRepository;
import com.dev.TekanaeWallet.service.TransactionService;
import com.dev.TekanaeWallet.shared.TransactionType;
import com.dev.TekanaeWallet.shared.Utils;
import com.dev.TekanaeWallet.ui.model.request.TransactionRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    Utils utils;


    // Create a new transaction
    @Override
    public TransactionEntity createTransaction(TransactionRequestModel transactionDetails) {

        // Check if wallet exist
        WalletEntity walletRecord = walletRepository.findWalletByWalletId((transactionDetails.getWalletId()));
        if (walletRecord == null) throw new TekanaEWalletException("Wallet could not be found", 404);

        TransactionEntity transactionEntity = new TransactionEntity();

        // Generate transaction public id
        String transactionPublicId = utils.generateTransactionPublicId(30);
        transactionEntity.setTransactionId(transactionPublicId);

        // Check if transaction type is Deposit or Withdrawal and create new transaction accordingly
        if (transactionDetails.getTransactionType().equals(TransactionType.DEPOSIT.getValue())) {

            transactionEntity.setAmount(transactionDetails.getAmount());
            transactionEntity.setDescription(transactionDetails.getDescription());
            transactionEntity.setTransactionType(transactionDetails.getTransactionType());
            transactionEntity.setWalletDetails(walletRecord);

            // Add the amount to the wallet balance
            walletRecord.setBalance(walletRecord.getBalance() + transactionDetails.getAmount());
            walletRepository.save(walletRecord);

            return transactionRepository.save(transactionEntity);

        } else if (transactionDetails.getTransactionType().equals(TransactionType.WITHDRAWAL.getValue())) {

            // Check if the wallet has sufficient funds
            if (walletRecord.getBalance() < transactionDetails.getAmount())
                throw new TekanaEWalletException("Insufficient funds", 406);

            transactionEntity.setAmount(transactionDetails.getAmount());
            transactionEntity.setDescription(transactionDetails.getDescription());
            transactionEntity.setTransactionType(transactionDetails.getTransactionType());
            transactionEntity.setWalletDetails(walletRecord);

            // Deduct the amount from the wallet balance
            walletRecord.setBalance(walletRecord.getBalance() - transactionDetails.getAmount());
            walletRepository.save(walletRecord);

            return transactionRepository.save(transactionEntity);

        } else {
            throw new TekanaEWalletException("Invalid transaction type", 400);
        }
    }


    // Read all transactions
    @Override
    public List<TransactionEntity> getAllTransactions(String transactionType) {

        // Check if transaction type is not null and return all transactions of that type. And if it is null, return all transactions

        if (transactionType != null && transactionType.equals(TransactionType.DEPOSIT.getValue())) {

            List<TransactionEntity> allDepositTransactions = transactionRepository.findAllByTransactionType(transactionType);

            if (allDepositTransactions.isEmpty()) throw new TekanaEWalletException("Not found", 404);

            return allDepositTransactions;
        } else if (transactionType != null && transactionType.equals(TransactionType.WITHDRAWAL.getValue())) {

            List<TransactionEntity> allWithdrawalTransactions = transactionRepository.findAllByTransactionType(transactionType);

            if (allWithdrawalTransactions.isEmpty()) throw new TekanaEWalletException("Not found", 404);

            return allWithdrawalTransactions;
        } else {
            List<TransactionEntity> allTransactions = (List<TransactionEntity>) transactionRepository.findAll();

            if (allTransactions.isEmpty()) throw new TekanaEWalletException("Not found", 404);

            return allTransactions;
        }
    }

    // Get transactions of a customer
    @Override
    public List<TransactionEntity> getTransactionsOfACustomer(String walletId, String transactionType) {

        // Check if a wallet exist
        WalletEntity walletRecord = walletRepository.findWalletByWalletId(walletId);
        if (walletRecord == null) throw new TekanaEWalletException("Wallet could not be found", 404);

        // Check if transaction type is not null and return all customer transactions of that type. And if it is null, return all customer transactions

        if (transactionType != null && transactionType.equals(TransactionType.DEPOSIT.getValue())) {

            List<TransactionEntity> allDepositTransactionsOfACustomer = transactionRepository.findAllByTransactionTypeAndWalletDetails(transactionType, walletRecord);

            if (allDepositTransactionsOfACustomer.isEmpty()) throw new TekanaEWalletException("Not found", 404);

            return allDepositTransactionsOfACustomer;

        } else if (transactionType != null && transactionType.equals(TransactionType.WITHDRAWAL.getValue())) {

            List<TransactionEntity> allWithdrawalTransactionsOfACustomer = transactionRepository.findAllByTransactionTypeAndWalletDetails(transactionType, walletRecord);

            if (allWithdrawalTransactionsOfACustomer.isEmpty()) throw new TekanaEWalletException("Not found", 404);

            return allWithdrawalTransactionsOfACustomer;

        } else {

            List<TransactionEntity> allTransactionsOfACustomer = transactionRepository.findAllByWalletDetails(walletRecord);

            if (allTransactionsOfACustomer.isEmpty()) throw new TekanaEWalletException("Not found", 404);

            return allTransactionsOfACustomer;

        }

    }
}
