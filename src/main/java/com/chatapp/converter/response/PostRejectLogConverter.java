package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.PostRejectLogDTO;
import com.chatapp.entity.PostApprovalLogEntity;
import com.chatapp.util.DateTimeUtil;
import org.springframework.stereotype.Component;

@Component
public class PostRejectLogConverter extends BaseConverter<PostApprovalLogEntity, PostRejectLogDTO> {
    @Override
    public PostRejectLogDTO toDTO(PostApprovalLogEntity postApprovalLogEntity) {
        PostRejectLogDTO dto = super.toDTO(postApprovalLogEntity);
        dto.setCreatedAt(DateTimeUtil.convertToTimestamp(postApprovalLogEntity.getCreatedAt()));
        return dto;
    }
}
