package com.chatapp.controller.api;

import java.util.List;
import java.util.Map;

import com.chatapp.dto.request.*;
import com.chatapp.dto.response.*;
import com.chatapp.dto.response.postSearch.PostSearchResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.BaseDTO;
import com.chatapp.enums.PostType;
import com.chatapp.service.PostService;
import com.google.firebase.database.annotations.Nullable;

@RestController
@RequestMapping("/api")
public class PostAPI {
    @Autowired
    PostService postService;

    @GetMapping({ "posts/all", "posts/all/" })
    public ResponseEntity<ResponseData<List<BaseDTO>>> findAll() {
        ResponseData<List<BaseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success", postService.findAll());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/search", "posts/search/" })
    public ResponseEntity<ResponseData<List<PostSearchResponseDTO>>> findPosts(@RequestParam Map<String, String> params) {
        PostSearchRequestDTO dto = new PostSearchRequestDTO();
        dto.setGroup(params.get("group"));
        dto.setOwnerFaculty(params.get("ownerFaculty"));
        dto.setStatus(params.get("status"));
        ResponseData<List<PostSearchResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success", postService.findPosts(dto));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "posts/user/save", "posts/user/save/" })
    ResponseEntity<ResponseData<?>> userSavePost(@RequestBody UserSavePostRequestDTO userSavePostRequestDTO) {
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.CREATED, "success",
                postService.userSavePost(userSavePostRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    @GetMapping({ "posts/user/save/{userId}", "posts/user/save/{userId}/" })
    ResponseEntity<ResponseData<?>> userSavePost(@PathVariable Long userId) {
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.getPostSaveByUserId(userId));
        return ResponseEntity.ok(responseData);
    }

    // normalPost api
    @GetMapping({ "posts/normal", "posts/normal/" })
    public ResponseEntity<ResponseData<List<NormalPostResponseDTO>>> findAllNormalPosts() {
        ResponseData<List<NormalPostResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.findAllNormalPost());
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "posts/normal", "posts/normal/" })
    ResponseEntity<ResponseData<String>> updateOrSave(
            @RequestBody NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO) {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED, "add or update normal post success",
                postService.normalPostUpdateOrSave(normalPostUpdateOrSaveRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    @PutMapping({ "posts/normal", "posts/normal/" })
    ResponseEntity<ResponseData<String>> updateNormalPost(
            @RequestBody NormalPostUpdateRequestDTO normalPostUpdateRequestDTO) {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED, "add or update normal post success",
                postService.updateNormalPost(normalPostUpdateRequestDTO));
        return ResponseEntity.created(null).body(responseData);
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

    @PostMapping({ "posts/recruitment", "posts/recruitment/" })
    ResponseEntity<ResponseData<String>> updateOrSave(
            @RequestBody RecruitmentPostUpdateOrSageRequestDTO recruitmentPostUpdateOrSageRequestDTO) {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED,
                "add or update recruitment post success",
                postService.recruitmentPostUpdateOrSave(recruitmentPostUpdateOrSageRequestDTO));
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

    @GetMapping({ "posts/recruitment/user/{userId}", "posts/recruitment/user/{userId}/" })
    ResponseEntity<ResponseData<List<BaseDTO>>> getRecruitmentPostsByUserId(@PathVariable Long userId) {
        ResponseData<List<BaseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.getAllPostByUserIdAndType(userId, PostType.RECRUIMENT.getName()));
        return ResponseEntity.ok(responseData);
    }

    // survey api
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

    // other api
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

    @DeleteMapping({ "posts/comment/delete", "posts/comment/delete/" })
    ResponseEntity<ResponseData<?>> deleteComment(@RequestBody CommentDeleteRequestDTO commentDeleteRequestDTO) {
        postService.deleteComment(commentDeleteRequestDTO);
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK, "success", null);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/{id}/comments", "posts/{id}/comments/" })
    public ResponseEntity<ResponseData<List<CommentResponeseDTO>>> getComments(@PathVariable Long id) {
        ResponseData<List<CommentResponeseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.findCommentByPostId(id));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/group", "posts/group/" })
    public ResponseEntity<ResponseData<List<BaseDTO>>> getByGroupId(@RequestParam String code,
            @RequestParam Long userLogin) {
        ResponseData<List<BaseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.findAllByGroupCode(code, userLogin));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "posts/group/user", "posts/group/user/" })
    public ResponseEntity<ResponseData<List<BaseDTO>>> getByUserIdAndGroupCode(
            @RequestBody AllPostByUserAndGroupResponseDTO allPostByUserAndGroupResponseDTO) {
        ResponseData<List<BaseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.getAllPostByUserIdAndGroupCode(allPostByUserAndGroupResponseDTO));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping({ "posts/{postId}", "posts/{postId}/" })
    ResponseEntity<ResponseData<?>> deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK, "success", null);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "posts/group/user/detail", "posts/group/user/detail/" })
    public ResponseEntity<ResponseData<UserDetailInGroupResponseDTO>> getByUserDetailInGroup(
            @RequestBody UserDetailInGroupRequestDTO userDetailInGroupRequestDTO) {
        ResponseData<UserDetailInGroupResponseDTO> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.getUserPageInGroup(userDetailInGroupRequestDTO));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "posts/acceptance", "posts/acceptance" })
    public ResponseEntity<ResponseData<?>> acceptPost(
            @RequestBody PostGetRequestDTO postGetRequestDTO) {
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.CREATED, "success",
                postService.acceptPost(postGetRequestDTO.getPostId()));
        return ResponseEntity.created(null).body(responseData);
    }
}
