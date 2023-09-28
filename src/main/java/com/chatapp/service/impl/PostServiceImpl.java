package com.chatapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.converter.request.NormalPostUpdateOrSaveRequestConverter;
import com.chatapp.converter.response.NormalPostResponeConverter;
import com.chatapp.converter.response.PostInfoResponeConverter;
import com.chatapp.dto.request.NormalPostUpdateOrSaveRequestDTO;
import com.chatapp.dto.response.NormalPostResponeDTO;
import com.chatapp.dto.response.PostInfoResponeDTO;
import com.chatapp.entity.PostEntity;
import com.chatapp.repository.NormalPostRepository;
import com.chatapp.repository.PostRepository;
import com.chatapp.service.PostService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private NormalPostRepository normalPostRepository;

    @Autowired
    private PostInfoResponeConverter postInfoResponeConverter;
    @Autowired
    private NormalPostResponeConverter normalPostResponeConverter;
    @Autowired
    private NormalPostUpdateOrSaveRequestConverter normalPostUpdateOrSaveRequestConverter;

    @Override
    public List<PostInfoResponeDTO> findAll() {
        return postInfoResponeConverter.toDTOGroup(postRepository.findAll());
    }

    @Override
    public List<NormalPostResponeDTO> findAllNormalPost() {
        return normalPostResponeConverter.toDTOGroup(normalPostRepository.findAll());
    }

    private PostEntity normalPostUpdate(NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO) {
        PostEntity postEntity;
        postEntity = normalPostUpdateOrSaveRequestConverter.toUpdatEntity(normalPostUpdateOrSaveRequestDTO);
        return postEntity;
    }

    private PostEntity normalPostSave(NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO) {
        PostEntity postEntity = normalPostUpdateOrSaveRequestConverter.toEntity(normalPostUpdateOrSaveRequestDTO);
        postEntity.setActive((byte)1);
        postEntity.setStatus((byte) 0);
        return postEntity;
    }

    @Override
    public PostInfoResponeDTO normalPostUpdateOrSave(
            NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO) {
        PostEntity postEntity;
        if (normalPostUpdateOrSaveRequestDTO.getId() != null) {
            postEntity = this.normalPostUpdate(normalPostUpdateOrSaveRequestDTO);
        } else {
            postEntity = this.normalPostSave(normalPostUpdateOrSaveRequestDTO);
        }
        return postInfoResponeConverter.toDTO(postRepository.save(postEntity));
    }
}
