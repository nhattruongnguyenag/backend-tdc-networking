package com.chatapp.controller.api;

import com.chatapp.dto.Pagination;
import com.chatapp.dto.request.message.MessageSaveRequestDTO;
import com.chatapp.dto.request.post.PostSearchRequestDTO;
import com.chatapp.dto.response.CountUnreadConversationResponseDTO;
import com.chatapp.dto.response.conversation.ConversationResponseDTO;
import com.chatapp.dto.response.message.MessageResponseDTO;
import com.chatapp.service.ConversationService;
import com.chatapp.service.MessageService;
import com.chatapp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    List<MessageResponseDTO> findMessagesBySenderOrReceiver(@PathVariable("senderId") Long senderId, @PathVariable("receiverId") Long receiverId, @RequestParam Map<String, Object> params) {
        Pagination pagination = CommonUtils.mapToObject(params, Pagination.class);
        return messageService.findBySenderAndReceiver(senderId, receiverId, pagination);
    }

    @GetMapping("/conversation/count/user/{senderId}")
    CountUnreadConversationResponseDTO countBySender_IdAndMessages_Status(@PathVariable("senderId")long senderId) {
        CountUnreadConversationResponseDTO countUnreadConversationResponseDTO = new CountUnreadConversationResponseDTO();
        countUnreadConversationResponseDTO.setCount(conversationService.countBySender_IdAndMessages_Status(senderId, 0));
        return countUnreadConversationResponseDTO;
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
    MessageResponseDTO save(@RequestBody MessageSaveRequestDTO messageRequestDTO) {
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
