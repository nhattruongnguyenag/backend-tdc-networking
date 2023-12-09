package com.chatapp.controller.socket;

import com.chatapp.dto.Pagination;
import com.chatapp.dto.request.message.MessageSaveRequestDTO;
import com.chatapp.dto.response.message.MessageResponseDTO;
import com.chatapp.service.FirebaseMessagingService;
import com.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageSocketController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private FirebaseMessagingService firebaseMessagingService;

    @MessageMapping({"/messages/{senderId}/{receiverId}", "/messages/{receiverId}/{senderId}/"})
    @SendTo({"/topic/messages/{senderId}/{receiverId}", "/topic/messages/{receiverId}/{senderId}/"})
    public List<MessageResponseDTO> saveMessage(@RequestBody MessageSaveRequestDTO messageRequestDTO) {
        messageService.save(messageRequestDTO);
        firebaseMessagingService.sendNotificationToUser(messageRequestDTO.getReceiverId(), messageRequestDTO.getContent());
        messageService.updateMessagesToReadState(messageRequestDTO.getSenderId(), messageRequestDTO.getReceiverId());
        return messageService.findBySenderAndReceiver(messageRequestDTO.getSenderId(), messageRequestDTO.getReceiverId(), new Pagination(1));
    }

    @MessageMapping({"/messages/{senderId}/{receiverId}/listen", "/messages/{receiverId}/{senderId}/listen/"})
    @SendTo({"/topic/messages/{senderId}/{receiverId}", "/topic/messages/{receiverId}/{senderId}/"})
    public List<MessageResponseDTO> getMessages(@DestinationVariable("senderId") Long senderId, @DestinationVariable("receiverId") Long receiverId) {
        messageService.updateMessagesToReadState(senderId, receiverId);
        return messageService.findBySenderAndReceiver(senderId, receiverId, new Pagination(1, 5));
    }
}
