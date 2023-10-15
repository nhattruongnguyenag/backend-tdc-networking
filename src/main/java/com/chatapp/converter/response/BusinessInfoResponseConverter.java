package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.BusinessInfoResponseDTO;
import com.chatapp.entity.BusinessesInfoEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessInfoResponseConverter extends BaseConverter<BusinessesInfoEntity, BusinessInfoResponseDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowResponseConverter followResponseConverter;

    @Override
    public BusinessInfoResponseDTO toDTO(BusinessesInfoEntity entity) {
        BusinessInfoResponseDTO businessInfoResponseDTO = super.toDTO(entity);
        UserEntity userEntity = userRepository.findOneById(entity.getUser().getId());
        businessInfoResponseDTO.setId(userEntity.getId());
        businessInfoResponseDTO.setEmail(userEntity.getEmail());
        businessInfoResponseDTO.setImage(userEntity.getImage());
        businessInfoResponseDTO.setName(userEntity.getName());
        businessInfoResponseDTO.setStatus(userEntity.getStatus());
        businessInfoResponseDTO.setCode(userEntity.getCode());
        String roleCodes = "";
        for (int i = 0; i < entity.getUser().getRoles().size(); i++) {
            if (i != 0) {
                roleCodes += ",";
            }
            roleCodes += entity.getUser().getRoles().get(i).getCode();
        }
        businessInfoResponseDTO.setRoleCodes(roleCodes);
        businessInfoResponseDTO.setFollows(followResponseConverter.toDTOGroup(userEntity.getFollowUsers()));
        return businessInfoResponseDTO;
    }
}