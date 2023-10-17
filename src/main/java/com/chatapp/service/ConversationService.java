package com.chatapp.service;

import com.chatapp.dto.response.ConversationResponseDTO;

import java.util.List;

public interface ConversationService {
    List<ConversationResponseDTO> findBySenderAndReceiver(long senderId, long receiverId);
    List<ConversationResponseDTO> findBySender(long senderId);
}
