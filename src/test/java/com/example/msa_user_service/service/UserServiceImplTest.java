package com.example.msa_user_service.service;

import com.example.msa_user_service.dto.UserDto;
import com.example.msa_user_service.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void 멤버생성(){
    // given
    String email ="made_power@naver.com";
    String name = "minu";
    String pwd = "test1234";

    // when
        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setName(name);
        userDto.setPwd(pwd);
        int count = userRepository.findAll().size();
        userService.createUser(userDto);

    // then
        Assertions.assertThat(count).isEqualTo(1);


    }

}