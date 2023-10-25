package com.dev.TekanaeWallet.io.repository;

import com.dev.TekanaeWallet.io.entity.CustomerEntity;
import com.dev.TekanaeWallet.io.entity.WalletEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


// This interface is used to perform CRUD operations on the wallet table
@Repository
public interface WalletRepository extends CrudRepository<WalletEntity, Long> {
    List<WalletEntity> findAllByCustomerDetails(CustomerEntity customerDetails);
    WalletEntity findWalletByWalletId(String walletId);
}
