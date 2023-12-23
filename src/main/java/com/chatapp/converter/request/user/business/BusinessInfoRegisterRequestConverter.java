package com.chatapp.converter.request.user.business;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.user.business.BusinessInfoRegisterRequestDTO;
import com.chatapp.entity.BusinessesInfoEntity;
import com.chatapp.entity.GroupEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.enums.GroupDefault;
import com.chatapp.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessInfoRegisterRequestConverter extends BaseConverter<UserEntity, BusinessInfoRegisterRequestDTO>{

    @Autowired
    GroupRepository groupRepository;

    @Override
    public UserEntity toEntity(BusinessInfoRegisterRequestDTO dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userEntity.setName(dto.getName());
        if(dto.getImage() != null){
            userEntity.setImage(dto.getImage());
        }
        userEntity.setPassword(dto.getPassword());
        userEntity.setPhone(dto.getPhone());
        userEntity.setStatus((byte)0);
        userEntity.setActive((byte)0);
        userEntity.setCode(dto.getCode());
        List<GroupEntity> groups = new ArrayList<>();
        groups.add(groupRepository.findOneByCode(GroupDefault.GROUP_KET_NOI_DOANH_NGHIEP.getCodeGroup()));
        userEntity.setGroups(groups);
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

