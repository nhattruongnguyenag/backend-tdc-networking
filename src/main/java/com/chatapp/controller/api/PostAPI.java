package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.request.NormalPostUpdateOrSaveRequestDTO;
import com.chatapp.dto.response.NormalPostResponeDTO;
import com.chatapp.dto.response.PostInfoResponeDTO;
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

    @GetMapping({ "posts/normalPosts", "posts/normalPosts/" })
    public ResponseEntity<ResponseData<List<NormalPostResponeDTO>>> findAllNormalPosts() {
        ResponseData<List<NormalPostResponeDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success", postService.findAllNormalPost());
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "posts/normalPosts", "posts/normalPosts/" })
    ResponseEntity<ResponseData<PostInfoResponeDTO>> updateOrSave(@RequestBody NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO) {
        ResponseData<PostInfoResponeDTO> responseData = new ResponseData<>(HttpStatus.ACCEPTED,"add or update faculty success",postService.normalPostUpdateOrSave(normalPostUpdateOrSaveRequestDTO));
        return ResponseEntity.ok(responseData);
    }
}
