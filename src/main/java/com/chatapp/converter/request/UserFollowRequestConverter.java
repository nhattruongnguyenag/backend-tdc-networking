package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.UserFollowRequestDTO;
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
        FollowEntity pFollowEntity = super.toEntity(dto);
        pFollowEntity.setUser(userRepository.findOneById(dto.getUserId()));
        pFollowEntity.setUserFollow(userRepository.findOneById(dto.getUserFollowId()));
        return pFollowEntity;
    }
}

