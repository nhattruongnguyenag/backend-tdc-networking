package com.chatapp.converter.response.user;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.user.UserMessageResponseDTO;
import com.chatapp.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMessageResponseConverter extends BaseConverter<UserEntity, UserMessageResponseDTO> {
}
