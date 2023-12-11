package com.chatapp.controller.socket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.dto.request.post.PostFindRequestDTO;
import com.chatapp.dto.request.user.UserInfoFindRequestDTO;
import com.chatapp.dto.request.user.follow.UserFollowRequestDTO;
import com.chatapp.dto.request.user.like.LikeRequestDTO;
import com.chatapp.dto.request.user.post_save.UserSavePostRequestDTO;
import com.chatapp.dto.response.post.PostSearchResponseDTO;
import com.chatapp.dto.response.user.UserFindResponseDTO;
import com.chatapp.service.PostService;
import com.chatapp.service.UserService;

@RestController
public class SearchSocketController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @MessageMapping({ "/find/user/follow", "/find/user/follow/" })
    @SendTo({ "/topic/find/user", "/topic/find/user" })
    public List<UserFindResponseDTO> followUserInSearch(@RequestBody UserInfoFindRequestDTO userInfoFindRequestDTO) {
        if (userInfoFindRequestDTO.getUserFollowId() != null) {
            UserFollowRequestDTO userFollowRequestDTO = new UserFollowRequestDTO();
            userFollowRequestDTO.setUserId(userInfoFindRequestDTO.getUserId());
            userFollowRequestDTO.setUserFollowId(userInfoFindRequestDTO.getUserFollowId());
            userService.follow(userFollowRequestDTO);
        }
        return userService.findUserByName(userInfoFindRequestDTO);
    }

    @MessageMapping({ "/find/post/unsave", "/find/post/unsave/" })
    @SendTo({ "/topic/find/post", "/topic/find/post/" })
    public List<PostSearchResponseDTO> userSavePost(@RequestBody UserSavePostRequestDTO userSavePostRequestDTO) {
        if (userSavePostRequestDTO.getPostId() != null) {
            postService.userSavePost(userSavePostRequestDTO);
        }
        PostFindRequestDTO postFindRequestDTO = new PostFindRequestDTO();
        postFindRequestDTO.setName(userSavePostRequestDTO.getSearch());
        postFindRequestDTO.setType(userSavePostRequestDTO.getType());
        postFindRequestDTO.setUserLogin(userSavePostRequestDTO.getUserId());
        return postService.findPostByName(postFindRequestDTO);
    }

    @MessageMapping({ "/find/post/like", "/find/post/like/" })
    @SendTo({ "/topic/find/post", "/topic/find/post/" })
    public List<PostSearchResponseDTO> userLikePost(@RequestBody LikeRequestDTO likeRequestDTO) {
        if (likeRequestDTO.getPostId() != null) {
            postService.likePost(likeRequestDTO);
        }
        PostFindRequestDTO postFindRequestDTO = new PostFindRequestDTO();
        postFindRequestDTO.setName(likeRequestDTO.getSearch());
        postFindRequestDTO.setType(likeRequestDTO.getType());
        postFindRequestDTO.setUserLogin(likeRequestDTO.getUserId());
        return postService.findPostByName(postFindRequestDTO);
    }
}
