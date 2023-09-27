package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.StudentInfoResponeDTO;
import com.chatapp.entity.StudentInfoEntity;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentInfoResponeConverter extends BaseConverter<StudentInfoEntity, StudentInfoResponeDTO> {

    @Autowired
    private RoleResponeConverter roleResponeConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public StudentInfoResponeDTO toDTO(StudentInfoEntity entity) {
        StudentInfoResponeDTO studentInfoResponeDTO = super.toDTO(entity);
        studentInfoResponeDTO.getUser().setRoleCodes(roleResponeConverter.toDTOGroup(userRepository.findById(entity.getUser().getId()).get().getRoles()));
        return studentInfoResponeDTO;
    }
}
