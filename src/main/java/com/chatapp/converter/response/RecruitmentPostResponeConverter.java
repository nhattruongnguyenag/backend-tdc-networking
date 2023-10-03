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
        for (int i = 0; i < entity.getPost().getUser().getRoles().size(); i++) {
            if (i != 0) {
                roleCodes += ",";
            }
            roleCodes += entity.getPost().getUser().getRoles().get(i).getCode();
        }
        recruitmentPostResponeDTO.getPost().getUser().setRoleCodes(roleCodes);
        return recruitmentPostResponeDTO;
    }
}