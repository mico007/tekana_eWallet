package com.dev.TekanaeWallet.service.impl;

import com.dev.TekanaeWallet.exeptions.TekanaEWalletException;
import com.dev.TekanaeWallet.io.entity.CustomerEntity;
import com.dev.TekanaeWallet.io.entity.WalletEntity;
import com.dev.TekanaeWallet.io.repository.CustomerRepository;
import com.dev.TekanaeWallet.io.repository.WalletRepository;
import com.dev.TekanaeWallet.service.WalletService;
import com.dev.TekanaeWallet.shared.Utils;
import com.dev.TekanaeWallet.ui.model.request.WalletRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    Utils utils;

    //creating a new wallet
    @Override
    public WalletEntity createWallet(WalletRequestModel walletRequestDetails) {
        CustomerEntity customerRecord = customerRepository.findCustomerByCustomerId(walletRequestDetails.getCustomerId());
        if (customerRecord == null) throw new TekanaEWalletException("Customer could not be found", 404);
        WalletEntity walletEntity = new WalletEntity();

        // Generate wallet public id
        String walletPublicId = utils.generateWalletPublicId(30);
        walletEntity.setWalletId(walletPublicId);
        walletEntity.setCustomerDetails(customerRecord);

        return walletRepository.save(walletEntity);
    }

    //retrieving all wallets
    @Override
    public List<WalletEntity> getAllWallets() {

        List<WalletEntity> allWallets = (List<WalletEntity>) walletRepository.findAll();
        if(allWallets.isEmpty()) throw new TekanaEWalletException("Not found", 404);

        return allWallets;
    }

    //retrieving all wallets registered under a provided customer
    @Override
    public List<WalletEntity> getWalletsOfACustomer(String customerId) {

        // Check if a customer exist
        CustomerEntity customerRecord = customerRepository.findCustomerByCustomerId(customerId);
        if(customerRecord == null) throw new TekanaEWalletException("Customer could not be found", 404);

        List<WalletEntity> foundWalletsOfACustomer = walletRepository.findAllByCustomerDetails(customerRecord);
        if(foundWalletsOfACustomer == null || foundWalletsOfACustomer.isEmpty()) throw new TekanaEWalletException("Not found", 404);

        return foundWalletsOfACustomer;
    }
}
