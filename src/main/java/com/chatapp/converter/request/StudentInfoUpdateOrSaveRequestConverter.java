package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.StudentInfoUpdateOrSaveRequestDTO;
import com.chatapp.entity.StudentInfoEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.StudentInfoRepository;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentInfoUpdateOrSaveRequestConverter extends BaseConverter<UserEntity, StudentInfoUpdateOrSaveRequestDTO>{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentInfoRepository studentInfoRepository;

    public UserEntity toUpdateEntity(StudentInfoUpdateOrSaveRequestDTO dto){
        UserEntity userEntity = userRepository.findOneById(dto.getId());
        userEntity.setId(dto.getId());
        userEntity.setEmail(dto.getEmail());
        userEntity.setName(dto.getName());
        userEntity.setCode(dto.getCode());
        if (dto.getImage() != null) {
            userEntity.setImage(dto.getImage());
        }
        StudentInfoEntity studentInfoEntity = studentInfoRepository.findOneByUser_Id(dto.getId());
        studentInfoEntity.setFacultyName(dto.getFacultyName());
        studentInfoEntity.setMajor(dto.getMajor());
        studentInfoEntity.setStudentCode(dto.getStudentCode());
        studentInfoEntity.setUser(userEntity);
        userEntity.setStudentInfo(studentInfoEntity);
        return userEntity;
    }

    @Override
    public UserEntity toEntity(StudentInfoUpdateOrSaveRequestDTO dto) {
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

