package com.chatapp.converter.response.user.follow;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.user.follow.UserFollowResponseDTO;
import com.chatapp.entity.FollowEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FollowByOtherResponseConverter extends BaseConverter<FollowEntity, UserFollowResponseDTO> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FollowResponseConverter followResponseConverter;

    @Override
    public UserFollowResponseDTO toDTO(FollowEntity entity) {
        UserFollowResponseDTO userFollowResponseDTO = super.toDTO(entity);
        UserEntity userFollowEntity = userRepository.findOneById(entity.getUser().getId());
        userFollowResponseDTO.setId(userFollowEntity.getId());
        userFollowResponseDTO.setImage(userFollowEntity.getImage());
        userFollowResponseDTO.setName(userFollowEntity.getName());
        
        UserEntity user = userRepository.findOneById(entity.getUserFollow().getId());
        for (FollowEntity followEntity : user.getFollowUsers()) {
            if(userFollowEntity.getId() == followEntity.getUserFollow().getId()){
                userFollowResponseDTO.setIsFollow(true);
                return userFollowResponseDTO;
            }
        }
        return userFollowResponseDTO;
    }
}
