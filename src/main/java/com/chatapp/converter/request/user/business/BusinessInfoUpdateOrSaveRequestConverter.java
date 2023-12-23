package com.chatapp.converter.request.user.business;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.user.business.BusinessInfoUpdateOrSaveRequestDTO;
import com.chatapp.entity.BusinessesInfoEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.BusinessInfoRepository;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessInfoUpdateOrSaveRequestConverter
        extends BaseConverter<UserEntity, BusinessInfoUpdateOrSaveRequestDTO> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusinessInfoRepository businessInfoRepository;

    public UserEntity toUpdateEntity(BusinessInfoUpdateOrSaveRequestDTO dto) {
        UserEntity userEntity = userRepository.findOneById(dto.getId());
        userEntity.setId(dto.getId());
        userEntity.setEmail(dto.getEmail());
        userEntity.setName(dto.getName());
        userEntity.setPhone(dto.getPhone());
        if (dto.getImage() != null) {
            userEntity.setImage(dto.getImage());
        }
        if (dto.getBackground() != null) {
            userEntity.setBackground(dto.getBackground());
        }
        BusinessesInfoEntity businessesInfoEntity = businessInfoRepository.findOneByUser_Id(dto.getId());
        businessesInfoEntity.setRepresentor(dto.getRepresentor());
        businessesInfoEntity.setTaxCode(dto.getTaxCode());
        businessesInfoEntity.setAddress(dto.getAddress());
        businessesInfoEntity.setActiveTime(dto.getActiveTime());
        businessesInfoEntity.setUser(userEntity);
        userEntity.setBusinessesInfos(businessesInfoEntity);
        return userEntity;
    }

    @Override
    public UserEntity toEntity(BusinessInfoUpdateOrSaveRequestDTO dto) {
        UserEntity userEntity = super.toEntity(dto);
        BusinessesInfoEntity businessesInfoEntity = new BusinessesInfoEntity();
        businessesInfoEntity.setRepresentor(dto.getRepresentor());
        businessesInfoEntity.setTaxCode(dto.getTaxCode());
        businessesInfoEntity.setAddress(dto.getAddress());
        businessesInfoEntity.setActiveTime(dto.getActiveTime());
        businessesInfoEntity.setUser(userEntity);
        userEntity.setBusinessesInfos(businessesInfoEntity);
        return userEntity;
    }
}