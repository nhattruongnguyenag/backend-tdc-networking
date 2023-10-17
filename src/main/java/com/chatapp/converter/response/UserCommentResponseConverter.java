package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.UserCommentResponeDTO;
import com.chatapp.entity.UserEntity;

import org.springframework.stereotype.Component;

@Component
public class UserCommentResponseConverter extends BaseConverter<UserEntity, UserCommentResponeDTO> {
}

