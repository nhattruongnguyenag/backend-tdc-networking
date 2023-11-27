package com.chatapp.controller.socket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import com.chatapp.dto.request.post.comment.CommentDeleteRequestDTO;
import com.chatapp.dto.request.post.comment.CommentSaveRequestDTO;
import com.chatapp.dto.response.post.comment.CommentResponeseDTO;
import com.chatapp.service.PostService;

@RestController
public class CommentSocketController {

    @Autowired
    private PostService postService;

    @MessageMapping({"/posts/{postId}/comments", "/posts/{postId}/comments/"})
    @SendTo({"/topic/posts/{postId}", "/topic/posts/{postId}"})
    public List<CommentResponeseDTO> saveComment(@RequestBody CommentSaveRequestDTO commentSaveRequestDTO) {
        postService.commentPost(commentSaveRequestDTO);
        return postService.findCommentByPostId(commentSaveRequestDTO.getPostId());
    }

    @MessageMapping({"/posts/{postId}/comments/delete", "/posts/{postId}/comments/delete"})
    @SendTo({"/topic/posts/{postId}", "/topic/posts/{postId}"})
    public List<CommentResponeseDTO> deleteComment(@RequestBody CommentDeleteRequestDTO commentDeleteRequestDTO) {
        postService.deleteComment(commentDeleteRequestDTO);
        return postService.findCommentByPostId(commentDeleteRequestDTO.getPostId());
    }

    @MessageMapping({"/posts/{postId}/comments/listen", "/posts/{postId}/comments/listen/"})
    @SendTo({"/topic/posts/{postId}", "/topic/posts/{postId}"})
    public List<CommentResponeseDTO> getComments(@DestinationVariable("postId") Long postId){
        return postService.findCommentByPostId(postId);
    } 
}
