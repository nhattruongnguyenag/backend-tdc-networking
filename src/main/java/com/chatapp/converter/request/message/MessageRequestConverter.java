package com.chatapp.converter.request.message;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.message.MessageRequestDTO;
import com.chatapp.entity.MessageEntity;
import org.springframework.stereotype.Component;

@Component
public class MessageRequestConverter extends BaseConverter<MessageEntity, MessageRequestDTO> {
    @Override
    public MessageEntity toEntity(MessageRequestDTO messageRequestDTO) {
        return super.toEntity(messageRequestDTO);
    }
}
