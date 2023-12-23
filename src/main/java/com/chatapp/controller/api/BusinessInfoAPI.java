package com.chatapp.controller.api;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.request.user.business.BusinessInfoRegisterRequestDTO;
import com.chatapp.dto.request.user.business.BusinessInfoUpdateOrSaveRequestDTO;
import com.chatapp.dto.response.user.business.BusinessInfoResponseDTO;
import com.chatapp.service.UserService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api")
public class BusinessInfoAPI {
    @Autowired
    private UserService userService;

    //////////////////
    //Get
    //////////////////
    @GetMapping({ "business", "business/" })
    public ResponseEntity<ResponseData<List<BusinessInfoResponseDTO>>> findAll() {
        ResponseData<List<BusinessInfoResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "success",
                userService.findAllBusinessInfo());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "business/{userId}", "business/{userId}/" })
    public ResponseEntity<ResponseData<BusinessInfoResponseDTO>> getBusinessByUserId(@PathVariable Long userId) {
        ResponseData<BusinessInfoResponseDTO> responseData = new ResponseData<>(HttpStatus.OK, "success",
                userService.getBusinessDetailByUserId(userId));
        return ResponseEntity.ok(responseData);
    }

    //////////////////
    //Post
    //////////////////
    @PostMapping({ "business", "business/" })
    ResponseEntity<ResponseData<AuthTokenDTO>> updateOrSave(
            @RequestBody BusinessInfoUpdateOrSaveRequestDTO businessInfoUpdateOrSaveRequestDTO) {
        ResponseData<AuthTokenDTO> responseData = new ResponseData<>(HttpStatus.CREATED, "add or update success",
                userService.businessUpdateOrSave(businessInfoUpdateOrSaveRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }

    @PostMapping({ "business/register", "business/register/" })
    ResponseEntity<ResponseData<AuthTokenDTO>> businessRegister(
            @RequestBody BusinessInfoRegisterRequestDTO businessInfoRegisterRequestDTO) throws MessagingException, UnsupportedEncodingException{
        ResponseData<AuthTokenDTO> responseData = new ResponseData<>(HttpStatus.CREATED, "register success",
                userService.businessRegister(businessInfoRegisterRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }
}
