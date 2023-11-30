package com.chatapp.converter.request.post.normal;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.post.normal.NormalPostUpdateOrSaveRequestDTO;
import com.chatapp.entity.GroupEntity;
import com.chatapp.entity.NormalPostEntity;
import com.chatapp.entity.PostEntity;
import com.chatapp.entity.PostImageEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.GroupRepository;
import com.chatapp.repository.NormalPostRepository;
import com.chatapp.repository.PostImageRepository;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NormalPostUpdateOrSaveRequestConverter
        extends BaseConverter<PostEntity, NormalPostUpdateOrSaveRequestDTO> {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private NormalPostRepository normalPostRepository;

    public PostEntity toUpdatEntity(NormalPostUpdateOrSaveRequestDTO dto) {
        PostEntity postEntity = postRepository.findOneById(dto.getId());
        UserEntity userEntity = userRepository.findOneById(dto.getUserId());
        GroupEntity groupEntity = groupRepository.findOneById(dto.getGroupId());
        postEntity.setGroup(groupEntity);
        postEntity.setUser(userEntity);
        postEntity.setId(dto.getId());
        postEntity.setType(dto.getType());
        NormalPostEntity normalPostEntity = normalPostRepository.findOneByPost_Id(dto.getId());
        normalPostEntity.setContent(dto.getContent());
        normalPostEntity.setPost(postEntity);
        postEntity.setNormalPost(normalPostEntity);
        return postEntity;
    }

    @Override
    public PostEntity toEntity(NormalPostUpdateOrSaveRequestDTO dto) {
        UserEntity userEntity = userRepository.findOneById(dto.getUserId());
        GroupEntity groupEntity = groupRepository.findOneById(dto.getGroupId());
        PostEntity postEntity = new PostEntity();
        postEntity.setGroup(groupEntity);
        postEntity.setUser(userEntity);
        List<PostImageEntity> postImageEntityList = new ArrayList<>();
        for (String image : dto.getImages()) {
            PostImageEntity postImageEntity = new PostImageEntity();
            postImageEntity.setPost(postEntity);
            postImageEntity.setUri(image);
            postImageEntityList.add(postImageEntity);
        }
        postEntity.setImages(postImageEntityList);
        postEntity.setType(dto.getType());
        NormalPostEntity normalPostEntity = new NormalPostEntity();
        normalPostEntity.setContent(dto.getContent());
        normalPostEntity.setPost(postEntity);
        postEntity.setNormalPost(normalPostEntity);
        return postEntity;
    }
}
