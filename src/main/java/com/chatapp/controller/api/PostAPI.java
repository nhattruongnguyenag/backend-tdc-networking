package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.request.NormalPostUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.RecruitmentPostUpdateOrSageRequestDTO;
import com.chatapp.dto.response.NormalPostResponeDTO;
import com.chatapp.dto.response.PostInfoResponeDTO;
import com.chatapp.dto.response.RecruitmentPostResponeDTO;
import com.chatapp.service.PostService;

@RestController
@RequestMapping("/api")
public class PostAPI {
    @Autowired
    PostService postService;

    @GetMapping({"posts", "posts/"})
    public List<PostInfoResponeDTO> findAll() {
        return postService.findAll();
    }

    //normalPost api
    @GetMapping({ "posts/normalPosts", "posts/normalPosts/" })
    public ResponseEntity<ResponseData<List<NormalPostResponeDTO>>> findAllNormalPosts() {
        ResponseData<List<NormalPostResponeDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success", postService.findAllNormalPost());
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "posts/normalPosts", "posts/normalPosts/" })
    ResponseEntity<ResponseData<PostInfoResponeDTO>> updateOrSave(@RequestBody NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO) {
        ResponseData<PostInfoResponeDTO> responseData = new ResponseData<>(HttpStatus.CREATED,"add or update normal post success",postService.normalPostUpdateOrSave(normalPostUpdateOrSaveRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    //recruitmentPost api
    @GetMapping({ "posts/recruitmentPosts", "posts/recruitmentPosts/" })
    public ResponseEntity<ResponseData<List<RecruitmentPostResponeDTO>>> findRecruitmentPosts() {
        ResponseData<List<RecruitmentPostResponeDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success", postService.findAllRecruitmentPost());
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "posts/recruitmentPosts", "posts/recruitmentPosts/" })
    ResponseEntity<ResponseData<PostInfoResponeDTO>> updateOrSave(@RequestBody RecruitmentPostUpdateOrSageRequestDTO recruitmentPostUpdateOrSageRequestDTO) {
        ResponseData<PostInfoResponeDTO> responseData = new ResponseData<>(HttpStatus.CREATED,"add or update recruitment post success",postService.recruitmentPostUpdateOrSave(recruitmentPostUpdateOrSageRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }
}
