package com.chatapp.converter.request.user.follow;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.user.follow.UserFollowRequestDTO;
import com.chatapp.entity.FollowEntity;
import com.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFollowRequestConverter extends BaseConverter<FollowEntity, UserFollowRequestDTO>{

    @Autowired
    private UserRepository userRepository;

    @Override
    public FollowEntity toEntity(UserFollowRequestDTO dto) {
        FollowEntity pFollowEntity = new FollowEntity();
        pFollowEntity.setUser(userRepository.findOneById(dto.getUserId()));
        pFollowEntity.setUserFollow(userRepository.findOneById(dto.getUserFollowId()));
        return pFollowEntity;
    }
}

