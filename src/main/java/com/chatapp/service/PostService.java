package com.chatapp.service;

import java.util.List;

import com.chatapp.dto.request.NormalPostUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.RecruitmentPostUpdateOrSageRequestDTO;
import com.chatapp.dto.request.SurveySaveRequestDTO;
import com.chatapp.dto.response.NormalPostResponseDTO;
import com.chatapp.dto.response.PostInfoResponseDTO;
import com.chatapp.dto.response.RecruitmentPostResponseDTO;

public interface PostService {
    List<PostInfoResponseDTO> findAll();

    //normal post
    List<NormalPostResponseDTO> findAllNormalPost();
    PostInfoResponseDTO normalPostUpdateOrSave(NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO);

    //recruitment post
    List<RecruitmentPostResponseDTO> findAllRecruitmentPost();
    PostInfoResponseDTO recruitmentPostUpdateOrSave(RecruitmentPostUpdateOrSageRequestDTO recruitmentPostUpdateOrSageRequestDTO);

    //save survey
    String saveSurvey(SurveySaveRequestDTO saveRequestDTO);
}
