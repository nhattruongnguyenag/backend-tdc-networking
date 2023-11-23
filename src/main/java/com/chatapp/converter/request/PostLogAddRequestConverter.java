package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.PostLogRequestDTO;
import com.chatapp.entity.PostApprovalLogEntity;
import com.chatapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostLogAddRequestConverter extends BaseConverter<PostApprovalLogEntity, PostLogRequestDTO>{

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostApprovalLogEntity toEntity(PostLogRequestDTO dto) {
        PostApprovalLogEntity entity = super.toEntity(dto);
        entity.setPost(postRepository.findOneById(dto.getPostId()));
        return entity;
    }
}
