package com.chatapp.controller.socket;

import com.chatapp.dto.request.MessageRequestDTO;
import com.chatapp.dto.response.MessageResponseDTO;
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

    @MessageMapping({"/messages/{senderId}/{receiverId}", "/messages/{receiverId}/{senderId}"})
    @SendTo({"/topic/messages/{senderId}/{receiverId}", "/topic/messages/{receiverId}/{senderId}"})
    public List<MessageResponseDTO> saveMessage(@RequestBody MessageRequestDTO messageRequestDTO) {
        MessageResponseDTO messageResponseDTO = messageService.save(messageRequestDTO);
        firebaseMessagingService.sendNotificationToUser(messageResponseDTO.getSender().getId(), messageResponseDTO.getContent());
        return messageService.findBySenderAndReceiver(messageRequestDTO.getSenderId(), messageRequestDTO.getReceiverId());
    }

    @MessageMapping({"/messages/{senderId}/{receiverId}/listen", "/messages/{receiverId}/{senderId}/listen"})
    @SendTo({"/topic/messages/{senderId}/{receiverId}", "/topic/messages/{receiverId}/{senderId}"})
    public List<MessageResponseDTO> getMessages(@DestinationVariable("senderId") Long senderId, @DestinationVariable("receiverId") Long receiverId) {
        return messageService.findBySenderAndReceiver(senderId, receiverId);
    }
}
