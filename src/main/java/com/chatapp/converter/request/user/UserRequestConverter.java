package com.chatapp.converter.request.user;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.user.UserDTO;
import com.chatapp.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserRequestConverter extends BaseConverter<UserEntity, UserDTO> {
}
