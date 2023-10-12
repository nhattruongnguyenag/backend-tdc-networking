package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.QuestionRequestDTO;
import com.chatapp.dto.request.SurveySaveRequestDTO;
import com.chatapp.entity.PostEntity;
import com.chatapp.entity.PostImageEntity;
import com.chatapp.entity.QuestionEntity;
import com.chatapp.entity.SurveyPostEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurveySaveRequestConverter extends BaseConverter<PostEntity, SurveySaveRequestDTO>{

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private QuestionRequestConverter questionRequestConverter;

    public PostEntity toPostEntity(SurveySaveRequestDTO dto){
        UserEntity userEntity = userRepository.findOneById(dto.getUserId());
        PostEntity postEntity = new PostEntity();
        postEntity.setUser(userEntity);
        List<PostImageEntity> postImageEntityList = new ArrayList<>();
        for (String image : dto.getImages()) {
            PostImageEntity postImageEntity = new PostImageEntity();
            postImageEntity.setPost(postEntity);
            postImageEntity.setUri(image);
            postImageEntityList.add(postImageEntity);
        }
        postEntity.setImages(postImageEntityList);
        postEntity.setType(dto.getType());
        SurveyPostEntity surveyPostEntity = new SurveyPostEntity();
        surveyPostEntity.setPost(postEntity);
        surveyPostEntity.setTitle(dto.getTitle());
        List<QuestionRequestDTO> questions = new ArrayList<QuestionRequestDTO>();
        for (QuestionRequestDTO questionDTO : dto.getQuestions()) {
            QuestionRequestDTO questionRequestDTO = new QuestionRequestDTO();
            if(questionDTO.getChoices()!= null){
                questionRequestDTO.setTitle(questionDTO.getTitle());
                questionRequestDTO.setType(questionDTO.getType());
                questionRequestDTO.setChoices(questionDTO.getChoices());
            }
            else{
                questionRequestDTO.setTitle(questionDTO.getTitle());
                questionRequestDTO.setType(questionDTO.getType());
            }
            questions.add(questionRequestDTO);
        }
        List<QuestionEntity> questionEntities = questionRequestConverter.toEntityGroup(questions);
        for (QuestionEntity entity : questionEntities) {
            entity.setSurvey(surveyPostEntity);
        }
        surveyPostEntity.setQuestions(questionEntities);
        postEntity.setSurveyPost(surveyPostEntity);
        return postEntity;
    }
}

