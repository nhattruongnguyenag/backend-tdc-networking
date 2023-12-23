package com.chatapp.converter.response.post.survey;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.post.survey.QuestionDTO;
import com.chatapp.dto.response.post.survey.SurveyDTO;
import com.chatapp.entity.QuestionEntity;
import com.chatapp.entity.SurveyPostEntity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurveyConverter extends BaseConverter<SurveyPostEntity, SurveyDTO> {
    @Autowired
    private QuestionConverter questionConverter;

    @Override
    public SurveyDTO toDTO(SurveyPostEntity entity) {
        SurveyDTO surveyDTO = super.toDTO(entity);
        surveyDTO.setPostId(entity.getPost().getId());
        List<QuestionDTO> questions = new ArrayList<QuestionDTO>();
        for (QuestionEntity questionEntity : entity.getQuestions()) {
            QuestionDTO questionDTO = questionConverter.toDTO(questionEntity);
            questions.add(questionDTO);
        }
        surveyDTO.setQuestions(questions);
        return surveyDTO;
    }
}
