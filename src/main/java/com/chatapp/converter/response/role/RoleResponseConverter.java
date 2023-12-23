package com.chatapp.converter.response.role;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.role.RoleResponseDTO;
import com.chatapp.entity.RoleEntity;

import org.springframework.stereotype.Component;

@Component
public class RoleResponseConverter extends BaseConverter<RoleEntity, RoleResponseDTO> {
}
