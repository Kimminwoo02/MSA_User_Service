package com.example.msa_user_service.service;

import com.example.msa_user_service.dto.UserDto;
import com.example.msa_user_service.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    Iterable<UserDto> getUserByAll();

    UserDto getUserDetailsByEmail(String userName);
}
