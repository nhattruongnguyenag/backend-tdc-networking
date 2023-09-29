package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.RecruitmentPostResponeDTO;
import com.chatapp.entity.RecruitmentPostEntity;
import com.chatapp.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecruitmentPostResponeConverter extends BaseConverter<RecruitmentPostEntity, RecruitmentPostResponeDTO> {
    @Autowired
    private RoleResponeConverter roleResponeConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RecruitmentPostResponeDTO toDTO(RecruitmentPostEntity entity) {
        RecruitmentPostResponeDTO recruitmentPostResponeDTO = super.toDTO(entity);
        recruitmentPostResponeDTO.getPost().getUser().setRoleCodes(roleResponeConverter.toDTOGroup(userRepository.findById(entity.getPost().getUser().getId()).get().getRoles()));
        return recruitmentPostResponeDTO;
    }
}