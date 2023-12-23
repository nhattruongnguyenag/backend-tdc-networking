package com.chatapp.controller.api;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.request.post.PostFindRequestDTO;
import com.chatapp.dto.request.user.UserInfoFindRequestDTO;
import com.chatapp.dto.response.post.PostSearchResponseDTO;
import com.chatapp.dto.response.user.UserFindResponseDTO;
import com.chatapp.service.PostService;
import com.chatapp.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class SearchAPI {
   
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    //////////////////
    //Post
    //////////////////

    @PostMapping({ "find/user", "find/user/" })
    ResponseEntity<ResponseData<List<UserFindResponseDTO>>> findUser(@RequestBody UserInfoFindRequestDTO userInfoFindRequestDTO) {
        ResponseData<List<UserFindResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK,"success",userService.findUserByName(userInfoFindRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    @PostMapping({ "find/post", "find/post/" })
    ResponseEntity<ResponseData<List<PostSearchResponseDTO>>> findPost(@RequestBody PostFindRequestDTO postFindRequestDTO) {
        ResponseData<List<PostSearchResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK,"success",postService.findPostByName(postFindRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }
}
