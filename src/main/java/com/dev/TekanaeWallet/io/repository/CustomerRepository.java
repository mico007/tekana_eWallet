package com.dev.TekanaeWallet.io.repository;

import com.dev.TekanaeWallet.io.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


// This interface is used to perform CRUD operations on the customer table
@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    CustomerEntity findByEmail(String email);
    CustomerEntity findCustomerByCustomerId(String customerId);
}
