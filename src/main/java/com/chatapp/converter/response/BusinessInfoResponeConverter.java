package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.BusinessInfoResponseDTO;
import com.chatapp.entity.BusinessesInfoEntity;
import com.chatapp.entity.RoleEntity;

import org.springframework.stereotype.Component;

@Component
public class BusinessInfoResponeConverter extends BaseConverter<BusinessesInfoEntity, BusinessInfoResponseDTO> {


    @Override
    public BusinessInfoResponseDTO toDTO(BusinessesInfoEntity entity) {
        BusinessInfoResponseDTO businessInfoResponseDTO = super.toDTO(entity);
        String roleCodes = "";
        if (entity.getUser().getRoles().size() > 1) {
            Integer temp = 0;
            for (RoleEntity role : entity.getUser().getRoles()) {
                if(temp == entity.getUser().getRoles().size() - 1){
                    roleCodes += role.getCode();
                    break;
                }
                roleCodes += role.getCode() + ",";
                temp++;
            }
        }else{
            roleCodes += entity.getUser().getRoles().get(0).getCode();
        }
        businessInfoResponseDTO.getUser().setRoleCodes(roleCodes);
        return businessInfoResponseDTO;
    }
}