package com.chatapp.controller.api;

import com.chatapp.commond.MessageResponseData;
import com.chatapp.dto.request.DeviceTokenRequestDTO;
import com.chatapp.service.DeviceTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DeviceTokenAPI {
    @Autowired
    private DeviceTokenService deviceTokenService;

    @PostMapping("/device-token")
    public ResponseEntity<MessageResponseData> updateDeviceToken(@RequestBody DeviceTokenRequestDTO requestDTO) {
        boolean isSuccess = deviceTokenService.saveUserDeviceToken(requestDTO);
        if (isSuccess) {
            return new ResponseEntity<>(new MessageResponseData(HttpStatus.CREATED, "success"), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new MessageResponseData(HttpStatus.BAD_REQUEST, "failed"), HttpStatus.BAD_REQUEST);
    }
}
