package com.chatapp.converter.response.post.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.converter.response.post.PostSearchResponseConverter;
import com.chatapp.dto.response.post.log.PostRejectLogResponseDTO;
import com.chatapp.entity.PostApprovalLogEntity;
import com.chatapp.repository.PostRepository;
import com.chatapp.util.DateTimeUtil;

@Component
public class PostRejectLogResponseConverter extends BaseConverter<PostApprovalLogEntity, PostRejectLogResponseDTO> {
    @Autowired
    PostSearchResponseConverter postSearchResponseConverter;

    @Autowired
    PostRepository postRepository;

    @Override
    public PostRejectLogResponseDTO toDTO(PostApprovalLogEntity postApprovalLogEntity) {
        PostRejectLogResponseDTO dto = super.toDTO(postApprovalLogEntity);
        dto.setCreatedAt(DateTimeUtil.convertToTimestamp(postApprovalLogEntity.getCreatedAt()));
        dto.setPost(postSearchResponseConverter.toDTO(postRepository.findOneById(postApprovalLogEntity.getPost().getId())));
        return dto;
    }
}
