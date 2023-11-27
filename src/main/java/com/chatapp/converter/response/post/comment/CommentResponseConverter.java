package com.chatapp.converter.response.post.comment;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.post.comment.CommentResponeseDTO;
import com.chatapp.dto.response.post.comment.ParentCommentResponseDTO;
import com.chatapp.dto.response.user.UserCommentResponeDTO;
import com.chatapp.entity.PostCommentEntity;
import com.chatapp.repository.PostCommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentResponseConverter extends BaseConverter<PostCommentEntity,CommentResponeseDTO>{

    @Autowired
    UserCommentResponseConverter userCommentResponseConverter;

    @Autowired
    PostCommentRepository postCommentRepository;
    
    @Override
    public CommentResponeseDTO toDTO(PostCommentEntity entity) {
        CommentResponeseDTO commentResponeseDTO = super.toDTO(entity);
        UserCommentResponeDTO userCommentResponeDTO = userCommentResponseConverter.toDTO(entity.getUser());
        commentResponeseDTO.setUser(userCommentResponeDTO);
        commentResponeseDTO.setPostId(entity.getPost().getId());
        commentResponeseDTO.setChildrens(this.toDTOGroup(entity.getPostComments()));
        if(entity.getParentComment() != null){
            ParentCommentResponseDTO parentCommentResponseDTO = new ParentCommentResponseDTO();
            parentCommentResponseDTO.setParentId(entity.getParentComment().getId());
            parentCommentResponseDTO.setName(postCommentRepository.findOneById(entity.getParentComment().getId()).getUser().getName());
            commentResponeseDTO.setParent(parentCommentResponseDTO);
        }
        else{
            commentResponeseDTO.setParent(null);
        }
        return commentResponeseDTO;
    }
}
  