package com.chatapp.service;

import java.util.List;

import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.request.AllPostByUserAndGroupResponseDTO;
import com.chatapp.dto.request.CommentDeleteRequestDTO;
import com.chatapp.dto.request.CommentSaveRequestDTO;
import com.chatapp.dto.request.LikeRequestDTO;
import com.chatapp.dto.request.NormalPostUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.PostFindRequestDTO;
import com.chatapp.dto.request.RecruitmentPostUpdateOrSageRequestDTO;
import com.chatapp.dto.request.SurveyAnswerRequestDTO;
import com.chatapp.dto.request.SurveySaveRequestDTO;
import com.chatapp.dto.request.UserDetailInGroupRequestDTO;
import com.chatapp.dto.request.UserGetRequestDTO;
import com.chatapp.dto.request.UserSavePostRequestDTO;
import com.chatapp.dto.response.CommentResponeseDTO;
import com.chatapp.dto.response.NormalPostResponseDTO;
import com.chatapp.dto.response.RecruitmentPostResponseDTO;
import com.chatapp.dto.response.SurveyPreviewResponseDTO;
import com.chatapp.dto.response.SurveyResponeDTO;
import com.chatapp.dto.response.SurveyResultResponseDTO;
import com.chatapp.dto.response.UserDetailInGroupResponseDTO;

public interface PostService {
    List<BaseDTO> findAll();

    List<BaseDTO> findAllByUserId(Long id);

    String delete(Long postid);

    // normal post
    List<NormalPostResponseDTO> findAllNormalPost();

    String normalPostUpdateOrSave(NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO);

    NormalPostResponseDTO getNormalDetailByPostId(Long postId);

    // recruitment post
    List<RecruitmentPostResponseDTO> findAllRecruitmentPost();

    RecruitmentPostResponseDTO getRecruimentDetailByPostId(Long postId, Long userLogin);

    String recruitmentPostUpdateOrSave(RecruitmentPostUpdateOrSageRequestDTO recruitmentPostUpdateOrSageRequestDTO);

    // save survey
    String saveSurvey(SurveySaveRequestDTO saveRequestDTO);

    SurveyResponeDTO getSurveyDetailByPostId(Long postId, Long userLogin);

    String answerSurvey(SurveyAnswerRequestDTO surveyAnswerRequestDTO);

    List<SurveyResultResponseDTO> getSurveyResultByPostId(Long postId);

    List<SurveyPreviewResponseDTO> reviewSurveyResultByPostIdAndUserId(Long postId, Long userId);
    // post like
    String likePost(LikeRequestDTO likeRequestDTO);

    // comment
    String commentPost(CommentSaveRequestDTO commentSaveRequestDTO);

    String deleteComment(CommentDeleteRequestDTO commentDeleteRequestDTO);

    // find
    List<BaseDTO> findPostByName(PostFindRequestDTO postFindRequestDTO);

    // find comments
    List<CommentResponeseDTO> findCommentByPostId(Long postId);

    List<BaseDTO> findAllByRoleCode(String code);

    List<BaseDTO> findAllByGroupCode(String groupCode, Long userLogin);

    List<BaseDTO> getAllPostByUserIdAndType(Long userId, String type);

    List<BaseDTO> getAllPostByUserIdAndGroupCode(AllPostByUserAndGroupResponseDTO allPostByUserAndGroupResponseDTO);

    // user save post
    String userSavePost(UserSavePostRequestDTO userSavePostRequestDTO);

    List<BaseDTO> getPostSaveByUserId(Long userId);

    UserDetailInGroupResponseDTO getUserPageInGroup(UserDetailInGroupRequestDTO userDetailInGroupRequestDTO);

}
