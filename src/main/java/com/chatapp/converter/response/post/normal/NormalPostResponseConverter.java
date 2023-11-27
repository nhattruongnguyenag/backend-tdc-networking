package com.chatapp.converter.response.post.normal;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.converter.response.group.GroupResponseConverter;
import com.chatapp.converter.response.post.comment.CommentResponseConverter;
import com.chatapp.converter.response.user.UserInfoResponseConverter;
import com.chatapp.dto.response.image.ImageResponseDTO;
import com.chatapp.dto.response.post.comment.CommentResponeseDTO;
import com.chatapp.dto.response.post.normal.NormalPostResponseDTO;
import com.chatapp.dto.response.user.UserInfoResponseDTO;
import com.chatapp.dto.response.user.like.UserLikeResponeDTO;
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
    @Autowired
    private GroupResponseConverter groupResponseConverter;

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
        if (postEntity.getGroup() != null) {
            normalPostResponeDTO.setGroup(groupResponseConverter.toDTO(postEntity.getGroup()));
        }
        else{
            normalPostResponeDTO.setGroup(null);
        }
        List<UserLikeResponeDTO> likes = new ArrayList<>();
        for (PostLikeEntity postLikeEntity : postEntity.getLikes()) {
            UserLikeResponeDTO userLikeResponeDTO = new UserLikeResponeDTO();
            UserEntity uEntity = userRepository.findOneById(postLikeEntity.getUser().getId());
            userLikeResponeDTO.setId(uEntity.getId());
            userLikeResponeDTO.setName(uEntity.getName());
            userLikeResponeDTO.setImage(uEntity.getImage());
            likes.add(userLikeResponeDTO);
        }
        normalPostResponeDTO.setLikes(likes);
        List<ImageResponseDTO> images = new ArrayList<>();
        for (PostImageEntity postImageEntity : postEntity.getImages()) {
            ImageResponseDTO imageResponseDTO = new ImageResponseDTO();
            imageResponseDTO.setId(postImageEntity.getId());
            imageResponseDTO.setUri(postImageEntity.getUri());
            images.add(imageResponseDTO);
        }
        normalPostResponeDTO.setImages(images);
        List<CommentResponeseDTO> comments = new ArrayList<>();
        for (PostCommentEntity postCommentEntity : postEntity.getComments()) {
            CommentResponeseDTO commentResponeseDTO = commentResponseConverter.toDTO(postCommentEntity);
            comments.add(commentResponeseDTO);
        }
        normalPostResponeDTO.setComment(comments);
        normalPostResponeDTO.setCommentQuantity(Long.valueOf(comments.size()));
        
        return normalPostResponeDTO;
    }
}
