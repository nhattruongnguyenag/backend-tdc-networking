package com.chatapp.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.request.BusinessInfoRegisterRequestDTO;
import com.chatapp.dto.request.BusinessInfoUpdateOrSaveRequestDTO;
import com.chatapp.dto.response.BusinessInfoResponseDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.service.UserService;

@RestController
@RequestMapping("/api")
public class BusinessInfoAPI {
    @Autowired
    private UserService userService;

    @GetMapping({ "businessInfos", "businessInfos/" })
    public ResponseData<List<BusinessInfoResponseDTO>> findAll() {
        ResponseData<List<BusinessInfoResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                userService.findAllBusinessInfo());
        return responseData;
    }

    @PostMapping({ "businessInfos", "businessInfos/" })
    ResponseEntity<ResponseData<UserInfoResponseDTO>> updateOrSave(
            @RequestBody BusinessInfoUpdateOrSaveRequestDTO businessInfoUpdateOrSaveRequestDTO) {
        ResponseData<UserInfoResponseDTO> responseData = new ResponseData<>(HttpStatus.CREATED, "add or update success",
                userService.businessUpdateOrSave(businessInfoUpdateOrSaveRequestDTO));
        return new ResponseEntity<ResponseData<UserInfoResponseDTO>>(responseData, HttpStatus.CREATED);
    }

    @PostMapping({ "businessInfos/register", "businessInfos/register/" })
    ResponseEntity<ResponseData<AuthTokenDTO>> studentRegister(
            @RequestBody BusinessInfoRegisterRequestDTO businessInfoRegisterRequestDTO) {
        ResponseData<AuthTokenDTO> responseData = new ResponseData<>(HttpStatus.CREATED, "register success",
                userService.businessRegister(businessInfoRegisterRequestDTO));
        return ResponseEntity.ok(responseData);
    }
}
