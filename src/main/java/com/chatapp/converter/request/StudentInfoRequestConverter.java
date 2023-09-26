package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.StudentRegisterRequestDTO;
import com.chatapp.entity.StudentInfoEntity;
import com.chatapp.entity.UserEntity;


import org.springframework.stereotype.Component;

@Component
public class StudentInfoRequestConverter extends BaseConverter<UserEntity, StudentRegisterRequestDTO>{

    @Override
    public UserEntity toEntity(StudentRegisterRequestDTO dto) {
        UserEntity userEntity = super.toEntity(dto);
        StudentInfoEntity studentInfoEntity = new StudentInfoEntity();
        studentInfoEntity.setFacultyName(dto.getFacultyName());
        studentInfoEntity.setMajor(dto.getMajor());
        studentInfoEntity.setStudentCode(dto.getStudentCode());
        studentInfoEntity.setUser(userEntity);
        userEntity.setStudentInfo(studentInfoEntity);
        return userEntity;
    }

}
