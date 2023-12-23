package com.chatapp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.dto.request.email.EmailSendRequestionDTO;
import com.chatapp.service.EmailService;

@RestController
@RequestMapping("/api")
public class EmailSendAPI {
    @Autowired
    private EmailService emailService;

    //////////////////
    //Post
    //////////////////
    @PostMapping({ "/mail", "/mail/" })
    public ResponseEntity<?> sendMail(@RequestBody EmailSendRequestionDTO emailSendRequestionDTO) {
        try {
            emailService.sendEmail(emailSendRequestionDTO.getTo(), emailSendRequestionDTO.getSubject(),
                    emailSendRequestionDTO.getContent());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
