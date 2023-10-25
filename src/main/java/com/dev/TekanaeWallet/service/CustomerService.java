package com.dev.TekanaeWallet.service;

import com.dev.TekanaeWallet.io.entity.CustomerEntity;
import com.dev.TekanaeWallet.shared.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerEntity registerCustomer(CustomerEntity customerDto);
    List<CustomerEntity> getAllCustomers();

    CustomerEntity getCustomerByPublicId(String customerPublicId);
}
