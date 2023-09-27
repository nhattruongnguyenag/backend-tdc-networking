package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.dto.request.StudentInfoUpdateOrSaveRequestDTO;
import com.chatapp.dto.response.StudentInfoResponeDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.service.UserService;

@RestController
@RequestMapping("/api")
public class StudentInfoAPI {
    @Autowired
    private UserService userService;
    
    @GetMapping({ "studentInfos", "studentInfos/" })
    public List<StudentInfoResponeDTO> findAll() {
        return userService.findAllStudentInfo();
    }

    @PostMapping({ "studentInfos", "studentInfos/" })
    ResponseEntity<UserInfoResponseDTO> updateOrSave(@RequestBody StudentInfoUpdateOrSaveRequestDTO studentInfoUpdateOrSaveRequestDTO) {
        return ResponseEntity.ok(userService.studentUpdateOrSave(studentInfoUpdateOrSaveRequestDTO));
    }
}
