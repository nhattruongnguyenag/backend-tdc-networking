package com.chatapp.converter;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.UserLikeResponeDTO;
import com.chatapp.entity.PostLikeEntity;
import org.springframework.stereotype.Component;

@Component
public class UserPostLikeConverter extends BaseConverter<PostLikeEntity, UserLikeResponeDTO> {
    @Override
    public UserLikeResponeDTO toDTO(PostLikeEntity postLikeEntity) {
        UserLikeResponeDTO  userLikeResponeDTO = super.toDTO(postLikeEntity);
        userLikeResponeDTO.setId(postLikeEntity.getUser().getId());
        userLikeResponeDTO.setName(postLikeEntity.getUser().getName());
        userLikeResponeDTO.setImage(postLikeEntity.getUser().getImage());
        return userLikeResponeDTO;
    }
}
