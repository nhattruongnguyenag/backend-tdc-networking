package com.chatapp.converter.response.post.survey;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.items.ChoiceItemResponseDTO;
import com.chatapp.dto.response.post.survey.SurveyResultResponseDTO;
import com.chatapp.dto.response.post.survey.TextResultResponseDTO;
import com.chatapp.dto.response.post.survey.VoteResultResponseDTO;
import com.chatapp.entity.QuestionEntity;
import com.chatapp.entity.VoteAnswerEntity;
import com.chatapp.enums.QuestionType;
import com.chatapp.repository.ShortAnswerRepository;
import com.chatapp.repository.VoteAnswerRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurveyResultResponseConverter extends BaseConverter<QuestionEntity, SurveyResultResponseDTO> {
    @Autowired
    private ShortAnswerResultResponseConverter shortAnswerResultResponseConverter;
    @Autowired
    private ShortAnswerRepository shortAnswerRepository;
    @Autowired
    private VoteAnswerRepository voteAnswerRepository;

    @Override
    public SurveyResultResponseDTO toDTO(QuestionEntity entity) {
        if (entity.getType().equals(QuestionType.SHORT.getName())) {
            TextResultResponseDTO textResultResponseDTO = new TextResultResponseDTO();
            textResultResponseDTO.setType(entity.getType());
            textResultResponseDTO.setQuestionId(entity.getId());
            textResultResponseDTO.setTitle(entity.getTitle());
            textResultResponseDTO.setAnswers(shortAnswerResultResponseConverter
                    .toDTOGroup(shortAnswerRepository.findAllByQuestion_Id(entity.getId())));
            return textResultResponseDTO;
        } else {
            VoteResultResponseDTO voteResultResponseDTO = new VoteResultResponseDTO();
            voteResultResponseDTO.setType(entity.getType());
            voteResultResponseDTO.setQuestionId(entity.getId());
            voteResultResponseDTO.setTitle(entity.getTitle());
            List<VoteAnswerEntity> voteAnswerEntities = voteAnswerRepository
                    .findAllByQuestion_Id(entity.getId());
            List<ChoiceItemResponseDTO> choiceItemResponseDTOs = new ArrayList<ChoiceItemResponseDTO>();
            for (VoteAnswerEntity voteAnswerEntity : voteAnswerEntities) {
                ChoiceItemResponseDTO choiceItemResponseDTO = new ChoiceItemResponseDTO();
                choiceItemResponseDTO.setContent(voteAnswerEntity.getContent());
                choiceItemResponseDTO.setVotes(voteAnswerEntity.getUsers().size());
                choiceItemResponseDTOs.add(choiceItemResponseDTO);
            }
            voteResultResponseDTO.setChoices(choiceItemResponseDTOs);
            return voteResultResponseDTO;
        }
    }
}
