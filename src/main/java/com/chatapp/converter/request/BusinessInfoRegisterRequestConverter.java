package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.BusinessInfoRegisterRequestDTO;
import com.chatapp.entity.BusinessesInfoEntity;
import com.chatapp.entity.UserEntity;

import org.springframework.stereotype.Component;

@Component
public class BusinessInfoRegisterRequestConverter extends BaseConverter<UserEntity, BusinessInfoRegisterRequestDTO>{

    @Override
    public UserEntity toEntity(BusinessInfoRegisterRequestDTO dto) {
        UserEntity userEntity = super.toEntity(dto);
        BusinessesInfoEntity businessesInfoEntity = new BusinessesInfoEntity();
        businessesInfoEntity.setRepresentor(dto.getRepresentor());
        businessesInfoEntity.setTaxCode(dto.getTaxCode());
        businessesInfoEntity.setAddress(dto.getAddress());
        businessesInfoEntity.setActiveTime(dto.getActiveTime());
        businessesInfoEntity.setPhone(dto.getPhone());
        businessesInfoEntity.setUser(userEntity);
        userEntity.setBusinessesInfos(businessesInfoEntity);
        return userEntity;
    }

}  

