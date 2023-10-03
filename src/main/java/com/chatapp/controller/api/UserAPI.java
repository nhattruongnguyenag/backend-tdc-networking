package com.chatapp.controller.api;

import com.chatapp.commond.ResponseData;
import com.chatapp.converter.request.UserRequestConverter;
import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.UserDTO;
import com.chatapp.dto.request.UserLoginRequestDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.service.UserService;
import com.chatapp.util.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserAPI {
   
    @Autowired
    private UserService userService;
    @Autowired
    private TokenProvider tokenProvider;

    @GetMapping({ "users", "users/" })
    public ResponseEntity<ResponseData<List<UserInfoResponseDTO>>> findAll() {
        ResponseData<List<UserInfoResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",userService.findAll());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "users/token/{token}", "users/token/{token}/" })
    public ResponseEntity<ResponseData<BaseDTO>> getUserFromToken(@PathVariable("token") String token) {
        ResponseData<BaseDTO> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",userService.getUserFromToken(token));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "users/{email}", "users/{email}/" })
    public UserInfoResponseDTO getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping({ "login", "login/" })
    ResponseEntity<ResponseData<AuthTokenDTO>> login(@RequestBody UserLoginRequestDTO userDTORequest) {
        ResponseData<AuthTokenDTO> responseData = new ResponseData<>(HttpStatus.OK, "login_sucesss",userService.login(userDTORequest));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users", "users/" })
    public AuthTokenDTO save(@RequestBody UserDTO userDTO) {
        UserInfoResponseDTO tempUserDTO = userService.saveOrUpdate(userDTO);
        String token = tokenProvider.generateToken(tempUserDTO.getEmail());
        return new AuthTokenDTO(token);
    }

    @DeleteMapping({ "users/{id}", "users/{id}/" })
    public ResponseEntity delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
