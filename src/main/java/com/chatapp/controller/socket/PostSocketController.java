package com.chatapp.controller.socket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import com.chatapp.dto.request.post.PostGetRequestDTO;
import com.chatapp.dto.request.user.like.LikeRequestDTO;
import com.chatapp.dto.request.user.post_save.UserSavePostFindRequestDTO;
import com.chatapp.dto.request.user.post_save.UserSavePostRequestDTO;
import com.chatapp.dto.response.post.PostSearchResponseDTO;
import com.chatapp.service.PostService;

@RestController
public class PostSocketController {

    @Autowired
    private PostService postService;

    @MessageMapping({ "/posts/group/{code}/listen/{userlogin}", "/posts/group/{code}/listen/{userlogin}/" })
    @SendTo({ "/topic/posts/group/{code}", "/topic/posts/group/{code}/" })
    public List<PostSearchResponseDTO> getPostsByCode(@DestinationVariable("code") String code,
            @DestinationVariable("userLogin") Long userLogin) {
        return postService.findAllByGroupCode(code, userLogin);
    }

    @MessageMapping({ "/posts/group/{code}/like", "/posts/group/{code}/like/" })
    @SendTo({ "/topic/posts/group/{code}", "/topic/posts/group/{code}" })
    public List<PostSearchResponseDTO> saveMessage(@DestinationVariable("code") String code,
            @RequestBody LikeRequestDTO likeRequestDTO) {
        postService.likePost(likeRequestDTO);
        return postService.findAllByGroupCode(code, likeRequestDTO.getUserId());
    }

    @MessageMapping({ "/posts/save/user/unsave", "/posts/save/user/unsave/" })
    @SendTo({ "/topic/posts/save", "/topic/posts/save/" })
    public List<PostSearchResponseDTO> userSavePost(@RequestBody UserSavePostRequestDTO userSavePostRequestDTO) {
        postService.userSavePost(userSavePostRequestDTO);
        return postService.getPostSaveByUserId(userSavePostRequestDTO.getUserId());
    }

    @MessageMapping({ "/posts/save/user/like", "/posts/save/user/like/" })
    @SendTo({ "/topic/posts/save", "/topic/posts/save" })
    public List<PostSearchResponseDTO> userLikePost(@RequestBody LikeRequestDTO likeRequestDTO) {
        postService.likePost(likeRequestDTO);
        return postService.getPostSaveByUserId(likeRequestDTO.getUserId());
    }

    @MessageMapping({ "/posts/detail/unsave", "/posts/detail/unsave" })
    @SendTo({ "/topic/posts/{postId}/detail", "/topic/posts/{postId}/detail/" })
    public PostSearchResponseDTO savePost(@RequestBody UserSavePostRequestDTO userSavePostRequestDTO,
            @DestinationVariable("postId") String postId) {
        if (userSavePostRequestDTO.getPostId() != null && userSavePostRequestDTO.getUserId() != null) {
            postService.userSavePost(userSavePostRequestDTO);
        }
        PostGetRequestDTO postGetRequestDTO = new PostGetRequestDTO();
        postGetRequestDTO.setPostId(Long.valueOf(postId));
        return postService.findById(postGetRequestDTO);
    }

    @MessageMapping({ "/posts/detail/like", "/posts/detail/like/" })
    @SendTo({ "/topic/posts/{postId}/detail", "/topic/posts/{postId}/detail/" })
    public PostSearchResponseDTO likePost(@RequestBody LikeRequestDTO likeRequestDTO,
            @DestinationVariable("postId") String postId) {
        if (likeRequestDTO.getPostId() != null && likeRequestDTO.getUserId() != null) {
            postService.likePost(likeRequestDTO);
        }
        PostGetRequestDTO postGetRequestDTO = new PostGetRequestDTO();
        postGetRequestDTO.setPostId(Long.valueOf(postId));
        return postService.findById(postGetRequestDTO);
    }

    @MessageMapping({ "/posts/group/{code}/unsave", "/posts/group/{code}/unsave/" })
    @SendTo({ "/topic/posts/group/{code}", "/topic/posts/group/{code}/" })
    public List<PostSearchResponseDTO> userSavePostInGroupScreen(
            @RequestBody UserSavePostRequestDTO userSavePostRequestDTO, @DestinationVariable("code") String code) {
        postService.userSavePost(userSavePostRequestDTO);
        return postService.findAllByGroupCode(code, userSavePostRequestDTO.getUserId());
    }
}