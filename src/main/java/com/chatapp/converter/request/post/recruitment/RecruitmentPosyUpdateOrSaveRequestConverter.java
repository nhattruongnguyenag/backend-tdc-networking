package com.chatapp.converter.request.post.recruitment;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.post.recruitment.RecruitmentPostUpdateOrSageRequestDTO;
import com.chatapp.entity.GroupEntity;
import com.chatapp.entity.PostEntity;
import com.chatapp.entity.PostImageEntity;
import com.chatapp.entity.RecruitmentPostEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.GroupRepository;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.RecruitmentPostRepository;
import com.chatapp.repository.UserRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    private GroupRepository groupRepository;
    
    @Autowired
    private RecruitmentPostRepository recruitmentPostRepository;

    public PostEntity toUpdatEntity(RecruitmentPostUpdateOrSageRequestDTO dto) {
        PostEntity postEntity = postRepository.findOneById(dto.getId());
        UserEntity userEntity = userRepository.findOneById(dto.getUserId());
        GroupEntity groupEntity = groupRepository.findOneById(dto.getGroupId());
        postEntity.setGroup(groupEntity);
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
        GroupEntity groupEntity = groupRepository.findOneById(dto.getGroupId());
        postEntity.setGroup(groupEntity);
        postEntity.setUser(userEntity);
        if (dto.getImages() != null) {
            List<PostImageEntity> postImageEntityList = new ArrayList<>();
            for (String image : dto.getImages()) {
                PostImageEntity postImageEntity = new PostImageEntity();
                postImageEntity.setPost(postEntity);
                postImageEntity.setUri(image);
                postImageEntityList.add(postImageEntity);
            }
            postEntity.setImages(postImageEntityList);
        }
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
