package com.chatapp.service;

import com.chatapp.dto.request.option.OptionRequestDTO;

public interface OptionService {
    String saveOrUpdateLanguage(OptionRequestDTO optionRequestDTO);
}