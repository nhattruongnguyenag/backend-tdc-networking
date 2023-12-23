package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.converter.request.notification.NotificationChangeAllStatusByUserIdRequest;
import com.chatapp.dto.request.notification.NotificationByUserRequestDTO;
import com.chatapp.dto.request.notification.NotificationChangeStatusRequestDTO;
import com.chatapp.dto.request.notification.NotificationDeleteRequestDTO;
import com.chatapp.dto.request.notification.NotificationSaveRequestDTO;
import com.chatapp.dto.response.notification.NotificationResponseDTO;
import com.chatapp.repository.NotificationRepository;
import com.chatapp.service.NotificationService;

@RestController
@RequestMapping("/api")
public class NotificationAPI {
        @Autowired
        private NotificationService notificationService;

        @Autowired
        private NotificationRepository notificationRepository;

        // test
        @GetMapping({ "delete/no" })
        public void testde() {
                notificationRepository.deleteAll();
        }

        //////////////////
        // Get
        //////////////////
        @GetMapping({ "notifications", "notifications/" })
        public ResponseEntity<ResponseData<List<NotificationResponseDTO>>> findAll() {
                ResponseData<List<NotificationResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",
                                notificationService.findAll());
                return ResponseEntity.ok(responseData);
        }

        @GetMapping({ "notifications/find", "notifications/find/" })
        public ResponseEntity<ResponseData<List<NotificationResponseDTO>>> findByContent(
                        @RequestParam(value = "content") String content) {
                ResponseData<List<NotificationResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",
                                notificationService.findByContent(content));
                return ResponseEntity.ok(responseData);
        }

        //////////////////
        // Post
        //////////////////
        @PostMapping({ "notifications/user", "notifications/user/" })
        public ResponseEntity<ResponseData<List<NotificationResponseDTO>>> findByUserId(
                        @RequestBody NotificationByUserRequestDTO notificationByUserRequestDTO) {
                ResponseData<List<NotificationResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",
                                notificationService.findById(notificationByUserRequestDTO));
                return ResponseEntity.ok(responseData);
        }

        @PostMapping({ "notifications/user/count", "notifications/user/count/" })
        public ResponseEntity<ResponseData<Integer>> findCountByUserId(
                        @RequestBody NotificationByUserRequestDTO notificationByUserRequestDTO) {
                ResponseData<Integer> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",
                                notificationService.getCountNotification(notificationByUserRequestDTO));
                return ResponseEntity.ok(responseData);
        }

        @PostMapping({ "notifications", "notifications/" })
        ResponseEntity<ResponseData<NotificationResponseDTO>> updateOrSave(
                        @RequestBody NotificationSaveRequestDTO notificationSaveRequestDTO) {
                ResponseData<NotificationResponseDTO> responseData = new ResponseData<>(HttpStatus.CREATED, "success",
                                notificationService.save(notificationSaveRequestDTO));
                return ResponseEntity.created(null).body(responseData);
        }

        //////////////////
        // Delete
        //////////////////
        @DeleteMapping({ "notifications", "notifications/" })
        ResponseEntity<ResponseData<String>> delete(
                        @RequestBody NotificationDeleteRequestDTO notificationDeleteRequestDTO) {
                ResponseData<String> responseData = new ResponseData<>(HttpStatus.CREATED, "success",
                                notificationService.delete(notificationDeleteRequestDTO));
                return ResponseEntity.ok(responseData);
        }

        //////////////////
        // Put
        //////////////////
        @PutMapping({ "notifications/changeStatus", "notifications/changeStatus/" })
        ResponseEntity<ResponseData<NotificationResponseDTO>> changeStatus(
                        @RequestBody NotificationChangeStatusRequestDTO notificationChangeStatusRequestDTO) {
                ResponseData<NotificationResponseDTO> responseData = new ResponseData<>(HttpStatus.OK, "success",
                                notificationService.changeStatus(notificationChangeStatusRequestDTO));
                return ResponseEntity.ok(responseData);
        }

        @PutMapping({ "notifications/changeStatus/makeNotSeen", "notifications/changeStatus/makeNotSeen/" })
        ResponseEntity<ResponseData<NotificationResponseDTO>> makeNotSeen(
                        @RequestBody NotificationChangeStatusRequestDTO notificationChangeStatusRequestDTO) {
                ResponseData<NotificationResponseDTO> responseData = new ResponseData<>(HttpStatus.OK, "success",
                                notificationService.makeNotSeen(notificationChangeStatusRequestDTO));
                return ResponseEntity.ok(responseData);
        }

        @PutMapping({ "notifications/changeStatus/all", "notifications/changeStatus/all/" })
        ResponseEntity<ResponseData<String>> changeStatusAll(
                        @RequestBody NotificationChangeAllStatusByUserIdRequest notificationChangeAllStatusByUserIdRequest) {
                ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK, "success",
                                notificationService.changeStatusAll(notificationChangeAllStatusByUserIdRequest));
                return ResponseEntity.ok(responseData);
        }
}
