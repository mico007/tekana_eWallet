package com.dev.TekanaeWallet.service.impl;

import com.dev.TekanaeWallet.exeptions.TekanaEWalletException;
import com.dev.TekanaeWallet.io.entity.UserEntity;
import com.dev.TekanaeWallet.io.repository.UserRepository;
import com.dev.TekanaeWallet.service.UserService;
import com.dev.TekanaeWallet.shared.Utils;
import com.dev.TekanaeWallet.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserEntity createUser(UserDto userDto) {

        //checking if user already exists
        if(userRepository.findByEmail(userDto.getEmail()) != null) throw new TekanaEWalletException("Record already exists", 406);

        //transforming userDto object to userEntity object
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);

        //generating user public id
        String userPublicId = utils.generateUserPublicId(30);

        //setting user public id and encrypted password
        userEntity.setUserId(userPublicId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        return userRepository.save(userEntity);
    }

    @Override
    public UserDto getUser(String email) {

        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null) throw new TekanaEWalletException("not found", 404);

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;
    }

    @Override
    public UserDto loginUser(UserDto userDto) {

        //checking if user exists
        UserEntity userEntity = userRepository.findByEmail(userDto.getEmail());
        if(userEntity == null) throw new TekanaEWalletException("Invalid Credentials", 400);

        //checking if password matches
        if (
                !bCryptPasswordEncoder.matches(userDto.getPassword(), userEntity.getEncryptedPassword())
        ) throw new TekanaEWalletException("Invalid credentials", 400);

        //generating token
        String token = utils.generateToken(userEntity.getUserId());

        userDto.setToken(token);
        userDto.setFullName(userEntity.getFullName());
        userDto.setUserId(userEntity.getUserId());

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userDto, returnValue);

        return returnValue;
    }


    // This method is used by Spring Security to authenticate and authorize user
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null) throw new TekanaEWalletException("Invalid credentials", 400);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
