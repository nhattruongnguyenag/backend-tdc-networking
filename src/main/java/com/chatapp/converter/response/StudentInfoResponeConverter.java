package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.StudentInfoResponeDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.entity.StudentInfoEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentInfoResponeConverter extends BaseConverter<StudentInfoEntity, StudentInfoResponeDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public StudentInfoResponeDTO toDTO(StudentInfoEntity entity) {
        StudentInfoResponeDTO studentInfoResponeDTO = super.toDTO(entity);
        UserEntity userEntity = userRepository.findOneById(entity.getUser().getId());
        studentInfoResponeDTO.setId(userEntity.getId());
        studentInfoResponeDTO.setEmail(userEntity.getEmail());
        studentInfoResponeDTO.setImage(userEntity.getImage());
        studentInfoResponeDTO.setName(userEntity.getName());
        studentInfoResponeDTO.setStatus(userEntity.getStatus());
        String roleCodes = "";
        for (int i = 0; i < entity.getUser().getRoles().size(); i++) {
            if (i != 0) {
                roleCodes += ",";
            }
            roleCodes += entity.getUser().getRoles().get(i).getCode();
        }
        studentInfoResponeDTO.setRoleCodes(roleCodes);
        return studentInfoResponeDTO;
    }
}
