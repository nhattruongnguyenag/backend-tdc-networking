package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.CommentResponeseDTO;
import com.chatapp.dto.response.NormalPostResponseDTO;
import com.chatapp.dto.response.RecruitmentPostResponseDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.entity.NormalPostEntity;
import com.chatapp.entity.PostCommentEntity;
import com.chatapp.entity.PostEntity;
import com.chatapp.entity.PostImageEntity;
import com.chatapp.entity.PostLikeEntity;
import com.chatapp.entity.RecruitmentPostEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.enums.PostType;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecruitmentPostResponseConverter extends BaseConverter<RecruitmentPostEntity, RecruitmentPostResponseDTO> {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;
    @Autowired
    private CommentResponseConverter commentResponseConverter;

    @Override
    public RecruitmentPostResponseDTO toDTO(RecruitmentPostEntity entity) {
        RecruitmentPostResponseDTO recruitmentPostResponseDTO = super.toDTO(entity);
        recruitmentPostResponseDTO.setId(entity.getPost().getId());
        recruitmentPostResponseDTO.setActive((byte) 1);
        recruitmentPostResponseDTO.setStatus((byte) 0);
        recruitmentPostResponseDTO.setType(PostType.RECRUIMENT.getName());
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
        recruitmentPostResponseDTO.setUser(userInfoResponseDTO);
        List<String> likes = new ArrayList<>();
        for ( PostLikeEntity postLikeEntity : postEntity.getLikes()) {
            likes.add(userRepository.findById(postLikeEntity.getUser().getId()).get().getEmail());
        }
        recruitmentPostResponseDTO.setLikes(likes);
        List<String> images = new ArrayList<>();
        for ( PostImageEntity postImageEntity : postEntity.getImages()) {
            images.add(postImageEntity.getUri());
        }
        recruitmentPostResponseDTO.setImages(images);
        List<CommentResponeseDTO> comments = new ArrayList<>();
        for ( PostCommentEntity postCommentEntity : postEntity.getComments()) {
            CommentResponeseDTO commentResponeseDTO = commentResponseConverter.toDTO(postCommentEntity);
            comments.add(commentResponeseDTO);
        }
        recruitmentPostResponseDTO.setComment(comments);
        return recruitmentPostResponseDTO;
    }
}