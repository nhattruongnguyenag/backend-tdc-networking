package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.entity.UserEntity;

import org.springframework.stereotype.Component;

@Component
public class UserInfoResponseConverter extends BaseConverter<UserEntity, UserInfoResponseDTO> {
    
    @Override
    public UserInfoResponseDTO toDTO(UserEntity entity) {
        UserInfoResponseDTO userInfoResponseDTO = super.toDTO(entity);
        String roleCodes = "";
        for (int i = 0; i < entity.getRoles().size(); i++) {
            if (i != 0) {
                roleCodes += ",";
            }
            roleCodes += entity.getRoles().get(i).getCode();
        }
        userInfoResponseDTO.setRoleCodes(roleCodes);
        return userInfoResponseDTO;
    }
}
