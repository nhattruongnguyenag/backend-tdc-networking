package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.ConversationResponseDTO;
import com.chatapp.entity.ConversationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversationResponseConverter extends BaseConverter<ConversationEntity, ConversationResponseDTO> {
    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;

    @Override
    public ConversationResponseDTO toDTO(ConversationEntity conversationEntity) {
        ConversationResponseDTO conversationResponseDTO = super.toDTO(conversationEntity);
        conversationResponseDTO.setSender(userInfoResponseConverter.toDTO(conversationEntity.getSender()));
        conversationResponseDTO.setReceiver(userInfoResponseConverter.toDTO(conversationEntity.getReceiver()));
        return conversationResponseDTO;
    }
}
