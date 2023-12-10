package com.chatapp.controller.socket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.dto.request.user.UserFindRequestDTO;
import com.chatapp.dto.request.user.UserGetRequestDTO;
import com.chatapp.dto.request.user.follow.UserFollowRequestDTO;
import com.chatapp.dto.response.user.follow.UserFollowResponseDTO;
import com.chatapp.service.UserService;

@RestController
public class UserPageSocketController {

    @Autowired
    private UserService userService;

    @MessageMapping({"/user/detail/follow/following", "/user/detail/follow/following"})
    @SendTo({"/topic/user/detail/follow/following", "/topic/user/detail/follow/following/"})
    public List<UserFollowResponseDTO> getFollowerInUserPage(@RequestBody UserFollowRequestDTO userFollowRequestDTO){
        if(userFollowRequestDTO.getUserFollowId() != null){
            userService.follow(userFollowRequestDTO);
        }
        UserGetRequestDTO userGetRequestDTO = new UserGetRequestDTO();
        userGetRequestDTO.setId(userFollowRequestDTO.getUserId());
        return userService.getFollowsByUserId(userGetRequestDTO);
    }

    @MessageMapping({"/user/detail/follow/follower", "/user/detail/follow/follower/"})
    @SendTo({"/topic/user/detail/follow/follower", "/topic/user/detail/follow/follower/"})
    public List<UserFollowResponseDTO> getFollowingInUserPage(@RequestBody UserFollowRequestDTO userFollowRequestDTO){
        if(userFollowRequestDTO.getUserFollowId() != null){
            userService.follow(userFollowRequestDTO);
        }
        UserGetRequestDTO userGetRequestDTO = new UserGetRequestDTO();
        userGetRequestDTO.setId(userFollowRequestDTO.getUserId());
        return userService.getOtherPeopleFollowByUserId(userGetRequestDTO);
    }

    @MessageMapping({"/user/detail/follow/following/search", "/user/detail/follow/following/search"})
    @SendTo({"/topic/user/detail/follow/following", "/topic/user/detail/follow/following/"})
    public List<UserFollowResponseDTO> searchInFollowingUserPage(@RequestBody UserFindRequestDTO userFindRequestDTO){
        return userService.findUserByNameInListFollowingByUserId(userFindRequestDTO);
    }

    @MessageMapping({"/user/detail/follow/follower/search", "/user/detail/follow/follower/search"})
    @SendTo({"/topic/user/detail/follow/follower", "/topic/user/detail/follow/follower/"})
    public List<UserFollowResponseDTO> searchInFollowerUserPage(@RequestBody UserFindRequestDTO userFindRequestDTO){
        return userService.findUserByNameInListFollowerByUserId(userFindRequestDTO);
    }
}
