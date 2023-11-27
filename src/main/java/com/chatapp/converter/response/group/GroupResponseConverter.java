package com.chatapp.converter.response.group;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.group.GroupResponseDTO;
import com.chatapp.entity.GroupEntity;

import org.springframework.stereotype.Component;

@Component
public class GroupResponseConverter extends BaseConverter<GroupEntity, GroupResponseDTO> {
}
