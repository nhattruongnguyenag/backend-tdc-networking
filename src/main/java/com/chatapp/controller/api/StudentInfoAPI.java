package com.chatapp.controller.api;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.request.user.student.StudentInfoRegisterRequestDTO;
import com.chatapp.dto.request.user.student.StudentInfoUpdateOrSaveRequestDTO;
import com.chatapp.dto.response.user.student.StudentInfoResponseDTO;
import com.chatapp.service.UserService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api")
public class StudentInfoAPI {
    @Autowired
    private UserService userService;

    //////////////////
    //Get
    //////////////////
    @GetMapping({ "student", "student/" })
    public ResponseEntity<ResponseData<List<StudentInfoResponseDTO>>> findAll() {
        ResponseData<List<StudentInfoResponseDTO>> responseData = new ResponseData<List<StudentInfoResponseDTO>>(HttpStatus.OK, "success", userService.findAllStudentInfo());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "student/{userId}", "student/{userId}/" })
    public ResponseEntity<ResponseData<StudentInfoResponseDTO>> getStudentByUserId(@PathVariable Long userId) {
        ResponseData<StudentInfoResponseDTO> responseData = new ResponseData<StudentInfoResponseDTO>(HttpStatus.OK, "success", userService.getStudentDetailByUserId(userId));
        return ResponseEntity.ok(responseData);
    }

    //////////////////
    //Post
    //////////////////
    @PostMapping({ "student", "student/" })
    ResponseEntity<ResponseData<AuthTokenDTO>> updateOrSave(@RequestBody StudentInfoUpdateOrSaveRequestDTO studentInfoUpdateOrSaveRequestDTO) {
        ResponseData<AuthTokenDTO> responseData = new ResponseData<>(HttpStatus.CREATED,"add or update student success",userService.studentUpdateOrSave(studentInfoUpdateOrSaveRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    @PostMapping({ "student/register", "student/register/" })
    ResponseEntity<ResponseData<AuthTokenDTO>> studentRegister(@RequestBody StudentInfoRegisterRequestDTO studentRegisterRequestDTO) throws MessagingException, UnsupportedEncodingException{
        ResponseData<AuthTokenDTO> responseData = new ResponseData<>(HttpStatus.CREATED,"register success",userService.studentRegister(studentRegisterRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }
}
