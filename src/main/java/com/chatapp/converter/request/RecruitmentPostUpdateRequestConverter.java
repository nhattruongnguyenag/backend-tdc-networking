package com.chatapp.converter.request;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.RecruitmentPostUpdateRequestDTO;
import com.chatapp.entity.RecruitmentPostEntity;
import com.chatapp.repository.RecruitmentPostRepository;


@Component
public class RecruitmentPostUpdateRequestConverter extends BaseConverter<RecruitmentPostEntity,RecruitmentPostUpdateRequestDTO>{

    @Autowired
    private RecruitmentPostRepository recruitmentPostRepository;

    @Override
    public RecruitmentPostEntity toEntity(RecruitmentPostUpdateRequestDTO dto) {
        RecruitmentPostEntity entity = recruitmentPostRepository.findOneByPost_Id(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setExpiration(Timestamp.valueOf(dto.getExpiration()));
        entity.setSalary(dto.getSalary());
        entity.setDescription(dto.getDescription());
        entity.setBenefit(dto.getBenefit());
        entity.setEmploymentType(dto.getEmploymentType());
        entity.setLocation(dto.getLocation());
        entity.setRequirement(dto.getRequirement());
        return entity;
    }
}
