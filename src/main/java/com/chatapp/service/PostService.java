package com.chatapp.service;

import java.util.List;

import com.chatapp.dto.request.NormalPostUpdateOrSaveRequestDTO;
import com.chatapp.dto.response.NormalPostResponeDTO;
import com.chatapp.dto.response.PostInfoResponeDTO;

public interface PostService {
    List<PostInfoResponeDTO> findAll();

    //normal post
    List<NormalPostResponeDTO> findAllNormalPost();
    PostInfoResponeDTO normalPostUpdateOrSave(NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO);
}
