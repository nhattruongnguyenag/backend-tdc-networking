package com.chatapp.converter.request.message;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.message.MessageSaveRequestDTO;
import com.chatapp.entity.MessageEntity;
import org.springframework.stereotype.Component;

@Component
public class MessageRequestConverter extends BaseConverter<MessageEntity, MessageSaveRequestDTO> {
    @Override
    public MessageEntity toEntity(MessageSaveRequestDTO messageRequestDTO) {
        return super.toEntity(messageRequestDTO);
    }
}
