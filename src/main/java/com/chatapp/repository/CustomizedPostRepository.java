package com.chatapp.repository;

import com.chatapp.dto.request.PostSearchRequestDTO;
import com.chatapp.entity.PostEntity;

import java.util.List;

public interface CustomizedPostRepository {
    List<PostEntity> findPosts(PostSearchRequestDTO dto);
}
