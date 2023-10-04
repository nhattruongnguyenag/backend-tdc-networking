package com.chatapp.service;

import com.chatapp.dto.request.MessageRequestDTO;
import com.chatapp.dto.response.MessageResponseDTO;

import java.util.List;

public interface MessageService {
    List<MessageResponseDTO> findBySenderAndReceiver(Long senderId, Long receiverId);
    List<MessageResponseDTO> findByConversations_Id(Long conversationId);
    MessageResponseDTO save(MessageRequestDTO messageRequestDTO);
    MessageResponseDTO delete(Long messageId);
}
