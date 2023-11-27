package com.chatapp.converter.response.post.survey;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.converter.response.group.GroupResponseConverter;
import com.chatapp.converter.response.post.comment.CommentResponseConverter;
import com.chatapp.converter.response.user.UserInfoResponseConverter;
import com.chatapp.dto.response.image.ImageResponseDTO;
import com.chatapp.dto.response.post.comment.CommentResponeseDTO;
import com.chatapp.dto.response.post.survey.QuestionResponseDTO;
import com.chatapp.dto.response.post.survey.SurveyResponeDTO;
import com.chatapp.dto.response.user.UserInfoResponseDTO;
import com.chatapp.dto.response.user.like.UserLikeResponeDTO;
import com.chatapp.entity.PostCommentEntity;
import com.chatapp.entity.PostEntity;
import com.chatapp.entity.PostImageEntity;
import com.chatapp.entity.PostLikeEntity;
import com.chatapp.entity.QuestionEntity;
import com.chatapp.entity.SurveyPostEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.enums.PostType;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurveyResponeConverter extends BaseConverter<SurveyPostEntity, SurveyResponeDTO> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;
    @Autowired
    private QuestionResponeConverter questionResponeConverter;
    @Autowired
    private CommentResponseConverter commentResponseConverter;
    @Autowired
    private GroupResponseConverter groupResponseConverter;

    @Override
    public SurveyResponeDTO toDTO(SurveyPostEntity entity) {
        SurveyResponeDTO surveyResponeDTO = super.toDTO(entity);
        surveyResponeDTO.setId(entity.getPost().getId());
        surveyResponeDTO.setActive((byte) 1);
        surveyResponeDTO.setStatus((byte) 0);
        surveyResponeDTO.setType(PostType.SURVEY.getName());
        surveyResponeDTO.setTitle(entity.getTitle());
        surveyResponeDTO.setDescription(entity.getDescription());
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
        List<QuestionResponseDTO> questionResponseDTOs = new ArrayList<>();
        for (QuestionEntity questionEntity : entity.getQuestions()) {
            QuestionResponseDTO questionResponseDTO = questionResponeConverter.toDTO(questionEntity);
            questionResponseDTOs.add(questionResponseDTO);
        }
        surveyResponeDTO.setQuestions(questionResponseDTOs);
        surveyResponeDTO.setUser(userInfoResponseDTO);
        if (postEntity.getGroup() != null) {
            surveyResponeDTO.setGroup(groupResponseConverter.toDTO(postEntity.getGroup()));
        }
        else{
            surveyResponeDTO.setGroup(null);
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
        surveyResponeDTO.setLikes(likes);
        List<ImageResponseDTO> images = new ArrayList<>();
        for (PostImageEntity postImageEntity : postEntity.getImages()) {
            ImageResponseDTO imageResponseDTO = new ImageResponseDTO();
            imageResponseDTO.setId(postImageEntity.getId());
            imageResponseDTO.setUri(postImageEntity.getUri());
            images.add(imageResponseDTO);
        }
        surveyResponeDTO.setImages(images);
        List<CommentResponeseDTO> comments = new ArrayList<>();
        for (PostCommentEntity postCommentEntity : postEntity.getComments()) {
            CommentResponeseDTO commentResponeseDTO = commentResponseConverter.toDTO(postCommentEntity);
            comments.add(commentResponeseDTO);
        }
        surveyResponeDTO.setComment(comments);
        surveyResponeDTO.setCommentQuantity(Long.valueOf(comments.size()));
        return surveyResponeDTO;
    }
}