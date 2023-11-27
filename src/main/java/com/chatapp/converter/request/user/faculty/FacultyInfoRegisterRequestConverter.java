package com.chatapp.converter.request.user.faculty;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.user.faculty.FacultyInfoRegisterRequestDTO;
import com.chatapp.entity.FacultyInfoEntity;
import com.chatapp.entity.UserEntity;

import org.springframework.stereotype.Component;

@Component
public class FacultyInfoRegisterRequestConverter extends BaseConverter<UserEntity, FacultyInfoRegisterRequestDTO>{

    @Override
    public UserEntity toEntity(FacultyInfoRegisterRequestDTO dto) {
        UserEntity userEntity = super.toEntity(dto);
        FacultyInfoEntity facultyInfoEntity = new FacultyInfoEntity();
        facultyInfoEntity.setUser(userEntity);
        userEntity.setFalcutyInfo(facultyInfoEntity);
        return userEntity;
    }

}  
