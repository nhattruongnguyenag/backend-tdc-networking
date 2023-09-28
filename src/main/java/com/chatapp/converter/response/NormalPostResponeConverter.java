package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.NormalPostResponeDTO;
import com.chatapp.entity.NormalPostEntity;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NormalPostResponeConverter extends BaseConverter<NormalPostEntity, NormalPostResponeDTO> {
    @Autowired
    private RoleResponeConverter roleResponeConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public NormalPostResponeDTO toDTO(NormalPostEntity entity) {
        NormalPostResponeDTO normalPostResponeDTO = super.toDTO(entity);
        normalPostResponeDTO.getPost().getUser().setRoleCodes(roleResponeConverter.toDTOGroup(userRepository.findById(entity.getPost().getUser().getId()).get().getRoles()));
        return normalPostResponeDTO;
    }
}
