package com.chatapp.converter.response.post.comment;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.user.UserCommentResponeDTO;
import com.chatapp.entity.UserEntity;

import org.springframework.stereotype.Component;

@Component
public class UserCommentResponseConverter extends BaseConverter<UserEntity, UserCommentResponeDTO> {
}

