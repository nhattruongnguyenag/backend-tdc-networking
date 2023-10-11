package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.CommentResponeseDTO;
import com.chatapp.dto.response.NormalPostResponseDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.entity.NormalPostEntity;
import com.chatapp.entity.PostCommentEntity;
import com.chatapp.entity.PostEntity;
import com.chatapp.entity.PostImageEntity;
import com.chatapp.entity.PostLikeEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.enums.PostType;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.UserRepository;
import java.util.List;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NormalPostResponseConverter extends BaseConverter<NormalPostEntity, NormalPostResponseDTO> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;
    @Autowired
    private CommentResponseConverter commentResponseConverter;

    @Override
    public NormalPostResponseDTO toDTO(NormalPostEntity entity) {
        NormalPostResponseDTO normalPostResponeDTO = super.toDTO(entity);
        normalPostResponeDTO.setId(entity.getPost().getId());
        normalPostResponeDTO.setActive((byte) 1);
        normalPostResponeDTO.setStatus((byte) 0);
        normalPostResponeDTO.setType(PostType.NORMAL.getName());
        PostEntity postEntity = postRepository.findOneById(entity.getPost().getId());
        UserEntity userEntity = userRepository.findOneById(postEntity.getUser().getId());
        UserInfoResponseDTO userInfoResponseDTO = userInfoResponseConverter.toDTO(userEntity);
        String roleCodes = "";
        for (int i = 0; i < userEntity.getRoles().size(); i++) {
            if (i != 0) {
                roleCodes += ",";
            }
            roleCodes += userEntity.getRoles().get(i).getCode();
        }
        userInfoResponseDTO.setRoleCodes(roleCodes);
        normalPostResponeDTO.setUser(userInfoResponseDTO);
        List<String> likes = new ArrayList<>();
        for ( PostLikeEntity postLikeEntity : postEntity.getLikes()) {
            likes.add(userRepository.findById(postLikeEntity.getUser().getId()).get().getEmail());
        }
        normalPostResponeDTO.setLikes(likes);
        List<String> images = new ArrayList<>();
        for ( PostImageEntity postImageEntity : postEntity.getImages()) {
            images.add(postImageEntity.getUri());
        }
        normalPostResponeDTO.setImages(images);
        List<CommentResponeseDTO> comments = new ArrayList<>();
        for ( PostCommentEntity postCommentEntity : postEntity.getComments()) {
            CommentResponeseDTO commentResponeseDTO = commentResponseConverter.toDTO(postCommentEntity);
            comments.add(commentResponeseDTO);
        }
        normalPostResponeDTO.setComment(comments);
        return normalPostResponeDTO;
    }
}
