package com.chatapp.controller.api;

import com.chatapp.commond.MessageResponseData;
import com.chatapp.dto.request.token.DeviceTokenRequestDTO;
import com.chatapp.service.DeviceTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeviceTokenAPI {
    @Autowired
    private DeviceTokenService deviceTokenService;

    //////////////////
    //Post
    //////////////////
    @PostMapping("/device-token")
    public ResponseEntity<MessageResponseData> updateDeviceToken(@RequestBody DeviceTokenRequestDTO requestDTO) {
        boolean isSuccess = deviceTokenService.saveUserDeviceToken(requestDTO);
        if (isSuccess) {
            return new ResponseEntity<>(new MessageResponseData(HttpStatus.CREATED, "token_saved_success"), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new MessageResponseData(HttpStatus.BAD_REQUEST, "failed_to_save_token"), HttpStatus.BAD_REQUEST);
    }

    //////////////////
    //Delete
    //////////////////
    @DeleteMapping("/device-token")
    public ResponseEntity<MessageResponseData> deleteDeviceToken(@RequestBody DeviceTokenRequestDTO requestDTO) {
        boolean isSuccess = deviceTokenService.deleteUserDeviceToken(requestDTO);
        if (isSuccess) {
            return new ResponseEntity<>(new MessageResponseData(HttpStatus.CREATED, "token_delete_success"), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new MessageResponseData(HttpStatus.BAD_REQUEST, "token_delete_failed"), HttpStatus.BAD_REQUEST);
    }
}
