package com.chatapp.converter.response.user;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.user.UserFindResponseDTO;
import com.chatapp.entity.UserEntity;

import org.springframework.stereotype.Component;

@Component
public class UserFindResponseConverter extends BaseConverter<UserEntity, UserFindResponseDTO> {

    @Override
    public UserFindResponseDTO toDTO(UserEntity entity) {
        UserFindResponseDTO userInfoResponseDTO = super.toDTO(entity);
        return userInfoResponseDTO;
    }
}
