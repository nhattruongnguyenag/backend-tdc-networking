package com.chatapp.converter.response.user.business;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.converter.response.user.follow.FollowResponseConverter;
import com.chatapp.dto.response.user.business.BusinessInfoResponseDTO;
import com.chatapp.entity.BusinessesInfoEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.UserRepository;
import com.chatapp.util.DateTimeUtil;

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
        businessInfoResponseDTO.setPhone(userEntity.getPhone());
        businessInfoResponseDTO.setEmail(userEntity.getEmail());
        businessInfoResponseDTO.setImage(userEntity.getImage());
        businessInfoResponseDTO.setBackground(userEntity.getBackground());
        businessInfoResponseDTO.setName(userEntity.getName());
        businessInfoResponseDTO.setStatus(userEntity.getStatus());
        businessInfoResponseDTO.setCode(userEntity.getCode());
        if (userEntity.getLastActiveAt() != null) {
            businessInfoResponseDTO.setLastActive(DateTimeUtil.convertToTimestamp(userEntity.getLastActiveAt()));
        }

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