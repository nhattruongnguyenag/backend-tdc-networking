package com.chatapp.repository;

import com.chatapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByEmailAndStatus(String email, int status);
    List<UserEntity> findAllByStatusNot(Byte status);
    List<UserEntity> findAllByNameContains(String name);
    List<UserEntity> findAllByNameContainsAndRoles_Code(String name , String code);
    UserEntity findOneById(Long id);
    UserEntity findOneById(Integer id);
    UserEntity findOneByCode(String code);
    UserEntity findOneByEmailAndPassword(String email, String password);
    UserEntity findOneByEmail(String email);
}
