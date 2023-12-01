package com.chatapp.converter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatapp.entity.PostEntity;
import com.chatapp.entity.QuestionEntity;
import com.chatapp.entity.RecruitmentPostEntity;
import com.chatapp.entity.SurveyPostEntity;
import com.chatapp.entity.VoteAnswerEntity;
import com.chatapp.enums.QuestionType;
import com.chatapp.repository.JobProfileRepository;
import com.chatapp.repository.ShortAnswerRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.repository.VoteAnswerRepository;

@Component
public class PostCheckParam {
    @Autowired
    ShortAnswerRepository shortAnswerRepository;

    @Autowired
    VoteAnswerRepository voteAnswerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JobProfileRepository jobProfileRepository;

    public Long checkUserLoginHadConducted(SurveyPostEntity surveyPostEntity, Long userLogin) {
        Long isConducted = Long.valueOf(0);
        for (QuestionEntity questionEntity : surveyPostEntity.getQuestions()) {
            if (questionEntity.getType().equals(QuestionType.SHORT.getName())) {
                if (shortAnswerRepository.findOneByUser_IdAndQuestion_Id(userLogin,questionEntity.getId()) != null) {
                    isConducted = Long.valueOf(1);
                    return isConducted;
                }
            } else {
                List<VoteAnswerEntity> voteAnswerEntities = voteAnswerRepository
                        .findAllByQuestion_Id(questionEntity.getId());
                for (VoteAnswerEntity voteAnswerEntity : voteAnswerEntities) {
                    if (userRepository.findOneById(userLogin).getVoteAnswers().contains(voteAnswerEntity)) {
                        isConducted = Long.valueOf(1);
                        return isConducted;
                    }
                }
            }
        }
        return isConducted;
    }

    public Long checkUserLoginHadApplied(RecruitmentPostEntity recruitmentEntity, Long userLogin) {
        Long isApplied = Long.valueOf(0);
        if (jobProfileRepository.findOneByPost_IdAndUser_Id(recruitmentEntity.getPost().getId(), userLogin) != null) {
            isApplied = Long.valueOf(1);
        }
        return isApplied;
    }

    public Long checkUserLoginHadSavePost(PostEntity postEntity, Long userLogin) {
        Long isSave = Long.valueOf(0);
        if (userRepository.findOneById(userLogin).getPostSave().contains(postEntity)) {
            isSave = Long.valueOf(1);
        }
        return isSave;
    }
}
