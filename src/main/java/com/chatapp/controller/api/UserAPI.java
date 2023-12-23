package com.chatapp.controller.api;

import com.chatapp.commond.MessageResponseData;
import com.chatapp.commond.ResponseData;
import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.request.email.EmailRequestDTO;
import com.chatapp.dto.request.email.PasswordChangeRequestDTO;
import com.chatapp.dto.request.email.PasswordResetRequestDTO;
import com.chatapp.dto.request.token.TokenRequestDTO;
import com.chatapp.dto.request.user.UserGetRequestDTO;
import com.chatapp.dto.request.user.UserLoginRequestDTO;
import com.chatapp.dto.request.user.follow.UserFollowRequestDTO;
import com.chatapp.dto.request.user.image.UserImageUpdateRequestDTO;
import com.chatapp.dto.response.group.GroupResponseDTO;
import com.chatapp.dto.response.user.UserDTO;
import com.chatapp.dto.response.user.UserInfoResponseDTO;
import com.chatapp.dto.response.user.follow.UserFollowResponseDTO;
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

    //////////////////
    // Get
    //////////////////
    @GetMapping({ "users", "users/" })
    public ResponseEntity<ResponseData<List<UserInfoResponseDTO>>> findAll() {
        ResponseData<List<UserInfoResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",
                userService.findAll());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "users/token/{token}", "users/token/{token}/" })
    public ResponseEntity<ResponseData<BaseDTO>> getUserFromToken(@PathVariable("token") String token) {
        ResponseData<BaseDTO> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",
                userService.getUserFromToken(token));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping({ "users/{email}", "users/{email}/" })
    public UserInfoResponseDTO getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping({ "users/{userId}/group", "users/{userId}/group/" })
    public ResponseEntity<ResponseData<List<GroupResponseDTO>>> getGroupByUserId(@PathVariable Long userId) {
        ResponseData<List<GroupResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",
                userService.getGroupByUserId(userId));
        return ResponseEntity.ok(responseData);
    }

    //////////////////
    // Post
    //////////////////
    @PostMapping({ "login", "login/" })
    ResponseEntity<?> login(@RequestBody UserLoginRequestDTO userDTORequest) {
        ResponseData<AuthTokenDTO> responseData = new ResponseData<>(HttpStatus.OK, "login_success",
                userService.login(userDTORequest));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users", "users/" })
    public AuthTokenDTO save(@RequestBody UserDTO userDTO) {
        UserInfoResponseDTO tempUserDTO = userService.saveOrUpdate(userDTO);
        String token = tokenProvider.generateToken(tempUserDTO.getEmail());
        return new AuthTokenDTO(token);
    }

    @PostMapping({ "users/follow", "users/follow" })
    public ResponseEntity<ResponseData<String>> save(@RequestBody UserFollowRequestDTO userFollowRequestDTO) {
        userService.follow(userFollowRequestDTO);
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK, "success", null);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users/follow/me", "users/follow/me/" })
    public ResponseEntity<ResponseData<List<UserFollowResponseDTO>>> getFollowsByUserId(
            @RequestBody UserGetRequestDTO userGetRequestDTO) {
        ResponseData<List<UserFollowResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",
                userService.getFollowsByUserId(userGetRequestDTO));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users/follow/other", "users/follow/other/" })
    public ResponseEntity<ResponseData<List<UserFollowResponseDTO>>> getOtherFollowByUserId(
            @RequestBody UserGetRequestDTO userGetRequestDTO) {
        ResponseData<List<UserFollowResponseDTO>> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",
                userService.getOtherPeopleFollowByUserId(userGetRequestDTO));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users/check", "users/check/" })
    public ResponseEntity<ResponseData<Long>> checkEmail(@RequestParam String email) {
        ResponseData<Long> responseData = new ResponseData<>(HttpStatus.OK, "success",
                userService.checkEmailUser(email));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users/get/email/reset", "users/get/email/reset/" })
    public ResponseEntity<ResponseData<String>> sendEmailResetPassword(@RequestBody EmailRequestDTO request)
            throws MessagingException, UnsupportedEncodingException {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK, "success",
                userService.sendEmailResetPassword(request));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users/get/email/authen/register", "users/get/email/authen/register/" })
    public ResponseEntity<ResponseData<String>> sendEmailAuthenRegister(@RequestBody EmailRequestDTO request)
            throws MessagingException, UnsupportedEncodingException {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK, "success",
                userService.sendEmailAuthenticationRegister(request));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users/check/token", "users/check/token" })
    public ResponseEntity<ResponseData<Long>> checkToken(@RequestBody TokenRequestDTO request) throws Exception {
        ResponseData<Long> responseData = new ResponseData<>(HttpStatus.OK, "success",
                userService.checkToken(request.getToken()));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users/reset/password", "users/reset/password/" })
    public ResponseEntity<ResponseData<String>> resetPassword(@RequestBody PasswordResetRequestDTO request)
            throws Exception {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK, "success",
                userService.resetPassword(request));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users/change/password", "users/change/password/" })
    public ResponseEntity<ResponseData<String>> changePassword(@RequestBody PasswordChangeRequestDTO request)
            throws Exception {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK, "ok",
                userService.changePassword(request));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users/authen/register", "users/authen/register/" })
    public ResponseEntity<ResponseData<String>> authenRegister(@RequestBody TokenRequestDTO request) throws Exception {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK, "success",
                userService.authenRegister(request));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping({ "users/change/image", "users/change/image/" })
    public ResponseEntity<ResponseData<String>> updateImage(@RequestBody UserImageUpdateRequestDTO request) {
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK, "success",
                userService.updateAvatar(request));
        return ResponseEntity.ok(responseData);
    }

    //////////////////
    // Put
    //////////////////
    @PutMapping("users/message/focus")
    public ResponseEntity<MessageResponseData> updateUserFocusMessageFocusIn(@RequestBody UserDTO userDTO) {
        boolean isSuccess = userService.setIsMessageFocusIn(userDTO.getId());
        if (isSuccess) {
            return new ResponseEntity<>(
                    new MessageResponseData(HttpStatus.CREATED, "update_user_messages_focus_in_success"),
                    HttpStatus.CREATED);
        }
        return new ResponseEntity<>(
                new MessageResponseData(HttpStatus.BAD_REQUEST, "fail_to_update_user_messages_focus_in"),
                HttpStatus.BAD_REQUEST);
    }

    @PutMapping("users/message/exit")
    public ResponseEntity<MessageResponseData> updateUserFocusMessageFocusOut(@RequestBody UserDTO userDTO) {
        boolean isSuccess = userService.setIsMessageFocusOut(userDTO.getId());
        if (isSuccess) {
            return new ResponseEntity<>(
                    new MessageResponseData(HttpStatus.CREATED, "update_user_messages_exit_success"),
                    HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new MessageResponseData(HttpStatus.BAD_REQUEST, "update_user_messages_exit_failed"),
                HttpStatus.BAD_REQUEST);
    }

    @PutMapping("users/status/inactive")
    public ResponseEntity<MessageResponseData> updateUserToInactiveState(@RequestBody UserDTO userDTO) {
        boolean isSuccess = userService.setUserStatusInactive(userDTO.getId());
        if (isSuccess) {
            return new ResponseEntity<>(
                    new MessageResponseData(HttpStatus.CREATED, "changed_user_state_to_inactive"),
                    HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new MessageResponseData(HttpStatus.BAD_REQUEST, "change_user_status_faild"),
                HttpStatus.BAD_REQUEST);
    }

    //////////////////
    // Delete
    //////////////////
    @DeleteMapping({ "users/{id}", "users/{id}/" })
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
