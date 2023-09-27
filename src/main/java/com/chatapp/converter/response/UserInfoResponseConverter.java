package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoResponseConverter extends BaseConverter<UserEntity, UserInfoResponseDTO> {
    
    @Autowired
    private RoleResponeConverter roleResponeConverter;

    @Override
    public UserInfoResponseDTO toDTO(UserEntity entity) {
        UserInfoResponseDTO userInfoResponseDTO = super.toDTO(entity);
        userInfoResponseDTO.setRoleCodes(roleResponeConverter.toDTOGroup(entity.getRoles()));
        return userInfoResponseDTO;
    }
}
