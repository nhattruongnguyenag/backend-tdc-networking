package com.chatapp.repository;

import com.chatapp.entity.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationRepository extends JpaRepository<ConversationEntity, Long> {
    List<ConversationEntity> findBySender_IdAndReceiver_Id(long senderId, long receiverId);
    List<ConversationEntity> findBySender_Id(long senderId);
    long countDistinctBySender_IdAndMessages_Status(long senderId, long status);
}
