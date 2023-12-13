package com.chatapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.converter.request.option.OptionRequestConverter;
import com.chatapp.dto.request.option.OptionRequestDTO;
import com.chatapp.dto.response.option.OptionResponseDTO;
import com.chatapp.entity.OptionUserEntity;
import com.chatapp.enums.Notification;
import com.chatapp.repository.OptionUserRepository;
import com.chatapp.service.NotificationService;
import com.chatapp.service.OptionService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OptionServiceImpl implements OptionService {
    @Autowired
    OptionRequestConverter optionRequestConverter;

    @Autowired
    OptionUserRepository optionUserRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public String saveOrUpdateLanguage(OptionRequestDTO optionRequestDTO) {
        OptionUserEntity optionUserEntity = optionRequestConverter.toEntity(optionRequestDTO);
        optionUserRepository.save(optionUserEntity);
        notificationService.addNotification(Notification.USER_CHANGE_LANGUAGE.getValue(),
                Notification.USER_CHANGE_LANGUAGE.getValue(), optionUserEntity.getUser().getId(),
                "",null);
        return "";
    }

    @Override
    public OptionResponseDTO getOptionByKeyAndUserId(OptionRequestDTO optionRequestDTO) {
        OptionUserEntity optionUserEntity = optionUserRepository.findOneByUser_IdAndOptionKey(optionRequestDTO.getUserId(), optionRequestDTO.getOptionKey());
        OptionResponseDTO optionResponseDTO = new OptionResponseDTO();
        optionResponseDTO.setUserId(optionUserEntity.getUser().getId());
        optionResponseDTO.setOptionKey(optionUserEntity.getOptionKey());
        optionResponseDTO.setValue(optionUserEntity.getValue());
        return optionResponseDTO;
    }
}
