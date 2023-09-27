package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.dto.response.FalcutyInfoResponeDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.service.UserService;

@RestController
@RequestMapping("/api")
public class FalcutyInfoAPI {
    @Autowired
    private UserService userService;
    
    @GetMapping({ "falcutyInfos", "falcutyInfos/" })
    public List<FalcutyInfoResponeDTO> findAll() {
        return userService.findAllFalcutyInfo();
    }

    // @PostMapping({ "studentInfos", "studentInfos/" })
    // ResponseEntity<UserInfoResponseDTO> updateOrSave(@RequestBody StudentInfoUpdateOrSaveRequestDTO studentInfoUpdateOrSaveRequestDTO) {
    //     return ResponseEntity.ok(userService.studentUpdateOrSave(studentInfoUpdateOrSaveRequestDTO));
    // }
}
