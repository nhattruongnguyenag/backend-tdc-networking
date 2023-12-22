package com.chatapp.service;

import java.util.List;

import com.chatapp.dto.response.conversation.ConversationResponseDTO;

public interface ConversationService {
    List<ConversationResponseDTO> findBySenderAndReceiver(long senderId, long receiverId);
    List<ConversationResponseDTO> findBySender(long senderId);
    long countBySender_IdAndMessages_Status(long senderId, long status);
}
