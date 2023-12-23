package com.chatapp.converter.response.user.follow;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.user.follow.UserFollowResponseDTO;
import com.chatapp.entity.FollowEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FollowResponseConverter extends BaseConverter<FollowEntity, UserFollowResponseDTO> {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserFollowResponseDTO toDTO(FollowEntity entity) {
        UserFollowResponseDTO userFollowResponseDTO = super.toDTO(entity);
        UserEntity userEntity = userRepository.findOneById(entity.getUserFollow().getId());
        userFollowResponseDTO.setId(userEntity.getId());
        userFollowResponseDTO.setImage(userEntity.getImage());
        userFollowResponseDTO.setName(userEntity.getName());
        userFollowResponseDTO.setIsFollow(true);
        return userFollowResponseDTO;
    }
}
