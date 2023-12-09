package com.chatapp.repository;

import com.chatapp.dto.Pagination;
import com.chatapp.entity.MessageEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomizedMessageRepository {
    List<MessageEntity> findBySenderOrReceiver(Long senderId, Long receiverId);

    List<MessageEntity> findBySenderOrReceiver(Long senderId, Long receiverId, Pagination pagination);
}
