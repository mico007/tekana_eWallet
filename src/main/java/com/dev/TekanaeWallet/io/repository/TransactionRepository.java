package com.dev.TekanaeWallet.io.repository;

import com.dev.TekanaeWallet.io.entity.TransactionEntity;
import com.dev.TekanaeWallet.io.entity.WalletEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


// This interface is used to perform CRUD operations on the transaction table
@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
    List<TransactionEntity> findAllByTransactionType(String transactionType);
    List<TransactionEntity> findAllByTransactionTypeAndWalletDetails(String transactionType, WalletEntity walletDetails);
    List<TransactionEntity> findAllByWalletDetails(WalletEntity walletDetails);
}
