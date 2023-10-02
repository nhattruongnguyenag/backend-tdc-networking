package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.request.StudentInfoRegisterRequestDTO;
import com.chatapp.dto.request.StudentInfoUpdateOrSaveRequestDTO;
import com.chatapp.dto.response.StudentInfoResponeDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.service.UserService;

@RestController
@RequestMapping("/api")
public class StudentInfoAPI {
    @Autowired
    private UserService userService;
    
    @GetMapping({ "student", "student/" })
    public ResponseData<List<StudentInfoResponeDTO>> findAll() {
        ResponseData<List<StudentInfoResponeDTO>> responseData = new ResponseData<List<StudentInfoResponeDTO>>(HttpStatus.OK, "success", userService.findAllStudentInfo());
        return responseData;
    }

    @PostMapping({ "student", "student/" })
    ResponseEntity<ResponseData<UserInfoResponseDTO>> updateOrSave(@RequestBody StudentInfoUpdateOrSaveRequestDTO studentInfoUpdateOrSaveRequestDTO) {
        ResponseData<UserInfoResponseDTO> responseData = new ResponseData<>(HttpStatus.CREATED,"add or update student success",userService.studentUpdateOrSave(studentInfoUpdateOrSaveRequestDTO));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "student/register", "student/register/" })
    ResponseEntity<ResponseData<AuthTokenDTO>> studentRegister(@RequestBody StudentInfoRegisterRequestDTO studentRegisterRequestDTO) {
        ResponseData<AuthTokenDTO> responseData = new ResponseData<>(HttpStatus.CREATED,"register success",userService.studentRegister(studentRegisterRequestDTO));
        return ResponseEntity.ok(responseData);
    }
}
