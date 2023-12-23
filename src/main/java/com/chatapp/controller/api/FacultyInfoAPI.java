package com.chatapp.controller.api;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.request.user.faculty.FacultyInfoRegisterRequestDTO;
import com.chatapp.dto.request.user.faculty.FacultyInfoUpdateOrSaveRequestDTO;
import com.chatapp.dto.response.user.faculty.FacultyInfoResponseDTO;
import com.chatapp.service.UserService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api")
public class FacultyInfoAPI {
    @Autowired
    private UserService userService;
    
    //////////////////
    //Get
    //////////////////
    @GetMapping({ "faculty", "faculty/" })
    public ResponseEntity<ResponseData<List<FacultyInfoResponseDTO>>> findAll() {
        ResponseData<List<FacultyInfoResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK,"success",userService.findAllFacultyInfo());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "faculty/{userId}", "faculty/{userId}/"})
    public ResponseEntity<ResponseData<FacultyInfoResponseDTO>> getFacultyByUserId(@PathVariable Long userId) {
        ResponseData<FacultyInfoResponseDTO> responseData = new ResponseData<>(HttpStatus.OK,"success",userService.getFacultyDetailByUserId(userId));
        return ResponseEntity.ok(responseData);
    }

    //////////////////
    //Post
    //////////////////
    @PostMapping({ "faculty", "faculty/" })
    ResponseEntity<ResponseData<AuthTokenDTO>> updateOrSave(@RequestBody FacultyInfoUpdateOrSaveRequestDTO facultyInfoUpdateOrSaveRequestDTO) {
        ResponseData<AuthTokenDTO> responseData = new ResponseData<>(HttpStatus.CREATED,"add or update success",userService.facultyUpdateOrSave(facultyInfoUpdateOrSaveRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    @PostMapping({ "faculty/register", "faculty/register/" })
    ResponseEntity<ResponseData<AuthTokenDTO>> facultyRegister(
            @RequestBody FacultyInfoRegisterRequestDTO facultyInfoRegisterRequestDTO) throws MessagingException, UnsupportedEncodingException{
        ResponseData<AuthTokenDTO> responseData = new ResponseData<>(HttpStatus.CREATED, "register success",
                userService.facultyRegister(facultyInfoRegisterRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }
}
