package com.dev.TekanaeWallet.service;

import com.dev.TekanaeWallet.io.entity.WalletEntity;
import com.dev.TekanaeWallet.ui.model.request.WalletRequestModel;

import java.util.List;

public interface WalletService {
    WalletEntity createWallet(WalletRequestModel walletRequestDetails);
    List<WalletEntity> getAllWallets();

    List<WalletEntity> getWalletsOfACustomer(String customerId);
}
