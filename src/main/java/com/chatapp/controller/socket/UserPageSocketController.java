package com.chatapp.controller.socket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.dto.request.UserFollowRequestDTO;
import com.chatapp.dto.request.UserGetRequestDTO;
import com.chatapp.dto.response.UserFollowResponseDTO;
import com.chatapp.service.UserService;

@RestController
public class UserPageSocketController {

    @Autowired
    private UserService userService;

    @MessageMapping({"/user/detail/follow", "/user/detail/follow/"})
    @SendTo({"/topic/user/detail/follow/me", "/topic/user/detail/follow/me"})
    public List<UserFollowResponseDTO> getFollowerInUserPage(@RequestBody UserFollowRequestDTO userFollowRequestDTO){
        if(userFollowRequestDTO.getUserFollowId() != null){
            userService.follow(userFollowRequestDTO);
        }
        UserGetRequestDTO userGetRequestDTO = new UserGetRequestDTO();
        userGetRequestDTO.setId(userFollowRequestDTO.getUserId());
        return userService.getFollowsByUserId(userGetRequestDTO);
    }

    @MessageMapping({"/user/detail/follow", "/user/detail/follow/"})
    @SendTo({"/topic/user/detail/follow/other", "/topic/user/detail/follow/other"})
    public List<UserFollowResponseDTO> getFollowingInUserPage(@RequestBody UserFollowRequestDTO userFollowRequestDTO){
        if(userFollowRequestDTO.getUserFollowId() != null){
            userService.follow(userFollowRequestDTO);
        }
        UserGetRequestDTO userGetRequestDTO = new UserGetRequestDTO();
        userGetRequestDTO.setId(userFollowRequestDTO.getUserId());
        return userService.getOtherPeopleFollowByUserId(userGetRequestDTO);
    }
}
