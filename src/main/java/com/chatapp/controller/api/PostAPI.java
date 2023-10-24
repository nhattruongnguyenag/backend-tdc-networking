package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.request.CommentDeleteRequestDTO;
import com.chatapp.dto.request.CommentSaveRequestDTO;
import com.chatapp.dto.request.LikeRequestDTO;
import com.chatapp.dto.request.NormalPostUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.RecruitmentPostUpdateOrSageRequestDTO;
import com.chatapp.dto.request.SurveyAnswerRequestDTO;
import com.chatapp.dto.request.SurveySaveRequestDTO;
import com.chatapp.dto.response.CommentResponeseDTO;
import com.chatapp.dto.response.NormalPostResponseDTO;
import com.chatapp.dto.response.RecruitmentPostResponseDTO;
import com.chatapp.dto.response.SurveyResponeDTO;
import com.chatapp.service.PostService;

@RestController
@RequestMapping("/api")
public class PostAPI {
    @Autowired
    PostService postService;

    @GetMapping({"posts", "posts/"})
    public ResponseEntity<ResponseData<List<BaseDTO>>> findAll() {
        ResponseData<List<BaseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success", postService.findAll());
        return ResponseEntity.ok(responseData);
    }

    //normalPost api
    @GetMapping({ "posts/normal", "posts/normal/" })
    public ResponseEntity<ResponseData<List<NormalPostResponseDTO>>> findAllNormalPosts() {
        ResponseData<List<NormalPostResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success", postService.findAllNormalPost());
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "posts/normal", "posts/normal/" })
    ResponseEntity<ResponseData<String>> updateOrSave(@RequestBody NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO) {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED,"add or update normal post success",postService.normalPostUpdateOrSave(normalPostUpdateOrSaveRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    //recruitmentPost api
    @GetMapping({ "posts/recruitment", "posts/recruitment/" })
    public ResponseEntity<ResponseData<List<RecruitmentPostResponseDTO>>> findRecruitmentPosts() {
        ResponseData<List<RecruitmentPostResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success", postService.findAllRecruitmentPost());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "posts/recruitment/{postId}", "posts/recruitment/{postId}/" })
    public ResponseEntity<ResponseData<RecruitmentPostResponseDTO>> getRecruimentPostByPostId(@PathVariable Long postId) {
        ResponseData<RecruitmentPostResponseDTO> responseData = new ResponseData<>(HttpStatus.OK, "success", postService.getRecruimentDetailByPostId(postId));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "posts/recruitment", "posts/recruitment/" })
    ResponseEntity<ResponseData<String>> updateOrSave(@RequestBody RecruitmentPostUpdateOrSageRequestDTO recruitmentPostUpdateOrSageRequestDTO) {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED,"add or update recruitment post success",postService.recruitmentPostUpdateOrSave(recruitmentPostUpdateOrSageRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    @PostMapping({ "posts/survey", "posts/survey/" })
    ResponseEntity<ResponseData<?>> surveySave(@RequestBody SurveySaveRequestDTO surveySaveRequestDTO) {
        postService.saveSurvey(surveySaveRequestDTO);
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED,"success",null);
        return ResponseEntity.created(null).body(responseData);
    }

    @PostMapping({ "posts/survey/conduct", "posts/survey/conduct/" })
    ResponseEntity<ResponseData<?>> surveyAnswer(@RequestBody SurveyAnswerRequestDTO surveyAnswerRequestDTO) {
        postService.answerSurvey(surveyAnswerRequestDTO);
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED,"success",null);
        return ResponseEntity.created(null).body(responseData);
    }

    @GetMapping({ "posts/survey/{postId}", "posts/survey/{postId}/" })
    ResponseEntity<ResponseData<SurveyResponeDTO>> getSurveyByPostId(@PathVariable Long postId) {
        ResponseData<SurveyResponeDTO> responseData = new ResponseData<>(HttpStatus.OK,"success",postService.getSurveyDetailByPostId(postId));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "posts/like", "posts/like/" })
    ResponseEntity<ResponseData<?>> like(@RequestBody LikeRequestDTO likeRequestDTO) {
        postService.likePost(likeRequestDTO);
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED,"success",null);
        return ResponseEntity.created(null).body(responseData);
    }

    @PostMapping({ "posts/comment", "posts/comment/" })
    ResponseEntity<ResponseData<?>> comment(@RequestBody CommentSaveRequestDTO commentSaveRequestDTO) {
        postService.commentPost(commentSaveRequestDTO);
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED,"success",null);
        return ResponseEntity.created(null).body(responseData);
    }

    @DeleteMapping({ "posts/comment/delete", "posts/comment/delete/" })
    ResponseEntity<ResponseData<?>> deleteComment(@RequestBody CommentDeleteRequestDTO commentDeleteRequestDTO) {
        postService.deleteComment(commentDeleteRequestDTO);
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK,"success",null);
        return ResponseEntity.ok(responseData);
    }

    //recruitmentPost api
    @GetMapping({ "posts/{id}/comments", "posts/{id}/comments" })
    public ResponseEntity<ResponseData<List<CommentResponeseDTO>>> getComments(@PathVariable Long id) {
        ResponseData<List<CommentResponeseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success", postService.findCommentByPostId(id));
        return ResponseEntity.ok(responseData);
    }
}
