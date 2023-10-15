package com.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.FollowEntity;

@Repository
public interface FollowReposittory extends JpaRepository<FollowEntity, Long> {
    FollowEntity findOneByUser_IdAndFollow_Id(Long userId, Long followId);
}
