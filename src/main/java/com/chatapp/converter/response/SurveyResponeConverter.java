package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.CommentResponeseDTO;
import com.chatapp.dto.response.NormalPostResponseDTO;
import com.chatapp.dto.response.QuestionResponseDTO;
import com.chatapp.dto.response.SurveyResponeDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.entity.NormalPostEntity;
import com.chatapp.entity.PostCommentEntity;
import com.chatapp.entity.PostEntity;
import com.chatapp.entity.PostImageEntity;
import com.chatapp.entity.PostLikeEntity;
import com.chatapp.entity.QuestionEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.enums.PostType;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.QuestionRepository;
import com.chatapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurveyResponeConverter extends BaseConverter<List<QuestionEntity>, SurveyResponeDTO> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;
    @Autowired
    private QuestionResponeConverter questionResponeConverter;
    @Autowired
    private CommentResponseConverter commentResponseConverter;

    @Override
    public SurveyResponeDTO toDTO(List<QuestionEntity> questions) {
        SurveyResponeDTO surveyResponeDTO = super.toDTO(questions);
        surveyResponeDTO.setId(questions.get(0).getPost().getId());
        surveyResponeDTO.setActive((byte) 1);
        surveyResponeDTO.setStatus((byte) 0);
        surveyResponeDTO.setType(PostType.SURVEY.getName());
        PostEntity postEntity = postRepository.findOneById(questions.get(0).getPost().getId());
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
        for (QuestionEntity questionEntity : questions) {
            QuestionResponseDTO questionResponseDTO = questionResponeConverter.toDTO(questionEntity);
            questionResponseDTOs.add(questionResponseDTO);
        }
        surveyResponeDTO.setQuestions(questionResponseDTOs);
        surveyResponeDTO.setUser(userInfoResponseDTO);
        List<String> likes = new ArrayList<>();
        for ( PostLikeEntity postLikeEntity : postEntity.getLikes()) {
            likes.add(userRepository.findById(postLikeEntity.getUser().getId()).get().getEmail());
        }
        surveyResponeDTO.setLikes(likes);
        List<String> images = new ArrayList<>();
        for ( PostImageEntity postImageEntity : postEntity.getImages()) {
            images.add(postImageEntity.getUri());
        }
        surveyResponeDTO.setImages(images);
        List<CommentResponeseDTO> comments = new ArrayList<>();
        for ( PostCommentEntity postCommentEntity : postEntity.getComments()) {
            CommentResponeseDTO commentResponeseDTO = commentResponseConverter.toDTO(postCommentEntity);
            comments.add(commentResponeseDTO);
        }
        surveyResponeDTO.setComment(comments);
        return surveyResponeDTO;
    }
}