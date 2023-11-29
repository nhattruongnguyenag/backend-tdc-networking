package com.chatapp.converter.response.post.survey;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.post.survey.QuestionResponseDTO;
import com.chatapp.dto.response.post.survey.VoteQuestionResponseDTO;
import com.chatapp.entity.QuestionEntity;
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
        List<VoteQuestionResponseDTO> choices = new ArrayList<>();
        if (entity.getType().equals(QuestionType.MULTIPLE.getName()) || entity.getType().equals(QuestionType.ONE.getName())) {
            for (VoteAnswerEntity voteAnswerEntity : entity.getVoteAnswers()) {
                VoteQuestionResponseDTO voteQuestionResponseDTO = new VoteQuestionResponseDTO();
                voteQuestionResponseDTO.setId(voteAnswerEntity.getId());
                voteQuestionResponseDTO.setContent(voteAnswerEntity.getContent());
                choices.add(voteQuestionResponseDTO);
            }
        }
        questionResponseDTO.setChoices(choices);
        return questionResponseDTO;
    }
}
