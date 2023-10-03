package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.UserTokenResponeDTO;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.BusinessInfoRepository;
import com.chatapp.repository.FacultyInfoRepository;
import com.chatapp.repository.StudentInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTokenResponeConverter extends BaseConverter<UserEntity, UserTokenResponeDTO> {

    @Autowired
    private StudentInfoRepository studentInfoRepository;

    @Autowired
    private FacultyInfoRepository facultyInfoRepository;

    @Autowired
    private BusinessInfoRepository businessInfoRepository;

    @Override
    public UserTokenResponeDTO toDTO(UserEntity entity) {
        UserTokenResponeDTO dto = super.toDTO(entity);
        
        return dto;
    }
}
