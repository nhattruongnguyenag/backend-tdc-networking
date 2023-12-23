package com.chatapp.converter.response.post.survey;

import com.chatapp.entity.QuestionEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.entity.VoteAnswerEntity;
import com.chatapp.enums.QuestionType;
import com.chatapp.repository.ShortAnswerRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.post.survey.SurveyPreviewResponseDTO;
import com.chatapp.dto.response.post.survey.TextPreviewResponseDTO;
import com.chatapp.dto.response.post.survey.VotePreviewResponseDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurveyReviewResponseConverter extends BaseConverter<QuestionEntity, SurveyPreviewResponseDTO> {
    @Autowired
    private ShortAnswerRepository shortAnswerRepository;
    @Autowired
    private UserRepository userRepository;

    public List<SurveyPreviewResponseDTO> toSurveyReviewDTOs(List<QuestionEntity> entities, Long userId) {
        List<SurveyPreviewResponseDTO> dtos = new ArrayList<SurveyPreviewResponseDTO>();
        for (QuestionEntity entity : entities) {
            UserEntity userEntity = userRepository.findOneById(userId);
            if (entity.getType().equals(QuestionType.SHORT.getName())) {
                TextPreviewResponseDTO textPreviewResponseDTO = new TextPreviewResponseDTO();
                textPreviewResponseDTO.setQuestionId(entity.getId());
                if(shortAnswerRepository.findOneByUser_IdAndQuestion_Id(userId, entity.getId()) != null){
                    textPreviewResponseDTO.setContent(
                        shortAnswerRepository.findOneByUser_IdAndQuestion_Id(userId, entity.getId()).getContent());
                }
                else{
                    textPreviewResponseDTO.setContent(null);
                }
                dtos.add(textPreviewResponseDTO);
            } else {
                VotePreviewResponseDTO votePreviewResponseDTO = new VotePreviewResponseDTO();
                votePreviewResponseDTO.setQuestionId(entity.getId());
                List<VoteAnswerEntity> voteAnswerEntities = userEntity.getVoteAnswers();
                List<Long> choice_ids = new ArrayList<Long>();
                for (VoteAnswerEntity voteAnswerEntity : voteAnswerEntities) {
                    if (voteAnswerEntity.getQuestion().getId().equals(entity.getId())) {
                        choice_ids.add(voteAnswerEntity.getId());
                    }
                }
                votePreviewResponseDTO.setChoices_ids(choice_ids);
                dtos.add(votePreviewResponseDTO);
            }
        }
        return dtos;
    }
}
