package com.chatapp.service;

import com.chatapp.dto.request.MessageRequestDTO;
import com.chatapp.dto.response.ConversationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ConversationService {
    List<ConversationResponseDTO> findBySenderAndReceiver(long senderId, long receiverId);
}
