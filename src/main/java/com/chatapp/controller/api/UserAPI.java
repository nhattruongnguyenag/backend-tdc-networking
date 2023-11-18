package com.chatapp.controller.api;

import com.chatapp.commond.MessageResponseData;
import com.chatapp.commond.ResponseData;
import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.UserDTO;
import com.chatapp.dto.request.PasswordResetRequestDTO;
import com.chatapp.dto.request.TokenRequestDTO;
import com.chatapp.dto.request.UserFollowRequestDTO;
import com.chatapp.dto.request.UserGetRequestDTO;
import com.chatapp.dto.request.UserGetResetPasswordRequestDTO;
import com.chatapp.dto.request.UserImageUpdateRequestDTO;
import com.chatapp.dto.request.UserLoginRequestDTO;
import com.chatapp.dto.response.GroupResponseDTO;
import com.chatapp.dto.response.UserFollowResponseDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.service.UserService;
import com.chatapp.util.TokenProvider;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
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
    ResponseEntity<?> login(@RequestBody UserLoginRequestDTO userDTORequest) {
        ResponseData<AuthTokenDTO> responseData = new ResponseData<>(HttpStatus.OK, "login_success", userService.login(userDTORequest));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users", "users/" })
    public AuthTokenDTO save(@RequestBody UserDTO userDTO) {
        UserInfoResponseDTO tempUserDTO = userService.saveOrUpdate(userDTO);
        String token = tokenProvider.generateToken(tempUserDTO.getEmail());
        return new AuthTokenDTO(token);
    }

    @PutMapping("users/message/focus")
    public ResponseEntity<MessageResponseData> updateUserFocusMessageFocusIn(@RequestBody UserDTO userDTO) {
        boolean isSuccess = userService.setIsMessageFocusIn(userDTO.getId());
        if (isSuccess) {
            return new ResponseEntity<>(new MessageResponseData(HttpStatus.CREATED, "update_user_messages_focus_in_success"), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new MessageResponseData(HttpStatus.BAD_REQUEST, "fail_to_update_user_messages_focus_in"), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("users/message/exit")
    public ResponseEntity<MessageResponseData> updateUserFocusMessageFocusOut(@RequestBody UserDTO userDTO) {
        boolean isSuccess = userService.setIsMessageFocusOut(userDTO.getId());
        if (isSuccess) {
            return new ResponseEntity<>(new MessageResponseData(HttpStatus.CREATED, "update_user_messages_exit_success"), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new MessageResponseData(HttpStatus.BAD_REQUEST, "update_user_messages_exit_failed"), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping({ "users/{id}", "users/{id}/" })
    public ResponseEntity delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping({ "users/follow", "users/follow" })
    public ResponseEntity<ResponseData<String>> save(@RequestBody UserFollowRequestDTO userFollowRequestDTO) {
        userService.follow(userFollowRequestDTO);
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK,"success",null);
        return ResponseEntity.ok(responseData);
    }

    
    @PostMapping({ "users/follow/me", "users/follow/me/" })
    public ResponseEntity<ResponseData<List<UserFollowResponseDTO>>> getFollowsByUserId(@RequestBody UserGetRequestDTO userGetRequestDTO) {
        ResponseData<List<UserFollowResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",userService.getFollowsByUserId(userGetRequestDTO));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users/follow/other", "users/follow/other/" })
    public ResponseEntity<ResponseData<List<UserFollowResponseDTO>>> getOtherFollowByUserId(@RequestBody UserGetRequestDTO userGetRequestDTO) {
        ResponseData<List<UserFollowResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",userService.getOtherPeopleFollowByUserId(userGetRequestDTO));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "users/{userId}/group", "users/{userId}/group/" })
    public ResponseEntity<ResponseData<List<GroupResponseDTO>>> getGroupByUserId(@PathVariable Long userId) {
        ResponseData<List<GroupResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",userService.getGroupByUserId(userId));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users/check", "users/check" })
    public ResponseEntity<ResponseData<Long>> checkEmail(@RequestParam String email) {
        ResponseData<Long> responseData = new ResponseData<>(HttpStatus.OK,"success",userService.checkEmailUser(email));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users/get/email/reset", "users/get/email/reset/" })
    public ResponseEntity<ResponseData<String>> checkEmail(@RequestBody UserGetResetPasswordRequestDTO request) throws MessagingException, UnsupportedEncodingException {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK,"success",userService.sendEmail(request.getEmail()));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users/check/token", "users/check/token" })
    public ResponseEntity<ResponseData<Long>> checkToken(@RequestBody TokenRequestDTO request) throws Exception{
        ResponseData<Long> responseData = new ResponseData<>(HttpStatus.OK,"success",userService.checkToken(request.getToken()));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users/reset/password", "users/reset/password/" })
    public ResponseEntity<ResponseData<String>> resetPassword(@RequestBody PasswordResetRequestDTO request) throws Exception{
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK,"success",userService.resetPassword(request));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users/change/image", "users/change/image/" })
    public ResponseEntity<ResponseData<String>> updateImage(@RequestBody UserImageUpdateRequestDTO request){
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK,"success",userService.updateAvatar(request));
        return ResponseEntity.ok(responseData);
    }
}
