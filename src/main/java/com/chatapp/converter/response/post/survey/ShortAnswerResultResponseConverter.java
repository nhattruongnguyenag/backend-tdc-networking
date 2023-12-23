package com.chatapp.converter.response.post.survey;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.entity.ShortAnswerEntity;

import org.springframework.stereotype.Component;

@Component
public class ShortAnswerResultResponseConverter extends BaseConverter<ShortAnswerEntity, String> {

    @Override
    public String toDTO(ShortAnswerEntity entity){
        return entity.getContent();
    }
}
