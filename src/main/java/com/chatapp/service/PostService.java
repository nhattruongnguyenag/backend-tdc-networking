package com.chatapp.service;

import java.util.List;

import com.chatapp.dto.request.NormalPostUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.RecruitmentPostUpdateOrSageRequestDTO;
import com.chatapp.dto.request.SurveySaveRequestDTO;
import com.chatapp.dto.response.NormalPostResponeDTO;
import com.chatapp.dto.response.PostInfoResponeDTO;
import com.chatapp.dto.response.RecruitmentPostResponeDTO;

public interface PostService {
    List<PostInfoResponeDTO> findAll();

    //normal post
    List<NormalPostResponeDTO> findAllNormalPost();
    PostInfoResponeDTO normalPostUpdateOrSave(NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO);

    //recruitment post
    List<RecruitmentPostResponeDTO> findAllRecruitmentPost();
    PostInfoResponeDTO recruitmentPostUpdateOrSave(RecruitmentPostUpdateOrSageRequestDTO recruitmentPostUpdateOrSageRequestDTO);

    //save survey
    String saveSurvey(SurveySaveRequestDTO saveRequestDTO);
}
