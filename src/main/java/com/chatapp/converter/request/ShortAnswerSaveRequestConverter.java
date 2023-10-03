package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.ShortAnswerSaveRequestDTO;
import com.chatapp.entity.PostEntity;
import com.chatapp.entity.QuestionEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShortAnswerSaveRequestConverter
        extends BaseConverter<PostEntity, ShortAnswerSaveRequestDTO> {

    @Autowired
    private UserRepository userRepository;


    @Override
    public PostEntity toEntity(ShortAnswerSaveRequestDTO dto) {
        PostEntity postEntity = super.toEntity(dto);
        UserEntity userEntity = userRepository.findOneById(dto.getUserId());
        postEntity.setUser(userEntity);
        // List<QuestionEntity> questions = new ArrayList<>();
        // QuestionEntity questionEntity = new QuestionEntity();
        // questionEntity.setTitle(dto.getTitle());
        // questionEntity.setType("short");
        // questionEntity.setPost(postEntity);
        // ShortAnswerEntity shortAnswerEntity = new ShortAnswerEntity();
        // shortAnswerEntity.setContent(dto.getContent());
        // shortAnswerEntity.setQuestion(questionEntity);
        // questions.add(questionEntity);
        // postEntity.setQuestions(questions);
        return postEntity;
    }
}