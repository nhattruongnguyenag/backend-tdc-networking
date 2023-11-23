package com.chatapp.converter.response.post;

import com.chatapp.converter.UserPostLikeConverter;
import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.converter.response.*;
import com.chatapp.dto.response.*;
import com.chatapp.dto.response.postSearch.NormalPostSearchResponseDTO;
import com.chatapp.dto.response.postSearch.PostSearchResponseDTO;
import com.chatapp.dto.response.postSearch.RecruitmentPostSearchResponseDTO;
import com.chatapp.dto.response.postSearch.SurveyPostSearchResponseDTO;
import com.chatapp.entity.PostEntity;
import com.chatapp.entity.QuestionEntity;
import com.chatapp.enums.PostType;
import com.chatapp.util.DateTimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostSearchResponseConverter extends BaseConverter<PostEntity, PostSearchResponseDTO> {
    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;

    @Autowired
    private GroupResponseConverter groupResponseConverter;

    @Autowired
    private RecruitmentPostResponseConverter recruitmentPostResponseConverter;

    @Autowired
    private CommentResponseConverter commentResponseConverter;

    @Autowired
    private UserPostLikeConverter userPostLikeConverter;

    @Autowired
    private QuestionResponeConverter questionResponeConverter;

    @Override
    public PostSearchResponseDTO toDTO(PostEntity entity) {
        PostSearchResponseDTO postInfoResponeDTO = super.toDTO(entity);

        UserInfoResponseDTO userInfoResponseDTO = userInfoResponseConverter.toDTO(entity.getUser());

        postInfoResponeDTO.setUser(userInfoResponseDTO);
        postInfoResponeDTO.setGroup(entity.getGroup() != null ? groupResponseConverter.toDTO(entity.getGroup()) : null);
        postInfoResponeDTO.setLikes(userPostLikeConverter.toDTOGroup(entity.getLikes()));
        postInfoResponeDTO.setImages(getImageResponseDTOGroup(entity));
        List<CommentResponeseDTO> comments = getCommentResponeseDTOGroup(entity);
        postInfoResponeDTO.setComment(comments);
        postInfoResponeDTO.setCommentQuantity(Long.valueOf(comments.size()));
        postInfoResponeDTO.setUser(userInfoResponseConverter.toDTO(entity.getUser()));
        postInfoResponeDTO.setGroup(entity.getGroup() != null ? groupResponseConverter.toDTO(entity.getGroup()) : null);
        postInfoResponeDTO.setCreatedAt(DateTimeUtil.convertToTimestamp(entity.getCreatedAt()));
        postInfoResponeDTO.setUpdatedAt(DateTimeUtil.convertToTimestamp(entity.getUpdatedAt()));

        if (postInfoResponeDTO.getType().equals(PostType.RECRUIMENT.getName())) {
            return getRecruitmentPostResponseDTO(entity, postInfoResponeDTO);
        } else if (postInfoResponeDTO.getType().equals(PostType.SURVEY.getName())) {
            return getSurveyResponeDTO(entity, postInfoResponeDTO);
        }

        return getNormalPostResponseDTO(entity, postInfoResponeDTO);
    }

    private static NormalPostSearchResponseDTO getNormalPostResponseDTO(PostEntity entity, PostSearchResponseDTO postSearchResponeDTO) {
        NormalPostSearchResponseDTO normalPostResponseDTO = new NormalPostSearchResponseDTO();
        BeanUtils.copyProperties(postSearchResponeDTO, normalPostResponseDTO);
        BeanUtils.copyProperties(entity.getNormalPost(), normalPostResponseDTO);
        return normalPostResponseDTO;
    }

    private SurveyPostSearchResponseDTO getSurveyResponeDTO(PostEntity entity, PostSearchResponseDTO postSearchResponseDTO) {
        SurveyPostSearchResponseDTO surveyResponeDTO = new SurveyPostSearchResponseDTO();
        BeanUtils.copyProperties(postSearchResponseDTO, surveyResponeDTO);
        BeanUtils.copyProperties(entity.getSurveyPost(), surveyResponeDTO);
        surveyResponeDTO.setQuestions(getQuestionResponseDTOGroup(entity));
        return surveyResponeDTO;
    }

    private static RecruitmentPostSearchResponseDTO getRecruitmentPostResponseDTO(PostEntity entity, PostSearchResponseDTO postInfoResponeDTO) {
        RecruitmentPostSearchResponseDTO recruitmentPostResponseDTO = new RecruitmentPostSearchResponseDTO();
        BeanUtils.copyProperties(postInfoResponeDTO, recruitmentPostResponseDTO);
        BeanUtils.copyProperties(entity.getRecruitmentPost(), recruitmentPostResponseDTO);
        recruitmentPostResponseDTO.setExpiration(DateTimeUtil.convertToTimestamp(entity.getRecruitmentPost().getExpiration()));
        return recruitmentPostResponseDTO;
    }

    private List<CommentResponeseDTO> getCommentResponeseDTOGroup(PostEntity postEntity) {
        return postEntity.getComments().stream().map(comment -> {
            return commentResponseConverter.toDTO(comment);
        }).collect(Collectors.toList());
    }

    private static List<ImageResponseDTO> getImageResponseDTOGroup(PostEntity postEntity) {
        return postEntity.getImages().stream().map(imageEntity -> {
            ImageResponseDTO imageResponseDTO = new ImageResponseDTO();
            imageResponseDTO.setId(imageEntity.getId());
            imageResponseDTO.setUri(imageEntity.getUri());
            return imageResponseDTO;
        }).collect(Collectors.toList());
    }

    private List<QuestionResponseDTO> getQuestionResponseDTOGroup(PostEntity postEntity) {
        List<QuestionResponseDTO> questionResponseDTOs = new ArrayList<>();
        for (QuestionEntity questionEntity : postEntity.getSurveyPost().getQuestions()) {
            QuestionResponseDTO questionResponseDTO = questionResponeConverter.toDTO(questionEntity);
            questionResponseDTOs.add(questionResponseDTO);
        }
        return questionResponseDTOs;
    }
}
