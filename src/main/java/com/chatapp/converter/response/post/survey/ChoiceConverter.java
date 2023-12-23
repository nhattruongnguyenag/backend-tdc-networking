package com.chatapp.converter.response.post.survey;

import org.springframework.stereotype.Component;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.post.survey.ChoiceDTO;
import com.chatapp.entity.VoteAnswerEntity;

@Component
public class ChoiceConverter extends BaseConverter<VoteAnswerEntity, ChoiceDTO> {

    @Override
    public ChoiceDTO toDTO(VoteAnswerEntity entity) {
        ChoiceDTO choiceDTO = super.toDTO(entity);
        
        return choiceDTO;
    }
}
