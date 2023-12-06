package com.chatapp.converter.request.option;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.option.OptionRequestDTO;
import com.chatapp.entity.OptionUserEntity;
import com.chatapp.repository.OptionUserRepository;
import com.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OptionRequestConverter extends BaseConverter<OptionUserEntity, OptionRequestDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OptionUserRepository optionUserRepository;

    @Override
    public OptionUserEntity toEntity(OptionRequestDTO dto) {
        OptionUserEntity optionUserEntity = null;
        if (optionUserRepository.findOneByUser_IdAndOptionKey(dto.getUserId(), dto.getOptionKey()) != null) {
            optionUserEntity = optionUserRepository.findOneByUser_IdAndOptionKey(dto.getUserId(),
                    dto.getOptionKey());
            optionUserEntity.setValue(dto.getValue());
        } else {
            optionUserEntity = super.toEntity(dto);
            optionUserEntity.setUser(userRepository.findOneById(dto.getUserId()));
        }
        return optionUserEntity;
    }
}
