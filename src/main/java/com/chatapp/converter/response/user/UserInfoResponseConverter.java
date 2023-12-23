package com.chatapp.converter.response.user;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.converter.response.user.follow.FollowResponseConverter;
import com.chatapp.dto.response.user.UserInfoResponseDTO;
import com.chatapp.entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoResponseConverter extends BaseConverter<UserEntity, UserInfoResponseDTO> {
    @Autowired
    private FollowResponseConverter followResponseConverter;

    @Override
    public UserInfoResponseDTO toDTO(UserEntity entity) {
        UserInfoResponseDTO userInfoResponseDTO = super.toDTO(entity);
        userInfoResponseDTO.setIsTyping(entity.isTyping() ? (byte) 1 : 0);
        userInfoResponseDTO.setIsMessageConnect(entity.getMessageConnect() ? (byte) 1 : 0);
        String roleCodes = "";
        for (int i = 0; i < entity.getRoles().size(); i++) {
            if (i != 0) {
                roleCodes += ",";
            }
            roleCodes += entity.getRoles().get(i).getCode();
        }
        userInfoResponseDTO.setRoleCodes(roleCodes);
        userInfoResponseDTO.setFollows(followResponseConverter.toDTOGroup(entity.getFollowUsers()));
        return userInfoResponseDTO;
    }
}
