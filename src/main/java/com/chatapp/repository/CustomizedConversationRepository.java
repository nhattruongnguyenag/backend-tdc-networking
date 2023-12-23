package com.chatapp.repository;

import com.chatapp.entity.ConversationEntity;

import java.util.List;

public interface CustomizedConversationRepository {
    public List<ConversationEntity> findBySenderAndReceiver(long senderId, long receiverId);

    long countDistinctBySender_IdAndMessages_Status(long senderId, long status);
}
