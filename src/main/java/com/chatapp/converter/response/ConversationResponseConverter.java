package com.chatapp.converter.response;

import com.chatapp.constant.MessageStatus;
import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.ConversationResponseDTO;
import com.chatapp.entity.ConversationEntity;
import com.chatapp.entity.MessageEntity;
import com.chatapp.repository.MessageRepository;
import com.chatapp.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ConversationResponseConverter extends BaseConverter<ConversationEntity, ConversationResponseDTO> {
    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageResponseConverter messageConverter;

    @Override
    public ConversationResponseDTO toDTO(ConversationEntity conversationEntity) {
        ConversationResponseDTO conversationResponseDTO = super.toDTO(conversationEntity);
        conversationResponseDTO.setSender(userInfoResponseConverter.toDTO(conversationEntity.getSender()));
        conversationResponseDTO.setReceiver(userInfoResponseConverter.toDTO(conversationEntity.getReceiver()));
        int lastMessageIndex = conversationEntity.getMessages().size() - 1;
        MessageEntity lastMessage = conversationEntity.getMessages().get(lastMessageIndex);
        conversationResponseDTO.setLastMessageContent(lastMessage.getContent());
        conversationResponseDTO.setLastMessageSentAt(lastMessage.getCreatedAt());
        long countMessagesNotSeen = conversationEntity.getMessages().stream().filter(messageEntity ->
                    messageEntity.getStatus() == MessageStatus.NOT_SEEN
                            && messageEntity.getSender().getId() == conversationEntity.getSender().getId()
                            && messageEntity.getReceiver().getId() == conversationEntity.getReceiver().getId()
        ).collect(Collectors.toList()).size();
        conversationResponseDTO.setCountNewMessage(countMessagesNotSeen);
        return conversationResponseDTO;
    }
}
