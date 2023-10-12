package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.CommentResponeseDTO;
import com.chatapp.dto.response.UserCommentResponeDTO;
import com.chatapp.entity.PostCommentEntity;

import org.springframework.stereotype.Component;

@Component
public class CommentResponseConverter extends BaseConverter<PostCommentEntity,CommentResponeseDTO>{

    @Override
    public CommentResponeseDTO toDTO(PostCommentEntity entity) {
        CommentResponeseDTO commentResponeseDTO = super.toDTO(entity);
        UserCommentResponeDTO userCommentResponeDTO = new UserCommentResponeDTO();
        userCommentResponeDTO.setImage(entity.getUser().getImage());
        userCommentResponeDTO.setName(entity.getUser().getName());
        commentResponeseDTO.setUser(userCommentResponeDTO);
        commentResponeseDTO.setPostId(entity.getPost().getId());
        commentResponeseDTO.setChildrens(this.toDTOGroup(entity.getPostComments()));
        if(entity.getPostComment() != null){
            commentResponeseDTO.setParentId(entity.getPostComment().getId());
        }
        else{
            commentResponeseDTO.setParentId(null);
        }
        return commentResponeseDTO;
    }
}
  