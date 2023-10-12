package com.chatapp.service;

import java.util.List;

import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.request.CommentDeleteRequestDTO;
import com.chatapp.dto.request.CommentSaveRequestDTO;
import com.chatapp.dto.request.LikeRequestDTO;
import com.chatapp.dto.request.NormalPostUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.RecruitmentPostUpdateOrSageRequestDTO;
import com.chatapp.dto.request.SurveySaveRequestDTO;
import com.chatapp.dto.response.NormalPostResponseDTO;
import com.chatapp.dto.response.PostInfoResponseDTO;
import com.chatapp.dto.response.RecruitmentPostResponseDTO;

public interface PostService {
    List<BaseDTO> findAll();

    //normal post
    List<NormalPostResponseDTO> findAllNormalPost();
    PostInfoResponseDTO normalPostUpdateOrSave(NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO);

    //recruitment post
    List<RecruitmentPostResponseDTO> findAllRecruitmentPost();
    PostInfoResponseDTO recruitmentPostUpdateOrSave(RecruitmentPostUpdateOrSageRequestDTO recruitmentPostUpdateOrSageRequestDTO);

    //save survey
    String saveSurvey(SurveySaveRequestDTO saveRequestDTO);

    //post like
    String likePost(LikeRequestDTO likeRequestDTO);

    //comment 
    String commentPost(CommentSaveRequestDTO commentSaveRequestDTO);
    String deleteComment(CommentDeleteRequestDTO commentDeleteRequestDTO);
}
