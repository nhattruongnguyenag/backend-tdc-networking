package com.chatapp.converter.response.user;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.user.UserFindResponseDTO;
import com.chatapp.entity.UserEntity;

import org.springframework.stereotype.Component;

@Component
public class UserFindResponseConverter extends BaseConverter<UserEntity, UserFindResponseDTO> {

    @Override
    public UserFindResponseDTO toDTO(UserEntity entity) {
        UserFindResponseDTO userInfoResponseDTO = new UserFindResponseDTO();
        userInfoResponseDTO.setId(entity.getId());
        userInfoResponseDTO.setImage(entity.getImage());
        userInfoResponseDTO.setName(entity.getName());
        return userInfoResponseDTO;
    }
}
