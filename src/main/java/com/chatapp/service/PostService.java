package com.chatapp.service;

import java.util.List;

import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.request.*;
import com.chatapp.dto.response.*;
import com.chatapp.dto.response.postSearch.PostSearchResponseDTO;

public interface PostService {
    List<PostSearchResponseDTO> findPosts(PostSearchRequestDTO requestDTO);
    List<BaseDTO> findAll();

    List<BaseDTO> findAllByUserId(Long id);

    String delete(Long postid);

    // normal post
    List<NormalPostResponseDTO> findAllNormalPost();

    String normalPostUpdateOrSave(NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO);

    NormalPostResponseDTO getNormalDetailByPostId(Long postId);

    String updateNormalPost(NormalPostUpdateRequestDTO normalPostUpdateRequestDTO);

    // recruitment post
    List<RecruitmentPostResponseDTO> findAllRecruitmentPost();

    RecruitmentPostResponseDTO getRecruimentDetailByPostId(Long postId, Long userLogin);

    String recruitmentPostUpdateOrSave(RecruitmentPostUpdateOrSageRequestDTO recruitmentPostUpdateOrSageRequestDTO);

    String updateRecruitmentPost(RecruitmentPostUpdateRequestDTO recruitmentPostUpdateRequestDTO);

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

    //post approval log
    String addPostLog(PostLogRequestDTO postLogRequestDTO);
    String deletePostLog(Long postId);
}
