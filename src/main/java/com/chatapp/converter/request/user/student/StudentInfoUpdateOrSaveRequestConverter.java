package com.chatapp.converter.request.user.student;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.user.student.StudentInfoUpdateOrSaveRequestDTO;
import com.chatapp.entity.StudentInfoEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.FacultyInfoRepository;
import com.chatapp.repository.MajorRepository;
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

    @Autowired
    private FacultyInfoRepository facultyInfoRepository;

    @Autowired
    private  MajorRepository majorRepository;

    public UserEntity toUpdateEntity(StudentInfoUpdateOrSaveRequestDTO dto){
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
        StudentInfoEntity studentInfoEntity = studentInfoRepository.findOneByUser_Id(dto.getId());
        studentInfoEntity.setStudentCode(dto.getStudentCode());
        studentInfoEntity.setUser(userEntity);
        userEntity.setStudentInfo(studentInfoEntity);
        return userEntity;
    }

    @Override
    public UserEntity toEntity(StudentInfoUpdateOrSaveRequestDTO dto) {
        UserEntity userEntity = super.toEntity(dto);
        StudentInfoEntity studentInfoEntity = new StudentInfoEntity();
        studentInfoEntity.setFaculty(facultyInfoRepository.findOneById(dto.getFacultyId()));
        studentInfoEntity.setMajor(majorRepository.findOneById(dto.getMajorId()));
        studentInfoEntity.setStudentCode(dto.getStudentCode());
        studentInfoEntity.setUser(userEntity);
        userEntity.setStudentInfo(studentInfoEntity);
        return userEntity;
    }
}

