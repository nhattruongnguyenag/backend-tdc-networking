package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.converter.request.NotificationChangeAllStatusByUserIdRequest;
import com.chatapp.dto.request.NotificationByUserRequestDTO;
import com.chatapp.dto.request.NotificationChangeStatusRequestDTO;
import com.chatapp.dto.request.NotificationDeleteRequestDTO;
import com.chatapp.dto.request.NotificationSaveRequestDTO;
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

    @PostMapping({"notifications/user", "notifications/user/"})
    public ResponseEntity<ResponseData<List<NotificationResponseDTO>>> findByUserId(@RequestBody NotificationByUserRequestDTO notificationByUserRequestDTO) {
        ResponseData<List<NotificationResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",notificationService.findById(notificationByUserRequestDTO));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({"notifications/find", "notifications/find/"})
    public ResponseEntity<ResponseData<List<NotificationResponseDTO>>> findByContent(@RequestParam(value = "content") String content) {
        ResponseData<List<NotificationResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",notificationService.findByContent(content));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "notifications", "notifications/" })
    ResponseEntity<ResponseData<NotificationResponseDTO>> updateOrSave(@RequestBody NotificationSaveRequestDTO notificationSaveRequestDTO) {
        ResponseData<NotificationResponseDTO> responseData = new ResponseData<>(HttpStatus.CREATED,"success",notificationService.save(notificationSaveRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    @DeleteMapping({ "notifications", "notifications/" })
    ResponseEntity<ResponseData<String>> delete(@RequestBody NotificationDeleteRequestDTO notificationDeleteRequestDTO) {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED,"success",notificationService.delete(notificationDeleteRequestDTO));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping({ "notifications/changeStatus", "notifications/changeStatus/" })
    ResponseEntity<ResponseData<NotificationResponseDTO>> changeStatus(@RequestBody NotificationChangeStatusRequestDTO notificationChangeStatusRequestDTO) {
        ResponseData<NotificationResponseDTO> responseData = new ResponseData<>(HttpStatus.OK,"success",notificationService.changeStatus(notificationChangeStatusRequestDTO));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping({ "notifications/changeStatus/makeNotSeen", "notifications/changeStatus/makeNotSeen/" })
    ResponseEntity<ResponseData<NotificationResponseDTO>> makeNotSeen(@RequestBody NotificationChangeStatusRequestDTO notificationChangeStatusRequestDTO) {
        ResponseData<NotificationResponseDTO> responseData = new ResponseData<>(HttpStatus.OK,"success",notificationService.makeNotSeen(notificationChangeStatusRequestDTO));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping({ "notifications/changeStatus/all", "notifications/changeStatus/all/" })
    ResponseEntity<ResponseData<String>> changeStatusAll(@RequestBody NotificationChangeAllStatusByUserIdRequest notificationChangeAllStatusByUserIdRequest) {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK,"success",notificationService.changeStatusAll(notificationChangeAllStatusByUserIdRequest));
        return ResponseEntity.ok(responseData);
    }
}
