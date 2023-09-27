package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.FacultyInfoResponeDTO;
import com.chatapp.dto.response.StudentInfoResponeDTO;
import com.chatapp.entity.FacultyInfoEntity;
import com.chatapp.entity.StudentInfoEntity;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacultyInfoResponeConverter extends BaseConverter<FacultyInfoEntity, FacultyInfoResponeDTO> {

    @Autowired
    private RoleResponeConverter roleResponeConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public FacultyInfoResponeDTO toDTO(FacultyInfoEntity entity) {
        FacultyInfoResponeDTO facultyInfoResponeDTO = super.toDTO(entity);
        facultyInfoResponeDTO.getUser().setRoleCodes(roleResponeConverter.toDTOGroup(userRepository.findById(entity.getUser().getId()).get().getRoles()));
        return facultyInfoResponeDTO;
    }
}