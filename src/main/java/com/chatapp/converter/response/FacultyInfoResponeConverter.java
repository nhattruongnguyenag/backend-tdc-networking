package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.FacultyInfoResponeDTO;
import com.chatapp.entity.FacultyInfoEntity;
import com.chatapp.entity.RoleEntity;

import org.springframework.stereotype.Component;

@Component
public class FacultyInfoResponeConverter extends BaseConverter<FacultyInfoEntity, FacultyInfoResponeDTO> {


    @Override
    public FacultyInfoResponeDTO toDTO(FacultyInfoEntity entity) {
        FacultyInfoResponeDTO facultyInfoResponeDTO = super.toDTO(entity);
        String roleCodes = "";
        if (entity.getUser().getRoles().size() > 1) {
            Integer temp = 0;
            for (RoleEntity role : entity.getUser().getRoles()) {
                if(temp == entity.getUser().getRoles().size() - 1){
                    roleCodes += role.getCode();
                    break;
                }
                roleCodes += role.getCode() + ",";
                temp++;
            }
        }else{
            roleCodes += entity.getUser().getRoles().get(0).getCode();
        }
        facultyInfoResponeDTO.getUser().setRoleCodes(roleCodes);
        return facultyInfoResponeDTO;
    }
}