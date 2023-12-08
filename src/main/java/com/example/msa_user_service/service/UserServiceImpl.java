package com.example.msa_user_service.service;

import com.example.msa_user_service.dto.UserDto;
import com.example.msa_user_service.entity.UserEntity;
import com.example.msa_user_service.repository.UserRepository;
import com.example.msa_user_service.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));

        userRepository.save(userEntity);


        return mapper.map(userDto,UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(String userId) {

        UserEntity user = userRepository.getReferenceById(Long.valueOf(userId));
        UserDto userDto = new ModelMapper().map(user, UserDto.class);


        List<ResponseOrder> orders = new ArrayList<>();
        userDto.setOrders(orders);

        return userDto;


    }

    @Override
    public Iterable<UserDto> getUserByAll() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<UserEntity> all = userRepository.findAll();
        return  all.stream()
                .map(entity -> mapper.map(entity, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null){
            throw new UsernameNotFoundException(email);
        }
        return new ModelMapper().map(userEntity,UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);

        if(userEntity == null){
            throw new UsernameNotFoundException(username);
        }
        return new User(userEntity.getEmail(), userEntity.getPassword(),true,true,true,true, new ArrayList<>());
    }
}
