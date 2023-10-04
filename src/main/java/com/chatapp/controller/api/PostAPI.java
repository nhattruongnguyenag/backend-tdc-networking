package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.request.NormalPostUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.RecruitmentPostUpdateOrSageRequestDTO;
import com.chatapp.dto.request.SurveySaveRequestDTO;
import com.chatapp.dto.response.NormalPostResponseDTO;
import com.chatapp.dto.response.PostInfoResponseDTO;
import com.chatapp.dto.response.RecruitmentPostResponseDTO;
import com.chatapp.service.PostService;

@RestController
@RequestMapping("/api")
public class PostAPI {
    @Autowired
    PostService postService;

    @GetMapping({"posts", "posts/"})
    public List<PostInfoResponseDTO> findAll() {
        return postService.findAll();
    }

    //normalPost api
    @GetMapping({ "posts/normal", "posts/normal/" })
    public ResponseEntity<ResponseData<List<NormalPostResponseDTO>>> findAllNormalPosts() {
        ResponseData<List<NormalPostResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success", postService.findAllNormalPost());
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "posts/normal", "posts/normal/" })
    ResponseEntity<ResponseData<PostInfoResponseDTO>> updateOrSave(@RequestBody NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO) {
        ResponseData<PostInfoResponseDTO> responseData = new ResponseData<>(HttpStatus.CREATED,"add or update normal post success",postService.normalPostUpdateOrSave(normalPostUpdateOrSaveRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    //recruitmentPost api
    @GetMapping({ "posts/recruitment", "posts/recruitment/" })
    public ResponseEntity<ResponseData<List<RecruitmentPostResponseDTO>>> findRecruitmentPosts() {
        ResponseData<List<RecruitmentPostResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success", postService.findAllRecruitmentPost());
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "posts/recruitment", "posts/recruitment/" })
    ResponseEntity<ResponseData<PostInfoResponseDTO>> updateOrSave(@RequestBody RecruitmentPostUpdateOrSageRequestDTO recruitmentPostUpdateOrSageRequestDTO) {
        ResponseData<PostInfoResponseDTO> responseData = new ResponseData<>(HttpStatus.CREATED,"add or update recruitment post success",postService.recruitmentPostUpdateOrSave(recruitmentPostUpdateOrSageRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    @PostMapping({ "posts/survey", "posts/survey/" })
    ResponseEntity<ResponseData<?>> surveySave(@RequestBody SurveySaveRequestDTO surveySaveRequestDTO) {
        postService.saveSurvey(surveySaveRequestDTO);
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED,"success",null);
        return ResponseEntity.created(null).body(responseData);
    }
}
