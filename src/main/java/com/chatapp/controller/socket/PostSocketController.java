package com.chatapp.controller.socket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.request.LikeRequestDTO;
import com.chatapp.service.PostService;

@RestController
public class PostSocketController {

    @Autowired
    private PostService postService;

    @MessageMapping({"/posts/{code}/listen", "/posts/{code}/listen/"})
    @SendTo({"/topic/posts/{code}", "/topic/posts/{code}"})
    public List<BaseDTO> getPostsByCode(@DestinationVariable("code") String code){
        return postService.findAllByRoleCode(code);
    }

    @MessageMapping({"/posts/{code}/{postId}/like", "/posts/{code}/{postId}/like/"})
    @SendTo({"/topic/posts/{code}", "/topic/posts/{code}"})
    public List<BaseDTO> saveMessage(@RequestBody LikeRequestDTO likeRequestDTO) {
        postService.likePost(likeRequestDTO);
        return postService.findAllByRoleCode(likeRequestDTO.getCode());
    }
}