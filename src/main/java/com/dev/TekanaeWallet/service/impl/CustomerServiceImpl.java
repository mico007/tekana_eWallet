package com.dev.TekanaeWallet.service.impl;

import com.dev.TekanaeWallet.exeptions.TekanaEWalletException;
import com.dev.TekanaeWallet.io.entity.CustomerEntity;
import com.dev.TekanaeWallet.io.repository.CustomerRepository;
import com.dev.TekanaeWallet.service.CustomerService;
import com.dev.TekanaeWallet.shared.Utils;
import com.dev.TekanaeWallet.shared.dto.CustomerDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    Utils utils;


    // Register a new customer
    @Override
    public CustomerEntity registerCustomer(CustomerEntity customerEntity) {

        // Check if customer record exist
        CustomerEntity customerRecord = customerRepository.findByEmail(customerEntity.getEmail());
        if (customerRecord != null) throw new TekanaEWalletException("Customer record already exist", 400);

        // Generate customer public id
        String customerPublicId = utils.generateCustomerPublicId(30);
        customerEntity.setCustomerId(customerPublicId);

        return customerRepository.save(customerEntity);
    }


    // Retrieve all customers
    @Override
    public List<CustomerEntity> getAllCustomers() {
        List<CustomerEntity> allCustomers = (List<CustomerEntity>) customerRepository.findAll();
        if(allCustomers.isEmpty()) throw new TekanaEWalletException("Not found", 404);
        return allCustomers;
    }

    // Retrieve a customer record by public id
    @Override
    public CustomerEntity getCustomerByPublicId(String customerPublicId) {

        CustomerEntity foundCustomer = customerRepository.findCustomerByCustomerId(customerPublicId);

        if (foundCustomer == null) throw new TekanaEWalletException("Customer record not found", 404);

        return foundCustomer;
    }
}
