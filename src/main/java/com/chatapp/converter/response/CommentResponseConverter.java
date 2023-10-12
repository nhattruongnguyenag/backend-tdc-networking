package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.CommentResponeseDTO;
import com.chatapp.entity.PostCommentEntity;

import org.springframework.stereotype.Component;

@Component
public class CommentResponseConverter extends BaseConverter<PostCommentEntity, CommentResponeseDTO> {

    @Override
    public CommentResponeseDTO toDTO(PostCommentEntity entity) {
        CommentResponeseDTO commentResponeseDTO = super.toDTO(entity);
        commentResponeseDTO.setUser(entity.getUser().getName());
        commentResponeseDTO.setPostId(entity.getPost().getId());
        if (entity.getPostComment() != null) {
            commentResponeseDTO.setParent(this.toDTO(entity.getPostComment()));
        } else {
            commentResponeseDTO.setParent(null);
        }
        return commentResponeseDTO;
    }
}
