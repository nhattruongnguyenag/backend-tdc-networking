package com.chatapp.converter.response.post.log;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.converter.response.group.GroupResponseConverter;
import com.chatapp.dto.response.group.GroupResponseDTO;
import com.chatapp.dto.response.post.log.PostRejectLogDTO;
import com.chatapp.entity.PostApprovalLogEntity;
import com.chatapp.repository.GroupRepository;
import com.chatapp.util.DateTimeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostRejectLogConverter extends BaseConverter<PostApprovalLogEntity, PostRejectLogDTO> {
    @Autowired
    GroupResponseConverter groupResponseConverter;

    @Autowired
    GroupRepository groupRepository;

    @Override
    public PostRejectLogDTO toDTO(PostApprovalLogEntity postApprovalLogEntity) {
        PostRejectLogDTO dto = super.toDTO(postApprovalLogEntity);
        dto.setCreatedAt(DateTimeUtil.convertToTimestamp(postApprovalLogEntity.getCreatedAt()));
        GroupResponseDTO groupResponseDTO = groupResponseConverter.toDTO(groupRepository.findOneById(postApprovalLogEntity.getPost().getGroup().getId()));
        dto.setGroup(groupResponseDTO);
        return dto;
    }
}
