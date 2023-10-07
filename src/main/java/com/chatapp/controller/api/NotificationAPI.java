package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
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

    @GetMapping({"notifications/user/{id}", "notifications/user/{id}/"})
    public ResponseEntity<ResponseData<List<NotificationResponseDTO>>> findByUserId(@PathVariable("id") Long id) {
        ResponseData<List<NotificationResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",notificationService.findById(id));
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
        ResponseData<NotificationResponseDTO> responseData = new ResponseData<>(HttpStatus.CREATED,"success",notificationService.changeStatus(notificationChangeStatusRequestDTO));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping({ "notifications/all/{id}", "notifications/all/{id}/" })
    ResponseEntity<ResponseData<String>> deleteAll(@PathVariable("id") Long userId) {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED,"success",notificationService.deleteAll(userId));
        return ResponseEntity.ok(responseData);
    }
}
