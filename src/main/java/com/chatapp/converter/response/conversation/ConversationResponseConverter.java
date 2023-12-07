package com.chatapp.converter.response.conversation;

import com.chatapp.constant.MessageStatus;
import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.converter.response.user.UserInfoResponseConverter;
import com.chatapp.dto.response.conversation.ConversationResponseDTO;
import com.chatapp.entity.ConversationEntity;
import com.chatapp.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ConversationResponseConverter extends BaseConverter<ConversationEntity, ConversationResponseDTO> {
    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;


    @Override
    public ConversationResponseDTO toDTO(ConversationEntity conversationEntity) {
        ConversationResponseDTO conversationResponseDTO = super.toDTO(conversationEntity);
        conversationResponseDTO.setSender(userInfoResponseConverter.toDTO(conversationEntity.getSender()));
        conversationResponseDTO.setReceiver(userInfoResponseConverter.toDTO(conversationEntity.getReceiver()));
        int lastMessageIndex = conversationEntity.getMessages().size() - 1;


        if (lastMessageIndex != -1) {
            MessageEntity lastMessage = conversationEntity.getMessages().get(lastMessageIndex);
            conversationResponseDTO.setLastMessageContent(lastMessage.getContent());
            conversationResponseDTO.setLastMessageSentAt(lastMessage.getCreatedAt());
            conversationResponseDTO.setLastMessageType(lastMessage.getType());
        }

        long countMessagesNotSeen = conversationEntity.getMessages().stream().filter(messageEntity ->
                    messageEntity.getStatus() == MessageStatus.NOT_SEEN
                            && messageEntity.getSender().getId() == conversationEntity.getReceiver().getId()
                            && messageEntity.getReceiver().getId() == conversationEntity.getSender().getId()
        ).collect(Collectors.toList()).size();
        conversationResponseDTO.setCountNewMessage(countMessagesNotSeen);
        return conversationResponseDTO;
    }
}
