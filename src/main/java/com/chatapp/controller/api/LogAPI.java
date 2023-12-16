package com.chatapp.controller.api;

import com.chatapp.commond.MessageResponseData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.request.post.log.PostLogRequestDTO;
import com.chatapp.dto.response.post.log.PostRejectLogDTO;
import com.chatapp.service.PostService;

@RestController
@RequestMapping("/api")
public class LogAPI {
    @Autowired
    PostService postService;

    //////////////////
    //Get
    //////////////////
    @GetMapping("test")
    public String test() {
        return "TEST CI/CD successful";
    }

    @GetMapping({"approval/log/post/{postId}"})
    ResponseEntity<?> getRejectLogByPostId(@PathVariable("postId") Long postId) {
        PostRejectLogDTO dto = postService.findRejectLogByPostId(postId);
        ResponseEntity<?> responseEntity = new ResponseEntity(new MessageResponseData(HttpStatus.NOT_FOUND, "not_found"), HttpStatus.NOT_FOUND);
        if (dto != null) {
            responseEntity = new ResponseEntity(new ResponseData<>(HttpStatus.OK, "success", dto), HttpStatus.OK);
        }

        return responseEntity;
    }

    //////////////////
    //Post
    //////////////////
    @PostMapping({ "approval/post/log", "approval/post/log/" })
    ResponseEntity<ResponseData<?>> postApprovalLog(@RequestBody PostLogRequestDTO postLogRequestDTO) {
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.CREATED, "success",
                postService.addPostLog(postLogRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    //////////////////
    //Delete
    //////////////////
    @DeleteMapping({ "approval/post/log/delete/{postId}", "approval/post/log/delete/{postId}/" })
    ResponseEntity<ResponseData<?>> deletePostApprovalLog(@PathVariable Long postId) {
        ResponseData<?> responseData = new ResponseData<>(HttpStatus.OK, "success",
                postService.deletePostLog(postId));
        return ResponseEntity.ok(responseData);
    }
}
