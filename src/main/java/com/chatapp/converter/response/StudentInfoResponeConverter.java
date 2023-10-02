package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.StudentInfoResponeDTO;
import com.chatapp.entity.RoleEntity;
import com.chatapp.entity.StudentInfoEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentInfoResponeConverter extends BaseConverter<StudentInfoEntity, StudentInfoResponeDTO> {

    @Override
    public StudentInfoResponeDTO toDTO(StudentInfoEntity entity) {
        StudentInfoResponeDTO studentInfoResponeDTO = super.toDTO(entity);
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
        studentInfoResponeDTO.getUser().setRoleCodes(roleCodes);
        return studentInfoResponeDTO;
    }
}
