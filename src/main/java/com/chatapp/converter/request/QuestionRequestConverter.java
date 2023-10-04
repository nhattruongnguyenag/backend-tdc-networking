package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.QuestionRequestDTO;
import com.chatapp.entity.QuestionEntity;
import com.chatapp.entity.VoteAnswerEntity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class QuestionRequestConverter
        extends BaseConverter<QuestionEntity, QuestionRequestDTO> {

    @Override
    public QuestionEntity toEntity(QuestionRequestDTO dto) {
        QuestionEntity entity = super.toEntity(dto);
        List<VoteAnswerEntity> answerEntities = new ArrayList<>();
        if (dto.getChoices() != null) {
            for (String content : dto.getChoices()) {
                VoteAnswerEntity voteAnswerEntity = new VoteAnswerEntity();
                voteAnswerEntity.setContent(content);
                voteAnswerEntity.setCountVote(0);
                voteAnswerEntity.setQuestion(entity);
                answerEntities.add(voteAnswerEntity);
            }
            entity.setVoteAnswers(answerEntities);
        }
        return entity;
    }
}