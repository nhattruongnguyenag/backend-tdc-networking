package com.chatapp.controller.api;

import com.chatapp.commond.MessageResponseData;
import com.chatapp.commond.ResponseData;
import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.request.post.AllPostByUserAndGroupResponseDTO;
import com.chatapp.dto.request.post.PostGetRequestDTO;
import com.chatapp.dto.request.post.PostSearchRequestDTO;
import com.chatapp.dto.request.post.PostUserGetRequestDTO;
import com.chatapp.dto.request.post.comment.CommentDeleteRequestDTO;
import com.chatapp.dto.request.post.comment.CommentSaveRequestDTO;
import com.chatapp.dto.request.post.normal.NormalPostUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.post.normal.NormalPostUpdateRequestDTO;
import com.chatapp.dto.request.post.recruitment.RecruitmentPostUpdateOrSageRequestDTO;
import com.chatapp.dto.request.post.recruitment.RecruitmentPostUpdateRequestDTO;
import com.chatapp.dto.request.post.survey.SurveyAnswerRequestDTO;
import com.chatapp.dto.request.post.survey.SurveySaveRequestDTO;
import com.chatapp.dto.request.post.survey.SurveyUpdateRequestDTO;
import com.chatapp.dto.request.user.UserDetailInGroupRequestDTO;
import com.chatapp.dto.request.user.like.LikeRequestDTO;
import com.chatapp.dto.request.user.post_save.UserSavePostFindRequestDTO;
import com.chatapp.dto.request.user.post_save.UserSavePostRequestDTO;
import com.chatapp.dto.response.post.PostSearchResponseDTO;
import com.chatapp.dto.response.post.comment.CommentResponeseDTO;
import com.chatapp.dto.response.post.normal.NormalPostResponseDTO;
import com.chatapp.dto.response.post.recruitment.RecruitmentPostResponseDTO;
import com.chatapp.dto.response.post.recruitment.RecruitmentPostSaveDTO;
import com.chatapp.dto.response.post.recruitment.RecruitmentPostSearchResponseDTO;
import com.chatapp.dto.response.post.survey.SurveyDTO;
import com.chatapp.dto.response.post.survey.SurveyPreviewResponseDTO;
import com.chatapp.dto.response.post.survey.SurveyResponeDTO;
import com.chatapp.dto.response.post.survey.SurveyResultResponseDTO;
import com.chatapp.dto.response.user.UserDetailInGroupResponseDTO;
import com.chatapp.enums.PostType;
import com.chatapp.service.PostService;
import com.chatapp.util.CommonUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostAPI {
    @Autowired
    private PostService postService;

    @Autowired
    private ModelMapper modelMapper;

    //////////////////
    // Get
    //////////////////
    @GetMapping({ "posts/all", "posts/all/" })
    public ResponseEntity<ResponseData<List<BaseDTO>>> findAll() {
        ResponseData<List<BaseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success", postService.findAll());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/search", "posts/search/" })
    public ResponseEntity<ResponseData<List<PostSearchResponseDTO>>> findPosts(
            @RequestParam Map<String, Object> params) {
        PostSearchRequestDTO dto = CommonUtils.mapToObject(params, PostSearchRequestDTO.class);
        ResponseData<List<PostSearchResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.findPosts(dto));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/user/save/{userId}", "posts/user/save/{userId}/" })
    ResponseEntity<ResponseData<?>> userSavePost(@PathVariable Long userId) {
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.getPostSaveByUserId(userId));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "posts/user/save/search", "posts/user/save/search" })
    ResponseEntity<ResponseData<?>> userSearchSavePost(
            @RequestBody UserSavePostFindRequestDTO userSavePostFindRequestDTO) {
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.getPostSaveByUserIdAndSearch(userSavePostFindRequestDTO));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/normal", "posts/normal/" })
    public ResponseEntity<ResponseData<List<NormalPostResponseDTO>>> findAllNormalPosts() {
        ResponseData<List<NormalPostResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.findAllNormalPost());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/normal/{postId}", "posts/normal/{postId}/" })
    ResponseEntity<ResponseData<NormalPostResponseDTO>> getNormalByPostId(@PathVariable Long postId) {
        ResponseData<NormalPostResponseDTO> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.getNormalDetailByPostId(postId));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/normal/user/{userId}", "posts/normal/user/{userId}/" })
    ResponseEntity<ResponseData<List<BaseDTO>>> getNormalPostsByUserId(@PathVariable Long userId) {
        ResponseData<List<BaseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.getAllPostByUserIdAndType(userId, PostType.NORMAL.getName()));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/recruitment", "posts/recruitment" })
    public ResponseEntity<ResponseData<RecruitmentPostResponseDTO>> getRecruimentPostByPostId(
            @RequestParam Long postId, @RequestParam Long userLogin) {
        ResponseData<RecruitmentPostResponseDTO> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.getRecruimentDetailByPostId(postId, userLogin));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/recruitment/{postId}/update", "posts/recruitment/{postId}/update/" })
    public ResponseEntity<?> getRecruitmentPostUpdate(@PathVariable("postId") Long id) {
        PostSearchRequestDTO postSearchRequestDTO = new PostSearchRequestDTO();
        postSearchRequestDTO.setPostId(id);

        List<PostSearchResponseDTO> responseDTOs = postService.findPosts(postSearchRequestDTO);
        if (responseDTOs.size() > 0) {
            RecruitmentPostSearchResponseDTO recruitmentPostSearchResponseDTO = (RecruitmentPostSearchResponseDTO) responseDTOs
                    .get(0);
            RecruitmentPostSaveDTO recruitmentPostSaveDTO = modelMapper.map(recruitmentPostSearchResponseDTO,
                    RecruitmentPostSaveDTO.class);
            return ResponseEntity.ok(recruitmentPostSaveDTO);
        }
        return ResponseEntity.badRequest().body(new MessageResponseData(HttpStatus.BAD_REQUEST, "not_found"));
    }

    @GetMapping({ "posts/recruitment/user/{userId}", "posts/recruitment/user/{userId}/" })
    ResponseEntity<ResponseData<List<BaseDTO>>> getRecruitmentPostsByUserId(@PathVariable Long userId) {
        ResponseData<List<BaseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.getAllPostByUserIdAndType(userId, PostType.RECRUIMENT.getName()));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/survey", "posts/survey" })
    ResponseEntity<ResponseData<SurveyResponeDTO>> getSurveyByPostId(@RequestParam Long postId,
            @RequestParam Long userLogin) {
        ResponseData<SurveyResponeDTO> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.getSurveyDetailByPostId(postId, userLogin));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/survey/user/{userId}", "posts/survey/user/{userId}/" })
    ResponseEntity<ResponseData<List<BaseDTO>>> getSurveyPostsByUserId(@PathVariable Long userId) {
        ResponseData<List<BaseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.getAllPostByUserIdAndType(userId, PostType.SURVEY.getName()));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/survey/{postId}/result", "posts/survey/{postId}/result/" })
    ResponseEntity<ResponseData<List<SurveyResultResponseDTO>>> getResultSurveyByPostId(@PathVariable Long postId) {
        ResponseData<List<SurveyResultResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.getSurveyResultByPostId(postId));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/survey/prev-conduct", "posts/survey/prev-conduct" })
    ResponseEntity<ResponseData<List<SurveyPreviewResponseDTO>>> reviewSurvey(@RequestParam Long postId,
            Long userLogin) {
        ResponseData<List<SurveyPreviewResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.reviewSurveyResultByPostIdAndUserId(postId, userLogin));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/{id}/comments", "posts/{id}/comments/" })
    public ResponseEntity<ResponseData<List<CommentResponeseDTO>>> getComments(@PathVariable Long id) {
        ResponseData<List<CommentResponeseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.findCommentByPostId(id));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/group", "posts/group/" })
    public ResponseEntity<ResponseData<List<PostSearchResponseDTO>>> getByGroupId(@RequestParam String code,
            @RequestParam Long userLogin) {
        ResponseData<List<PostSearchResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.findAllByGroupCode(code, userLogin));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/survey/{postId}/update", "posts/survey/{postId}/update/" })
    public ResponseEntity<ResponseData<SurveyDTO>> getSurveyByPostId(@PathVariable Long postId) {
        ResponseData<SurveyDTO> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.getSurveyByPostId(postId));
        return ResponseEntity.ok(responseData);
    }

    //////////////////
    // Post
    //////////////////
    @PostMapping({ "posts/normal", "posts/normal/" })
    ResponseEntity<ResponseData<String>> updateOrSave(
            @RequestBody NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO) {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED, "add or update normal post success",
                postService.normalPostUpdateOrSave(normalPostUpdateOrSaveRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    @PostMapping({ "posts/user/save", "posts/user/save/" })
    ResponseEntity<ResponseData<?>> userSavePost(@RequestBody UserSavePostRequestDTO userSavePostRequestDTO) {
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.CREATED, "success",
                postService.userSavePost(userSavePostRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    @PostMapping({ "posts/recruitment", "posts/recruitment/" })
    ResponseEntity<ResponseData<String>> updateOrSave(
            @RequestBody RecruitmentPostUpdateOrSageRequestDTO recruitmentPostUpdateOrSageRequestDTO) {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED,
                "add or update recruitment post success",
                postService.recruitmentPostUpdateOrSave(recruitmentPostUpdateOrSageRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    @PostMapping({ "posts/survey", "posts/survey/" })
    ResponseEntity<ResponseData<?>> surveySave(@RequestBody SurveySaveRequestDTO surveySaveRequestDTO) {
        postService.saveSurvey(surveySaveRequestDTO);
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED, "success", null);
        return ResponseEntity.created(null).body(responseData);
    }

    @PostMapping({ "posts/survey/conduct", "posts/survey/conduct/" })
    ResponseEntity<ResponseData<?>> surveyAnswer(@RequestBody SurveyAnswerRequestDTO surveyAnswerRequestDTO) {
        postService.answerSurvey(surveyAnswerRequestDTO);
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED, "success", null);
        return ResponseEntity.created(null).body(responseData);
    }

    @PostMapping({ "posts/like", "posts/like/" })
    ResponseEntity<ResponseData<?>> like(@RequestBody LikeRequestDTO likeRequestDTO) {
        postService.likePost(likeRequestDTO);
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED, "success", null);
        return ResponseEntity.created(null).body(responseData);
    }

    @PostMapping({ "posts/comment", "posts/comment/" })
    ResponseEntity<ResponseData<?>> comment(@RequestBody CommentSaveRequestDTO commentSaveRequestDTO) {
        postService.commentPost(commentSaveRequestDTO);
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED, "success", null);
        return ResponseEntity.created(null).body(responseData);
    }

    @PostMapping({ "posts/group/user", "posts/group/user/" })
    public ResponseEntity<ResponseData<List<PostSearchResponseDTO>>> getByUserIdAndGroupCode(
            @RequestBody AllPostByUserAndGroupResponseDTO allPostByUserAndGroupResponseDTO) {
        ResponseData<List<PostSearchResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.getAllPostByUserIdAndGroupCode(allPostByUserAndGroupResponseDTO));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "posts/group/user/detail", "posts/group/user/detail/" })
    public ResponseEntity<ResponseData<UserDetailInGroupResponseDTO>> getByUserDetailInGroup(
            @RequestBody UserDetailInGroupRequestDTO userDetailInGroupRequestDTO) {
        ResponseData<UserDetailInGroupResponseDTO> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.getUserPageInGroup(userDetailInGroupRequestDTO));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "posts/acceptance", "posts/acceptance/" })
    public ResponseEntity<ResponseData<?>> acceptPost(
            @RequestBody PostGetRequestDTO postGetRequestDTO) {
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.CREATED, "success",
                postService.acceptPost(postGetRequestDTO.getPostId()));
        return ResponseEntity.created(null).body(responseData);
    }

    @PostMapping({ "posts/get", "posts/get/" })
    public ResponseEntity<ResponseData<PostSearchResponseDTO>> getById(
            @RequestBody PostUserGetRequestDTO postUserGetRequestDTO) {
        PostGetRequestDTO postGetRequestDTO = new PostGetRequestDTO();
        postGetRequestDTO.setPostId(postUserGetRequestDTO.getPostId());
        ResponseData<PostSearchResponseDTO> responseData = new ResponseData<>(HttpStatus.CREATED, "success",
                postService.findById(postGetRequestDTO,postUserGetRequestDTO.getUserId()));
        return ResponseEntity.created(null).body(responseData);
    }

    //////////////////
    // Put
    //////////////////
    @PutMapping({ "posts/normal", "posts/normal/" })
    ResponseEntity<ResponseData<String>> updateNormalPost(
            @RequestBody NormalPostUpdateRequestDTO normalPostUpdateRequestDTO) {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED, "add or update normal post success",
                postService.updateNormalPost(normalPostUpdateRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    @PutMapping({ "posts/recruitment", "posts/recruitment/" })
    ResponseEntity<ResponseData<String>> updateRecruitmentPost(
            @RequestBody RecruitmentPostUpdateRequestDTO recruitmentPostUpdateRequestDTO) {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED,
                "add or update recruitment post success",
                postService.updateRecruitmentPost(recruitmentPostUpdateRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    @PutMapping({ "posts/survey", "posts/survey/" })
    ResponseEntity<MessageResponseData> updateSurveyPost(@RequestBody SurveyDTO surveyDTO) {
        boolean isSuccess = postService.updateSurvey(surveyDTO);
        if (isSuccess) {
            return ResponseEntity.ok(new MessageResponseData(HttpStatus.OK, "survey_update_success"));
        }

        return ResponseEntity.badRequest()
                .body(new MessageResponseData(HttpStatus.BAD_REQUEST, "survey_update_failed"));
    }

    //////////////////
    // Delete
    //////////////////
    @DeleteMapping({ "posts/comment/delete", "posts/comment/delete/" })
    ResponseEntity<ResponseData<?>> deleteComment(@RequestBody CommentDeleteRequestDTO commentDeleteRequestDTO) {
        postService.deleteComment(commentDeleteRequestDTO);
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK, "success", null);
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping({ "posts/{postId}", "posts/{postId}/" })
    ResponseEntity<ResponseData<?>> deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK, "success", null);
        return ResponseEntity.ok(responseData);
    }
}
