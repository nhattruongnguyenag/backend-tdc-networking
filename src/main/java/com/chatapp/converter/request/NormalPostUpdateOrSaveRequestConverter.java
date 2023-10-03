package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.NormalPostUpdateOrSaveRequestDTO;
import com.chatapp.entity.NormalPostEntity;
import com.chatapp.entity.PostEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.NormalPostRepository;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.UserRepository;

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
    private NormalPostRepository normalPostRepository;

    public PostEntity toUpdatEntity(NormalPostUpdateOrSaveRequestDTO dto) {
        PostEntity postEntity = postRepository.findOneById(dto.getId());
        UserEntity userEntity = userRepository.findOneById(dto.getUserId());
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
        PostEntity postEntity = super.toEntity(dto);
        UserEntity userEntity = userRepository.findOneById(dto.getUserId());
        postEntity.setUser(userEntity);
        NormalPostEntity normalPostEntity = new NormalPostEntity();
        normalPostEntity.setContent(dto.getContent());
        normalPostEntity.setPost(postEntity);
        postEntity.setNormalPost(normalPostEntity);
        return postEntity;
    }
}
