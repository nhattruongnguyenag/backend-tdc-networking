package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.GroupResponseDTO;
import com.chatapp.dto.response.PostInfoResponseDTO;
import com.chatapp.entity.PostEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostInfoResponseConverter extends BaseConverter<PostEntity, PostInfoResponseDTO> {

    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;

    @Autowired
    private GroupResponseConverter groupResponseConverter;

    @Override
    public PostInfoResponseDTO toDTO(PostEntity entity) {
        PostInfoResponseDTO postInfoResponeDTO = super.toDTO(entity);
        postInfoResponeDTO.setUser(userInfoResponseConverter.toDTO(entity.getUser()));
        postInfoResponeDTO.setGroup(groupResponseConverter.toDTO(entity.getGroup()));
        return postInfoResponeDTO;
    }

}
