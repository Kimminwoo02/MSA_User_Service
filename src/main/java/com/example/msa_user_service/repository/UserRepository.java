package com.example.msa_user_service.repository;

import com.example.msa_user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUserId(String userId);

    UserEntity findByEmail(String username);
}
