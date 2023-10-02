package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.RecruitmentPostResponeDTO;
import com.chatapp.entity.RecruitmentPostEntity;
import com.chatapp.entity.RoleEntity;


import org.springframework.stereotype.Component;

@Component
public class RecruitmentPostResponeConverter extends BaseConverter<RecruitmentPostEntity, RecruitmentPostResponeDTO> {


    @Override
    public RecruitmentPostResponeDTO toDTO(RecruitmentPostEntity entity) {
        RecruitmentPostResponeDTO recruitmentPostResponeDTO = super.toDTO(entity);
        String roleCodes = "";
        if (entity.getPost().getUser().getRoles().size() > 1) {
            Integer temp = 0;
            for (RoleEntity role : entity.getPost().getUser().getRoles()) {
                if(temp == entity.getPost().getUser().getRoles().size() - 1){
                    roleCodes += role.getCode();
                    break;
                }
                roleCodes += role.getCode() + ",";
                temp++;
            }
        }else{
            roleCodes += entity.getPost().getUser().getRoles().get(0).getCode();
        }
        recruitmentPostResponeDTO.getPost().getUser().setRoleCodes(roleCodes);
        return recruitmentPostResponeDTO;
    }
}