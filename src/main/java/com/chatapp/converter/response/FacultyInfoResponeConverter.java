package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.FacultyInfoResponeDTO;
import com.chatapp.entity.FacultyInfoEntity;

import org.springframework.stereotype.Component;

@Component
public class FacultyInfoResponeConverter extends BaseConverter<FacultyInfoEntity, FacultyInfoResponeDTO> {


    @Override
    public FacultyInfoResponeDTO toDTO(FacultyInfoEntity entity) {
        FacultyInfoResponeDTO facultyInfoResponeDTO = super.toDTO(entity);
        String roleCodes = "";
        for (int i = 0; i < entity.getUser().getRoles().size(); i++) {
            if (i != 0) {
                roleCodes += ",";
            }
            roleCodes += entity.getUser().getRoles().get(i).getCode();
        }
        facultyInfoResponeDTO.getUser().setRoleCodes(roleCodes);
        return facultyInfoResponeDTO;
    }
}