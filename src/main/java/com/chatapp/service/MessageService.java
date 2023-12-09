package com.chatapp.service;

import com.chatapp.dto.Pagination;
import com.chatapp.dto.request.message.MessageSaveRequestDTO;
import com.chatapp.dto.response.message.MessageResponseDTO;

import java.util.List;

public interface MessageService {
    List<MessageResponseDTO> findBySenderAndReceiver(Long senderId, Long receiverId);
    List<MessageResponseDTO> findBySenderAndReceiver(Long senderId, Long receiverId, Pagination pagination);
    List<MessageResponseDTO> findByConversations_Id(Long conversationId);
    List<MessageResponseDTO> updateMessagesToReadState(Long senderId, Long receiverId);
    MessageResponseDTO save(MessageSaveRequestDTO messageRequestDTO);
    MessageResponseDTO delete(Long messageId);
}
