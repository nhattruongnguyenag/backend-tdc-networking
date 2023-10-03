package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.NormalPostResponeDTO;
import com.chatapp.entity.NormalPostEntity;

import org.springframework.stereotype.Component;

@Component
public class NormalPostResponeConverter extends BaseConverter<NormalPostEntity, NormalPostResponeDTO> {


    @Override
    public NormalPostResponeDTO toDTO(NormalPostEntity entity) {
        NormalPostResponeDTO normalPostResponeDTO = super.toDTO(entity);
        String roleCodes = "";
        for (int i = 0; i < entity.getPost().getUser().getRoles().size(); i++) {
            if (i != 0) {
                roleCodes += ",";
            }
            roleCodes += entity.getPost().getUser().getRoles().get(i).getCode();
        }
        normalPostResponeDTO.getPost().getUser().setRoleCodes(roleCodes);
        return normalPostResponeDTO;
    }
}
