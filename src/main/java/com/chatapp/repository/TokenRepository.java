package com.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.TokenResetPasswordEntity;

@Repository
public interface TokenRepository extends JpaRepository<TokenResetPasswordEntity, Long> {
    
}
