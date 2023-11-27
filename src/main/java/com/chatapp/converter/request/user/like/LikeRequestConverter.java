package com.chatapp.converter.request.user.like;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.user.like.LikeRequestDTO;
import com.chatapp.entity.PostLikeEntity;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LikeRequestConverter extends BaseConverter<PostLikeEntity, LikeRequestDTO>{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PostLikeEntity toEntity(LikeRequestDTO dto) {
        PostLikeEntity postLikeEntity = super.toEntity(dto);
        postLikeEntity.setPost(postRepository.findOneById(dto.getPostId()));
        postLikeEntity.setUser(userRepository.findOneById(dto.getUserId()));
        return postLikeEntity;
    }

}
