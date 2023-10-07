package com.chatapp.controller.socket;

import com.chatapp.dto.response.ConversationResponseDTO;
import com.chatapp.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConversationSocketController {
    @Autowired
    private ConversationService conversationService;

    @MessageMapping({"/conversations/listen", "/conversations/listen/"})
    @SendTo({"/topic/conversations", "/topic/conversations/"})
    public List<ConversationResponseDTO> getConversations(Long senderId) {
        return conversationService.findBySender(senderId);
    }
}
