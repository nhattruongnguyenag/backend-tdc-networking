package com.chatapp.converter.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.NormalPostUpdateRequestDTO;
import com.chatapp.entity.NormalPostEntity;
import com.chatapp.repository.NormalPostRepository;


@Component
public class NormalPostUpdateRequestConverter extends BaseConverter<NormalPostEntity,NormalPostUpdateRequestDTO>{

    @Autowired
    private NormalPostRepository normalPostRepository;

    @Override
    public NormalPostEntity toEntity(NormalPostUpdateRequestDTO dto) {
        NormalPostEntity normalPostEntity = normalPostRepository.findOneByPost_Id(dto.getPostId());
        normalPostEntity.setContent(dto.getContent());
        return normalPostEntity;
    }
}
