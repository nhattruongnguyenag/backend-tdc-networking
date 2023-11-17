package com.chatapp.service.impl;

import com.chatapp.converter.response.ConversationResponseConverter;
import com.chatapp.dto.response.ConversationResponseDTO;
import com.chatapp.repository.ConversationRepository;
import com.chatapp.repository.CustomizedConversationRepository;
import com.chatapp.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {
    @Autowired
    private ConversationResponseConverter conversationResponseConverter;

    @Autowired
    private CustomizedConversationRepository customizedConversationRepository;
    @Autowired
    private ConversationRepository conversationRepository;

    @Override
    public List<ConversationResponseDTO> findBySenderAndReceiver(long senderId, long receiverId) {
        return conversationResponseConverter.toDTOGroup(customizedConversationRepository.findBySenderAndReceiver(senderId, receiverId));
    }

    @Override
    public List<ConversationResponseDTO> findBySender(long senderId) {
        return conversationResponseConverter.toDTOGroup(conversationRepository.findBySender_Id(senderId));
    }
}
