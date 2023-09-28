package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.BusinessInfoResponseDTO;
import com.chatapp.entity.BusinessesInfoEntity;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessInfoResponeConverter extends BaseConverter<BusinessesInfoEntity, BusinessInfoResponseDTO> {

    @Autowired
    private RoleResponeConverter roleResponeConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BusinessInfoResponseDTO toDTO(BusinessesInfoEntity entity) {
        BusinessInfoResponseDTO businessInfoResponseDTO = super.toDTO(entity);
        businessInfoResponseDTO.getUser().setRoleCodes(roleResponeConverter.toDTOGroup(userRepository.findById(entity.getUser().getId()).get().getRoles()));
        return businessInfoResponseDTO;
    }
}