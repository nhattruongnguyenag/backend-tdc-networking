package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.BusinessInfoResponseDTO;
import com.chatapp.entity.BusinessesInfoEntity;

import org.springframework.stereotype.Component;

@Component
public class BusinessInfoResponeConverter extends BaseConverter<BusinessesInfoEntity, BusinessInfoResponseDTO> {


    @Override
    public BusinessInfoResponseDTO toDTO(BusinessesInfoEntity entity) {
        BusinessInfoResponseDTO businessInfoResponseDTO = super.toDTO(entity);
        String roleCodes = "";
        for (int i = 0; i < entity.getUser().getRoles().size(); i++) {
            if (i != 0) {
                roleCodes += ",";
            }
            roleCodes += entity.getUser().getRoles().get(i).getCode();
        }
        businessInfoResponseDTO.getUser().setRoleCodes(roleCodes);
        return businessInfoResponseDTO;
    }
}