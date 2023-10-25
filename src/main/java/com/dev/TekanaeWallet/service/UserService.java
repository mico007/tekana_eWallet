package com.dev.TekanaeWallet.service;

import com.dev.TekanaeWallet.io.entity.UserEntity;
import com.dev.TekanaeWallet.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserEntity createUser(UserDto userDto);

    UserDto getUser(String email);

    UserDto loginUser(UserDto userDto);
}
