package com.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.chatapp.entity.OptionUserEntity;
import com.chatapp.entity.identity.OptionUserId;

@Repository
public interface OptionUserRepository extends JpaRepository<OptionUserEntity, OptionUserId> {
    OptionUserEntity findOneByUser_IdAndOptionKey(Long userId, String optionKey);
}
