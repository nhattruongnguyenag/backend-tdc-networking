package com.chatapp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.request.PostLogRequestDTO;
import com.chatapp.service.PostService;

@RestController
@RequestMapping("/api")
public class LogAPI {
    @Autowired
    PostService postService;

    @PostMapping({ "approval/post/log", "approval/post/log/" })
    ResponseEntity<ResponseData<?>> postApprovalLog(@RequestBody PostLogRequestDTO postLogRequestDTO) {
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.CREATED, "success",
                postService.addPostLog(postLogRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    @DeleteMapping({ "approval/post/log/delete/{postId}", "approval/post/log/delete/{postId}/" })
    ResponseEntity<ResponseData<?>> deletePostApprovalLog(@PathVariable Long postId) {
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.deletePostLog(postId));
        return ResponseEntity.ok(responseData);
    }
}
