package com.chatapp.controller.api;

import com.chatapp.dto.request.message.MessageRequestDTO;
import com.chatapp.dto.response.conversation.ConversationResponseDTO;
import com.chatapp.dto.response.message.MessageResponseDTO;
import com.chatapp.service.ConversationService;
import com.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageAPI {
    @Autowired
    private MessageService messageService;
    @Autowired
    private ConversationService conversationService;

    //////////////////
    //Get
    //////////////////
    @GetMapping("/messages/{senderId}/{receiverId}")
    List<MessageResponseDTO> findMessagesBySenderOrReceiver(@PathVariable("senderId") Long senderId, @PathVariable("receiverId") Long receiverId) {
        return messageService.findBySenderAndReceiver(senderId, receiverId);
    }

    @GetMapping("/conversations/{userId}")
    List<ConversationResponseDTO> listConversations(@PathVariable("userId") Long userId) {
        return conversationService.findBySender(userId);
    }

    @GetMapping("/messages/{conversationId}")
    List<MessageResponseDTO> messageEntities(@PathVariable("conversationId") Long conversationId) {
        return messageService.findByConversations_Id(conversationId);
    }


    //////////////////
    //Post
    //////////////////
    @PostMapping("/messages")
    MessageResponseDTO save(@RequestBody MessageRequestDTO messageRequestDTO) {
        return messageService.save(messageRequestDTO);
    }
    

    //////////////////
    //Delete
    //////////////////
    @DeleteMapping("/messages/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        messageService.delete(id);
        return ResponseEntity.ok().build();
    }
}
