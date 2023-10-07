package com.chatapp.controller.api;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.request.PushNotificationRequestDTO;
import com.chatapp.service.FirebaseMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FirebaseMessagingAPI {
    @Autowired
    private FirebaseMessagingService firebaseMessagingService;

    @PostMapping("/notification/token")
    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequestDTO request) {
        boolean isSuccess = firebaseMessagingService.sendNotificationToToken(request);
        if (isSuccess) {
            ResponseData responseData = new ResponseData<>(HttpStatus.OK, "push notification has been sent", null);
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData responseData = new ResponseData<>(HttpStatus.BAD_REQUEST, "fail to send push notification", null);
            return new ResponseEntity(responseData, HttpStatus.BAD_REQUEST);
        }
    }
}
