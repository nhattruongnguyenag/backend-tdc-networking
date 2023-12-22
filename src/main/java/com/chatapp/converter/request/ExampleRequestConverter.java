package com.chatapp.converter.request;

import org.springframework.stereotype.Component;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.ExampleRequestDTO;
import com.chatapp.entity.ExampleEntity;

@Component
public class ExampleRequestConverter extends BaseConverter<ExampleEntity , ExampleRequestDTO> {
    
}
