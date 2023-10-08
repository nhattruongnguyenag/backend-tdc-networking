package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.QuestionResponseDTO;
import com.chatapp.entity.QuestionEntity;
import com.chatapp.entity.ShortAnswerEntity;
import com.chatapp.entity.VoteAnswerEntity;
import com.chatapp.enums.QuestionType;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class QuestionResponeConverter extends BaseConverter<QuestionEntity, QuestionResponseDTO> {
    
    @Override
    public QuestionResponseDTO toDTO(QuestionEntity entity) {
        QuestionResponseDTO questionResponseDTO = super.toDTO(entity);
        List<String> choices = new ArrayList<>();
        if (entity.getType().equals(QuestionType.MULTIPLE.getName()) || entity.getType().equals(QuestionType.ONE.getName())) {
            for (VoteAnswerEntity voteAnswerEntity : entity.getVoteAnswers()) {
                choices.add(voteAnswerEntity.getContent());
            }
        }
        else if (entity.getType().equals(QuestionType.SHORT.getName())){
            for (ShortAnswerEntity shortAnswerEntity : entity.getShortAnswers()) {
                choices.add(shortAnswerEntity.getUser().getEmail() + "-" + shortAnswerEntity.getContent() + "-" + shortAnswerEntity.getCreatedAt());
            }
        }
        questionResponseDTO.setChoices(choices);
        return questionResponseDTO;
    }
}
