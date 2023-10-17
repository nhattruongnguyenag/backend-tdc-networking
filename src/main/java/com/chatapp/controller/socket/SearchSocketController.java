package com.chatapp.controller.socket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;

import com.chatapp.dto.request.UserFollowRequestDTO;
import com.chatapp.dto.request.UserInfoFindRequestDTO;
import com.chatapp.dto.response.UserFindResponseDTO;
import com.chatapp.service.UserService;

public class SearchSocketController {

    @Autowired
    private UserService userService;

    @MessageMapping({"/find/user/follow", "/find/user/follow/"})
    @SendTo({"/topic/find/user", "/topic/find/user"})
    public List<UserFindResponseDTO> followUserInSearch(@RequestBody UserInfoFindRequestDTO userInfoFindRequestDTO){
        if(userInfoFindRequestDTO.getUserFollowId() != null){
            UserFollowRequestDTO userFollowRequestDTO = new UserFollowRequestDTO();
            userFollowRequestDTO.setUserId(userInfoFindRequestDTO.getUserId());
            userFollowRequestDTO.setUserFollowId(userInfoFindRequestDTO.getUserFollowId());
            userService.follow(userFollowRequestDTO);
        }
        return userService.findUserByName(userInfoFindRequestDTO);
    }
}
