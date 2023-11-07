package com.chatapp.controller.socket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.request.LikeRequestDTO;
import com.chatapp.dto.request.UserSavePostRequestDTO;
import com.chatapp.service.PostService;

@RestController
public class PostSocketController {

    @Autowired
    private PostService postService;

    @MessageMapping({ "/posts/{code}/listen", "/posts/{code}/listen/" })
    @SendTo({ "/topic/posts/{code}", "/topic/posts/{code}" })
    public List<BaseDTO> getPostsByCode(@DestinationVariable("code") String code) {
        return postService.findAllByRoleCode(code);
    }

    @MessageMapping({ "/posts/{code}/like", "/posts/{code}/like/" })
    @SendTo({ "/topic/posts/{code}", "/topic/posts/{code}" })
    public List<BaseDTO> saveMessage(@RequestBody LikeRequestDTO likeRequestDTO) {
        postService.likePost(likeRequestDTO);
        return postService.findAllByRoleCode(likeRequestDTO.getCode());
    }

    @MessageMapping({ "/posts/user/unsave", "/posts/user/unsave/" })
    @SendTo({ "/topic/posts/save/page", "/topic/posts/save/page/" })
    public List<BaseDTO> userSavePost(@RequestBody UserSavePostRequestDTO userSavePostRequestDTO) {
        postService.userSavePost(userSavePostRequestDTO);
        return postService.getPostSaveByUserId(userSavePostRequestDTO.getUserId());
    }
}