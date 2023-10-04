package com.chatapp.controller.api;

import com.chatapp.dto.request.MessageRequestDTO;
import com.chatapp.dto.response.ConversationResponseDTO;
import com.chatapp.dto.response.MessageResponseDTO;
import com.chatapp.entity.MessageEntity;
import com.chatapp.repository.MessageRepository;
import com.chatapp.service.ConversationService;
import com.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageAPI {
    @Autowired
    private MessageService messageService;
    @Autowired
    private ConversationService conversationService;

    @GetMapping("/messages/{senderId}/{receiverId}")
    List<MessageResponseDTO> findMessagesBySenderOrReceiver(@PathVariable("senderId") Long senderId, @PathVariable("receiverId") Long receiverId) {
        return messageService.findBySenderAndReceiver(senderId, receiverId);
    }

    @Autowired
    private MessageRepository messageRepository;
    @GetMapping("/messages/{conversationId}")
    List<MessageResponseDTO> messageEntities(@PathVariable("conversationId") Long conversationId) {
        return messageService.findByConversations_Id(conversationId);
    }

    @PostMapping("/messages")
    MessageResponseDTO save(@RequestBody MessageRequestDTO messageRequestDTO) {
        return messageService.save(messageRequestDTO);
    }

    @DeleteMapping("/messages/{id}")
    ResponseEntity delete(@PathVariable("id") Long id) {
        messageService.delete(id);
        return ResponseEntity.ok().build();
    }
}
