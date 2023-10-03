package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.StudentInfoResponeDTO;
import com.chatapp.entity.StudentInfoEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentInfoResponeConverter extends BaseConverter<StudentInfoEntity, StudentInfoResponeDTO> {

    @Override
    public StudentInfoResponeDTO toDTO(StudentInfoEntity entity) {
        StudentInfoResponeDTO studentInfoResponeDTO = super.toDTO(entity);
        String roleCodes = "";
        for (int i = 0; i < entity.getUser().getRoles().size(); i++) {
            if (i != 0) {
                roleCodes += ",";
            }
            roleCodes += entity.getUser().getRoles().get(i).getCode();
        }
        studentInfoResponeDTO.getUser().setRoleCodes(roleCodes);
        return studentInfoResponeDTO;
    }
}
