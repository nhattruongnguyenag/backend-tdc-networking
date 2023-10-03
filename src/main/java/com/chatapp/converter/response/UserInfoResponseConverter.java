package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.entity.RoleEntity;
import com.chatapp.entity.UserEntity;

import org.springframework.stereotype.Component;

@Component
public class UserInfoResponseConverter extends BaseConverter<UserEntity, UserInfoResponseDTO> {
    
    @Override
    public UserInfoResponseDTO toDTO(UserEntity entity) {
        UserInfoResponseDTO userInfoResponseDTO = super.toDTO(entity);
        String roleCodes = "";
        if (entity.getRoles().size() > 1) {
            Integer temp = 0;
            for (RoleEntity role : entity.getRoles()) {
                if(temp == entity.getRoles().size() - 1){
                    roleCodes += role.getCode();
                    break;
                }
                roleCodes += role.getCode() + ",";
                temp++;
            }
        }else{
            roleCodes += entity.getRoles().get(0).getCode();
        }
        userInfoResponseDTO.setRoleCodes(roleCodes);
        return userInfoResponseDTO;
    }
}
