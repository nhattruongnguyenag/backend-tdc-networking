package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.UserFindResponseDTO;
import com.chatapp.entity.FollowEntity;
import com.chatapp.entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFindResponseConverter extends BaseConverter<UserEntity, UserFindResponseDTO> {

    @Override
    public UserFindResponseDTO toDTO(UserEntity entity) {
        UserFindResponseDTO userInfoResponseDTO = super.toDTO(entity);
        return userInfoResponseDTO;
    }
}
