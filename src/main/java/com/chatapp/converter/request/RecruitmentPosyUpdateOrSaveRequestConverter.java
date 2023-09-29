package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.RecruitmentPostUpdateOrSageRequestDTO;
import com.chatapp.entity.PostEntity;
import com.chatapp.entity.RecruitmentPostEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.RecruitmentPostRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.util.DateTimeUtil;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecruitmentPosyUpdateOrSaveRequestConverter
        extends BaseConverter<PostEntity, RecruitmentPostUpdateOrSageRequestDTO> {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecruitmentPostRepository recruitmentPostRepository;

    public PostEntity toUpdatEntity(RecruitmentPostUpdateOrSageRequestDTO dto) {
        PostEntity postEntity = postRepository.findOneById(dto.getId());
        UserEntity userEntity = userRepository.findOneById(dto.getUserId());
        postEntity.setUser(userEntity);
        postEntity.setId(dto.getId());
        postEntity.setType(dto.getType());
        RecruitmentPostEntity recruitmentPostEntity = recruitmentPostRepository.findOneByPost_Id(dto.getId());
        recruitmentPostEntity.setTitle(dto.getTitle());
        recruitmentPostEntity.setSalary(dto.getSalary());
        recruitmentPostEntity.setBenefit(dto.getBenefit());
        recruitmentPostEntity.setDescription(dto.getDescription());
        recruitmentPostEntity.setEmploymentType(dto.getEmploymentType());
        recruitmentPostEntity.setExpiration(Timestamp.valueOf(dto.getExpiration()));
        recruitmentPostEntity.setLocation(dto.getLocation());
        recruitmentPostEntity.setRequirement(dto.getRequirement());
        recruitmentPostEntity.setPost(postEntity);
        postEntity.setRecruitmentPost(recruitmentPostEntity);
        return postEntity;
    }

    @Override
    public PostEntity toEntity(RecruitmentPostUpdateOrSageRequestDTO dto) {
        PostEntity postEntity = super.toEntity(dto);
        UserEntity userEntity = userRepository.findOneById(dto.getUserId());
        postEntity.setUser(userEntity);
        RecruitmentPostEntity recruitmentPostEntity = new RecruitmentPostEntity();
        recruitmentPostEntity.setTitle(dto.getTitle());
        recruitmentPostEntity.setSalary(dto.getSalary());
        recruitmentPostEntity.setBenefit(dto.getBenefit());
        recruitmentPostEntity.setDescription(dto.getDescription());
        recruitmentPostEntity.setEmploymentType(dto.getEmploymentType());
        recruitmentPostEntity.setExpiration(Timestamp.valueOf(dto.getExpiration()));
        recruitmentPostEntity.setLocation(dto.getLocation());
        recruitmentPostEntity.setRequirement(dto.getRequirement());
        recruitmentPostEntity.setPost(postEntity);
        postEntity.setRecruitmentPost(recruitmentPostEntity);
        return postEntity;
    }
}
