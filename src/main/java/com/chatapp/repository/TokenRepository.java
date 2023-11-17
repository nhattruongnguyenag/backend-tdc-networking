package com.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.TokenResetPasswordEntity;

@Repository
public interface TokenRepository extends JpaRepository<TokenResetPasswordEntity, Long> {
    List<TokenResetPasswordEntity> findAll();
    TokenResetPasswordEntity findOneByToken(String token);
}
