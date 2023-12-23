package com.chatapp.service;

import com.chatapp.dto.request.option.OptionRequestDTO;
import com.chatapp.dto.response.option.OptionResponseDTO;

public interface OptionService {
    String saveOrUpdateLanguage(OptionRequestDTO optionRequestDTO);
    OptionResponseDTO getOptionByKeyAndUserId(OptionRequestDTO optionRequestDTO);
}