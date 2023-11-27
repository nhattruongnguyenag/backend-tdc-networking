package com.chatapp.converter.request.user.student;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.user.student.StudentInfoRegisterRequestDTO;
import com.chatapp.entity.StudentInfoEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.FacultyInfoRepository;
import com.chatapp.repository.MajorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentInfoRegisterRequestConverter extends BaseConverter<UserEntity, StudentInfoRegisterRequestDTO> {

    @Autowired
    private FacultyInfoRepository facultyInfoRepository;

    @Autowired
    private MajorRepository majorRepository;

    public UserEntity toEntity(StudentInfoRegisterRequestDTO dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userEntity.setName(dto.getName());
        userEntity.setPassword(dto.getPassword());
        userEntity.setPhone(dto.getPhone());
        userEntity.setStatus((byte)0);
        userEntity.setActive((byte)0);
        userEntity.setCode(dto.getCode());
        if (dto.getBackground() != null) {
            userEntity.setBackground(dto.getBackground());
        }
        if (dto.getImage() != null) {
            userEntity.setImage(dto.getImage());
        }
        StudentInfoEntity studentInfoEntity = new StudentInfoEntity();
        studentInfoEntity.setFaculty(facultyInfoRepository.findOneById(Long.valueOf(dto.getFacultyId())));
        studentInfoEntity.setMajor(majorRepository.findOneById(Long.valueOf(dto.getMajorId())));
        studentInfoEntity.setStudentCode(dto.getStudentCode());
        studentInfoEntity.setUser(userEntity);
        userEntity.setStudentInfo(studentInfoEntity);
        return userEntity;
    }
}
