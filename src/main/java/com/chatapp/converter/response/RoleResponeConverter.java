package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.RoleResponeDTO;
import com.chatapp.entity.RoleEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleResponeConverter extends BaseConverter<RoleEntity, RoleResponeDTO> {

    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;

    @Override
    public RoleResponeDTO toDTO(RoleEntity entity) {
        RoleResponeDTO roleResponeDTO = super.toDTO(entity);
        roleResponeDTO.setUsers(userInfoResponseConverter.toDTOGroup(entity.getUsers()));
        return roleResponeDTO;
    }
}
