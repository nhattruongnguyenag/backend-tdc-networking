package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.StudentInfoResponeDTO;
import com.chatapp.entity.StudentInfoEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentInfoResponeConverter extends BaseConverter<StudentInfoEntity, StudentInfoResponeDTO> {
}
