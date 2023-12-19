package com.chatapp.converter.request.post.recruitment;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.post.recruitment.RecruitmentPostUpdateRequestDTO;
import com.chatapp.entity.PostImageEntity;
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
        List<PostImageEntity> postImageEntityList = entity.getPost().getImages();
        if (dto.getImages() != null) {
            if (dto.getImages().size() >= postImageEntityList.size()) {
                for (int index = 0; index < dto.getImages().size(); index++) {
                    if (postImageEntityList.size() <= index) {
                        PostImageEntity postImageEntity = new PostImageEntity();
                        postImageEntity.setPost(entity.getPost());
                        postImageEntity.setUri(dto.getImages().get(index));
                        postImageEntityList.add(postImageEntity);
                    } else {
                        postImageEntityList.get(index).setUri(dto.getImages().get(index));
                    }
                }
            } else {
                postImageEntityList.removeAll(postImageEntityList);
                for (int index = 0; index < dto.getImages().size(); index++) {
                    PostImageEntity postImageEntity = new PostImageEntity();
                    postImageEntity.setPost(entity.getPost());
                    postImageEntity.setUri(dto.getImages().get(index));
                    postImageEntityList.add(postImageEntity);
                }
            }
        }
        entity.getPost().setImages(postImageEntityList);
        entity.getPost().setActive((byte)0);
        return entity;
    }
}
