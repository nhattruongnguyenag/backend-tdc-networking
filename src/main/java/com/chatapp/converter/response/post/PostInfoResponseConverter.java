package com.chatapp.converter.response.post;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.converter.response.group.GroupResponseConverter;
import com.chatapp.converter.response.user.UserInfoResponseConverter;
import com.chatapp.dto.response.post.PostInfoResponseDTO;
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
        if (entity.getGroup() != null) {
            postInfoResponeDTO.setGroup(groupResponseConverter.toDTO(entity.getGroup()));
        }
        else{
            postInfoResponeDTO.setGroup(null);
        }
        return postInfoResponeDTO;
    }
}
