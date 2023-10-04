package com.chatapp.service.impl;

import com.chatapp.converter.request.MessageRequestConverter;
import com.chatapp.converter.response.ConversationResponseConverter;
import com.chatapp.dto.request.MessageRequestDTO;
import com.chatapp.dto.response.ConversationResponseDTO;
import com.chatapp.entity.ConversationEntity;
import com.chatapp.entity.MessageEntity;
import com.chatapp.repository.ConversationRepository;
import com.chatapp.repository.CustomizedConversationRepository;
import com.chatapp.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationServiceImpl implements ConversationService {
    @Autowired
    private ConversationResponseConverter conversationResponseConverter;
    @Autowired
    private MessageRequestConverter messageRequestConverter;
    @Autowired
    private CustomizedConversationRepository customizedConversationRepository;
    @Autowired
    private ConversationRepository conversationRepository;

    @Override
    public List<ConversationResponseDTO> findBySenderAndReceiver(long senderId, long receiverId) {
        return conversationResponseConverter.toDTOGroup(customizedConversationRepository.findBySenderAndReceiver(senderId, receiverId));
    }
}
