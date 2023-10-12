package com.chatapp.controller.api;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.request.UserInfoFindRequestDTO;
import com.chatapp.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class SearchAPI {
   
    @Autowired
    private UserService userService;

    @PostMapping({ "find/user", "find/user/" })
    ResponseEntity<ResponseData<List<BaseDTO>>> findUser(@RequestBody UserInfoFindRequestDTO userInfoFindRequestDTO) {
        ResponseData<List<BaseDTO>> responseData = new ResponseData<>(HttpStatus.OK,"success",userService.findUserByName(userInfoFindRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }
}
