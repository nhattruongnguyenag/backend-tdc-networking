package com.chatapp.converter.response.message;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.converter.response.user.UserMessageResponseConverter;
import com.chatapp.dto.response.message.MessageResponseDTO;
import com.chatapp.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageResponseConverter extends BaseConverter<MessageEntity, MessageResponseDTO> {
    @Autowired
    private UserMessageResponseConverter userMessageResponseConverter;

    @Override
    public MessageResponseDTO toDTO(MessageEntity messageEntity) {
        MessageResponseDTO messageResponse = super.toDTO(messageEntity);
        messageResponse.setSender(userMessageResponseConverter.toDTO(messageEntity.getSender()));
        messageResponse.setReceiver(userMessageResponseConverter.toDTO(messageEntity.getReceiver()));
        return messageResponse;
    }
}
