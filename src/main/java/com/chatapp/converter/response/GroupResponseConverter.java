package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.GroupResponseDTO;
import com.chatapp.entity.GroupEntity;

import org.springframework.stereotype.Component;

@Component
public class GroupResponseConverter extends BaseConverter<GroupEntity, GroupResponseDTO> {

    @Override
    public GroupResponseDTO toDTO(GroupEntity entity) {
        GroupResponseDTO groupResponseDTO = super.toDTO(entity);
        return groupResponseDTO;
    }
}
