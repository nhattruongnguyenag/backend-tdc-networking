package com.chatapp.repository;

import com.chatapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByEmailAndStatus(String email, int status);
    List<UserEntity> findAllByStatusNot(Byte status);
    UserEntity findOneById(Long id);
    UserEntity findOneByCode(String code);
    UserEntity findOneByEmailAndPassword(String email, String password);
    UserEntity findOneByEmail(String email);
}
