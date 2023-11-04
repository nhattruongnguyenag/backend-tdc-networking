package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.StudentInfoRegisterRequestDTO;
import com.chatapp.entity.StudentInfoEntity;
import com.chatapp.entity.UserEntity;


import org.springframework.stereotype.Component;

@Component
public class StudentInfoRegisterRequestConverter extends BaseConverter<UserEntity, StudentInfoRegisterRequestDTO>{
    public UserEntity toEntity(StudentInfoRegisterRequestDTO dto) {
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
