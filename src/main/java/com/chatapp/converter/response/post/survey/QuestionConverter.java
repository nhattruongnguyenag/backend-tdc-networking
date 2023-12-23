package com.chatapp.converter.response.post.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.post.survey.QuestionDTO;
import com.chatapp.entity.QuestionEntity;
import com.chatapp.enums.QuestionType;

@Component
public class QuestionConverter extends BaseConverter<QuestionEntity, QuestionDTO> {

    @Autowired
    private ChoiceConverter choiceConverter;

    @Override
    public QuestionDTO toDTO(QuestionEntity entity) {
        QuestionDTO questionDTO = super.toDTO(entity);
        if (entity.getRequired() == 48) {
            questionDTO.setRequired((byte) 0);
        }

        if (entity.getType().equals(QuestionType.MULTIPLE.getName())
                || entity.getType().equals(QuestionType.ONE.getName())) {
            questionDTO.setChoices(choiceConverter.toDTOGroup(entity.getVoteAnswers()));
        }
        return questionDTO;
    }
}
