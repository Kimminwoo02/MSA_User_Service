package com.example.msa_user_service.service;

import com.example.msa_user_service.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}
