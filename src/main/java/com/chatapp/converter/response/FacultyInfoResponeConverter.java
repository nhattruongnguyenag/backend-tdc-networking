package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.FacultyInfoResponeDTO;
import com.chatapp.entity.FacultyInfoEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacultyInfoResponeConverter extends BaseConverter<FacultyInfoEntity, FacultyInfoResponeDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public FacultyInfoResponeDTO toDTO(FacultyInfoEntity entity) {
        FacultyInfoResponeDTO facultyInfoResponeDTO = super.toDTO(entity);
        UserEntity userEntity = userRepository.findOneById(entity.getUser().getId());
        facultyInfoResponeDTO.setId(userEntity.getId());
        facultyInfoResponeDTO.setEmail(userEntity.getEmail());
        facultyInfoResponeDTO.setImage(userEntity.getImage());
        facultyInfoResponeDTO.setName(userEntity.getName());
        facultyInfoResponeDTO.setStatus(userEntity.getStatus());
        facultyInfoResponeDTO.setCode(userEntity.getCode());
        String roleCodes = "";
        for (int i = 0; i < entity.getUser().getRoles().size(); i++) {
            if (i != 0) {
                roleCodes += ",";
            }
            roleCodes += entity.getUser().getRoles().get(i).getCode();
        }
        facultyInfoResponeDTO.setRoleCodes(roleCodes);
        return facultyInfoResponeDTO;
    }
}