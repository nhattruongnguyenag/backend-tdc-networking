package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.CommentSaveRequestDTO;
import com.chatapp.entity.PostCommentEntity;
import com.chatapp.repository.PostCommentRepository;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentSaveRequestConverter extends BaseConverter<PostCommentEntity, CommentSaveRequestDTO>{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;

    @Override
    public PostCommentEntity toEntity(CommentSaveRequestDTO dto) {
        PostCommentEntity postCommentEntity = new PostCommentEntity();
        postCommentEntity.setContent(dto.getContent());
        postCommentEntity.setUser(userRepository.findOneById(dto.getUserId()));
        postCommentEntity.setPost(postRepository.findOneById(dto.getPostId()));
        if(dto.getParentCommentId() != 0){
            postCommentEntity.setPostComment(postCommentRepository.findOneById(dto.getParentCommentId()));
        }
        return postCommentEntity;
    }
}
