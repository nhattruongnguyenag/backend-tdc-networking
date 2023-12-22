package com.chatapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.converter.request.ExampleRequestConverter;
import com.chatapp.dto.request.ExampleRequestDTO;
import com.chatapp.entity.ExampleEntity;
import com.chatapp.repository.ExampleRepository;
import com.chatapp.service.ExampleService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ExampleServiceImpl implements ExampleService{

    @Autowired
    ExampleRequestConverter exampleRequestConverter;

    @Autowired
    ExampleRepository exampleRepository;

    @Override
    public String save(ExampleRequestDTO request) {
        ExampleEntity entity = exampleRequestConverter.toEntity(request);
        if(exampleRepository.save(entity) != null){
            return "save success";
        }
        return "save failed";
    }
    
}
