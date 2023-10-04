package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.response.NotificationResponseDTO;
import com.chatapp.service.NotificationService;

@RestController
@RequestMapping("/api")
public class NotificationAPI {
    @Autowired
    private NotificationService notificationService;

    @GetMapping({"notifications", "notifications/"})
    public ResponseEntity<ResponseData<List<NotificationResponseDTO>>> findAll() {
        ResponseData<List<NotificationResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",notificationService.findAll());
        return ResponseEntity.ok(responseData);
    }
}
