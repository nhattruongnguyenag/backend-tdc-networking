package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.FalcutyInfoResponeDTO;
import com.chatapp.dto.response.StudentInfoResponeDTO;
import com.chatapp.entity.FalcutyInfoEntity;
import com.chatapp.entity.StudentInfoEntity;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FalcutyInfoResponeConverter extends BaseConverter<FalcutyInfoEntity, FalcutyInfoResponeDTO> {

    @Autowired
    private RoleResponeConverter roleResponeConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public FalcutyInfoResponeDTO toDTO(FalcutyInfoEntity entity) {
        FalcutyInfoResponeDTO falcutyInfoResponeDTO = super.toDTO(entity);
        falcutyInfoResponeDTO.getUser().setRoleCodes(roleResponeConverter.toDTOGroup(userRepository.findById(entity.getUser().getId()).get().getRoles()));
        return falcutyInfoResponeDTO;
    }
}