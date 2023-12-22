package com.chatapp.converter.request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatapp.dto.request.ExampleRequestDTO;
import com.chatapp.entity.ExampleEntity;

@Component
public class ExampleRequestConverter {
    @Autowired
    private ModelMapper modelMapper;

    public ExampleEntity toEntity(ExampleRequestDTO requestDTO) {
        Type entityClass = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        ExampleEntity entity = modelMapper.map(requestDTO, entityClass);
        return entity;
    }
}
