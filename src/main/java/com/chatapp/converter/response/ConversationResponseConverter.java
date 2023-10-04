package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.ConversationResponseDTO;
import com.chatapp.entity.ConversationEntity;
import org.springframework.stereotype.Component;

@Component
public class ConversationResponseConverter extends BaseConverter<ConversationEntity, ConversationResponseDTO> {
    @Override
    public ConversationResponseDTO toDTO(ConversationEntity conversationEntity) {
        ConversationResponseDTO conversationResponseDTO = super.toDTO(conversationEntity);
        conversationResponseDTO.setSenderId(conversationEntity.getSender().getId());
        conversationResponseDTO.setReceiverId(conversationEntity.getReceiver().getId());
        return conversationResponseDTO;
    }
}
