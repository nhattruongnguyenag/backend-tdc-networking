package com.chatapp.controller.socket;

import com.chatapp.constant.UserStatus;
import com.chatapp.dto.response.conversation.ConversationResponseDTO;
import com.chatapp.service.ConversationService;
import com.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConversationSocketController {
    @Autowired
    private ConversationService conversationService;
    @Autowired
    private UserService userService;

    @MessageMapping({"/conversations/listen/{senderId}", "/conversations/listen/{senderId}/"})
    @SendTo({"/topic/conversations", "/topic/conversations/"})
    public List<ConversationResponseDTO> getConversations(@DestinationVariable("senderId") Long senderId) {
        return conversationService.findBySender(senderId);
    }

    @MessageMapping({"/conversations/online/{senderId}", "/conversations/online/{senderId}/"})
    @SendTo({"/topic/conversations", "/topic/conversations/"})
    public List<ConversationResponseDTO> updateUserStatusOnline(@DestinationVariable("senderId") Long senderId) {
        userService.changeStatus(senderId, UserStatus.ONLINE);
        return conversationService.findBySender(senderId);
    }

    @MessageMapping({"/conversations/offline/{senderId}", "/conversations/offline/{senderId}/"})
    @SendTo({"/topic/conversations", "/topic/conversations/"})
    public List<ConversationResponseDTO> updateUserStatusOffline(@DestinationVariable("senderId") Long senderId) {
        userService.changeStatus(senderId, UserStatus.OFFLINE);
        return conversationService.findBySender(senderId);
    }
}
