package com.chatapp.service;

import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.request.post.AllPostByUserAndGroupResponseDTO;
import com.chatapp.dto.request.post.PostFindRequestDTO;
import com.chatapp.dto.request.post.PostGetRequestDTO;
import com.chatapp.dto.request.post.PostSearchRequestDTO;
import com.chatapp.dto.request.post.comment.CommentDeleteRequestDTO;
import com.chatapp.dto.request.post.comment.CommentSaveRequestDTO;
import com.chatapp.dto.request.post.log.PostLogRequestDTO;
import com.chatapp.dto.request.post.normal.NormalPostUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.post.normal.NormalPostUpdateRequestDTO;
import com.chatapp.dto.request.post.recruitment.RecruitmentPostUpdateOrSageRequestDTO;
import com.chatapp.dto.request.post.recruitment.RecruitmentPostUpdateRequestDTO;
import com.chatapp.dto.request.post.survey.SurveyAnswerRequestDTO;
import com.chatapp.dto.request.post.survey.SurveySaveRequestDTO;
import com.chatapp.dto.request.user.UserDetailInGroupRequestDTO;
import com.chatapp.dto.request.user.like.LikeRequestDTO;
import com.chatapp.dto.request.user.post_save.UserSavePostFindRequestDTO;
import com.chatapp.dto.request.user.post_save.UserSavePostRequestDTO;
import com.chatapp.dto.response.post.PostSearchResponseDTO;
import com.chatapp.dto.response.post.comment.CommentResponeseDTO;
import com.chatapp.dto.response.post.log.PostRejectLogDTO;
import com.chatapp.dto.response.post.normal.NormalPostResponseDTO;
import com.chatapp.dto.response.post.recruitment.RecruitmentPostResponseDTO;
import com.chatapp.dto.response.post.survey.SurveyDTO;
import com.chatapp.dto.response.post.survey.SurveyPreviewResponseDTO;
import com.chatapp.dto.response.post.survey.SurveyResponeDTO;
import com.chatapp.dto.response.post.survey.SurveyResultResponseDTO;
import com.chatapp.dto.response.user.UserDetailInGroupResponseDTO;

import java.util.List;

public interface PostService {
    boolean updateSurvey(SurveyDTO surveyDTO);
    List<PostSearchResponseDTO> findPosts(PostSearchRequestDTO requestDTO);
    List<BaseDTO> findAll();
    PostSearchResponseDTO findById(PostGetRequestDTO requestDTO, Long userLogin);

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

    SurveyDTO getSurveyByPostId(Long postId);
    // post like
    String likePost(LikeRequestDTO likeRequestDTO);

    // comment
    String commentPost(CommentSaveRequestDTO commentSaveRequestDTO);

    String deleteComment(CommentDeleteRequestDTO commentDeleteRequestDTO);

    // find
    List<PostSearchResponseDTO> findPostByName(PostFindRequestDTO postFindRequestDTO);

    // find comments
    List<CommentResponeseDTO> findCommentByPostId(Long postId);

    List<BaseDTO> findAllByRoleCode(String code);

    List<PostSearchResponseDTO> findAllByGroupCode(String groupCode, Long userLogin);

    List<BaseDTO> getAllPostByUserIdAndType(Long userId, String type);

    List<PostSearchResponseDTO> getAllPostByUserIdAndGroupCode(AllPostByUserAndGroupResponseDTO allPostByUserAndGroupResponseDTO);

    // user save post
    String userSavePost(UserSavePostRequestDTO userSavePostRequestDTO);

    List<PostSearchResponseDTO> getPostSaveByUserId(Long userId);

    List<PostSearchResponseDTO> getPostSaveByUserIdAndSearch(UserSavePostFindRequestDTO userSavePostFindRequestDTO);

    UserDetailInGroupResponseDTO getUserPageInGroup(UserDetailInGroupRequestDTO userDetailInGroupRequestDTO);

    //post approval log
    String addPostLog(PostLogRequestDTO postLogRequestDTO);
    String deletePostLog(Long postId);

    String acceptPost(Long postId);

    PostRejectLogDTO findRejectLogByPostId(Long postId);
}
