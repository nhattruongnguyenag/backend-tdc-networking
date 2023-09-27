package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
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
    public ResponseData<List<StudentInfoResponeDTO>> findAll() {
        ResponseData<List<StudentInfoResponeDTO>> responseData = new ResponseData<List<StudentInfoResponeDTO>>(HttpStatus.OK, "success", userService.findAllStudentInfo());
        return responseData;
    }

    @PostMapping({ "studentInfos", "studentInfos/" })
    ResponseEntity<ResponseData<UserInfoResponseDTO>> updateOrSave(@RequestBody StudentInfoUpdateOrSaveRequestDTO studentInfoUpdateOrSaveRequestDTO) {
        ResponseData<UserInfoResponseDTO> responseData = new ResponseData<>(HttpStatus.ACCEPTED,"add or update faculty success",userService.studentUpdateOrSave(studentInfoUpdateOrSaveRequestDTO));
        return ResponseEntity.ok(responseData);
    }
}
