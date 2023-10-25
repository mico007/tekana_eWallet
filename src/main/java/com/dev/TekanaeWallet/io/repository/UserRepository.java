package com.dev.TekanaeWallet.io.repository;

import com.dev.TekanaeWallet.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


// This interface is used to perform CRUD operations on the user table
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
