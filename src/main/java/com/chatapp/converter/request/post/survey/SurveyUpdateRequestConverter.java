package com.chatapp.converter.request.post.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.post.survey.SurveyUpdateRequestDTO;
import com.chatapp.entity.PostEntity;
import com.chatapp.entity.QuestionEntity;
import com.chatapp.entity.SurveyPostEntity;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.QuestionRepository;

@Component
public class SurveyUpdateRequestConverter extends BaseConverter<PostEntity, SurveyUpdateRequestDTO> {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public PostEntity toEntity(SurveyUpdateRequestDTO dto) {
        PostEntity postEntity = postRepository.findOneById(dto.getId());
        postEntity.setActive((byte) 0);
        SurveyPostEntity surveyPostEntity = postEntity.getSurveyPost();
        surveyPostEntity.setTitle(dto.getTitle());
        surveyPostEntity.setDescription(dto.getDescription());

        //1: xoa cac phan tu cu bi thua khi update lai
        for (QuestionEntity questionEntity : surveyPostEntity.getQuestions()) {
            if(questionRepository.findOneById(dto.getId()) == null){
                questionRepository.delete(questionEntity);
            }
        }

        //2: cap nhat cac phan tu hop le
        
        return postEntity;
    }    
}
