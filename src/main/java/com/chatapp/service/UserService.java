package com.chatapp.service;

import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.request.email.EmailRequestDTO;
import com.chatapp.dto.request.email.PasswordChangeRequestDTO;
import com.chatapp.dto.request.email.PasswordResetRequestDTO;
import com.chatapp.dto.request.token.TokenRequestDTO;
import com.chatapp.dto.request.user.UserFindRequestDTO;
import com.chatapp.dto.request.user.UserGetRequestDTO;
import com.chatapp.dto.request.user.UserInfoFindRequestDTO;
import com.chatapp.dto.request.user.UserLoginRequestDTO;
import com.chatapp.dto.request.user.business.BusinessInfoRegisterRequestDTO;
import com.chatapp.dto.request.user.business.BusinessInfoUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.user.faculty.FacultyInfoRegisterRequestDTO;
import com.chatapp.dto.request.user.faculty.FacultyInfoUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.user.follow.UserFollowRequestDTO;
import com.chatapp.dto.request.user.image.UserImageUpdateRequestDTO;
import com.chatapp.dto.request.user.student.StudentInfoRegisterRequestDTO;
import com.chatapp.dto.request.user.student.StudentInfoUpdateOrSaveRequestDTO;
import com.chatapp.dto.response.group.GroupResponseDTO;
import com.chatapp.dto.response.user.UserDTO;
import com.chatapp.dto.response.user.UserFindResponseDTO;
import com.chatapp.dto.response.user.UserInfoResponseDTO;
import com.chatapp.dto.response.user.business.BusinessInfoResponseDTO;
import com.chatapp.dto.response.user.faculty.FacultyInfoResponseDTO;
import com.chatapp.dto.response.user.follow.UserFollowResponseDTO;
import com.chatapp.dto.response.user.student.StudentInfoResponseDTO;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {
    List<UserInfoResponseDTO> findAll();
    UserInfoResponseDTO findByEmailAndPassword(String email, String password);
    UserInfoResponseDTO saveOrUpdate(UserDTO userDTO);
    Long checkEmailUser(String email);

    UserInfoResponseDTO findById(Long id);

    boolean setIsMessageFocusIn(Long userId);

    boolean setIsMessageFocusOut(Long userId);

    boolean setIsTypingOn(Long userId);

    boolean setIsTypingOff(Long userId);

    boolean setUserStatusInactive(Long userId);

    UserInfoResponseDTO delete(Long userId);
    UserInfoResponseDTO changeStatus(Long userId, Byte status);
    AuthTokenDTO login(UserLoginRequestDTO userDTORequest);
    BaseDTO getUserFromToken(String token);
    UserInfoResponseDTO getUserByEmail(String email);
    UserInfoResponseDTO getUserById(UserGetRequestDTO userGetRequestDTO);

    //student service
    AuthTokenDTO studentRegister(StudentInfoRegisterRequestDTO studentRegisterDTO) throws MessagingException, UnsupportedEncodingException;
    List<StudentInfoResponseDTO> findAllStudentInfo();
    AuthTokenDTO studentUpdateOrSave(StudentInfoUpdateOrSaveRequestDTO studentInfoUpdateOrSaveRequestDTO);
    StudentInfoResponseDTO getStudentDetailByUserId(Long userId);

    //faculty service
    List<FacultyInfoResponseDTO> findAllFacultyInfo();
    AuthTokenDTO facultyRegister(FacultyInfoRegisterRequestDTO facultyInfoRegisterRequestDTO) throws MessagingException, UnsupportedEncodingException;
    AuthTokenDTO facultyUpdateOrSave(FacultyInfoUpdateOrSaveRequestDTO facultyInfoUpdateOrSaveRequestDTO);
    FacultyInfoResponseDTO getFacultyDetailByUserId(Long userId);

    //business
    List<BusinessInfoResponseDTO> findAllBusinessInfo();
    AuthTokenDTO businessRegister(BusinessInfoRegisterRequestDTO businessInfoRegisterRequestDTO) throws MessagingException, UnsupportedEncodingException;
    AuthTokenDTO businessUpdateOrSave(BusinessInfoUpdateOrSaveRequestDTO businessInfoUpdateOrSaveRequestDTO);
    BusinessInfoResponseDTO getBusinessDetailByUserId(Long userId);

    //find
    List<UserFindResponseDTO> findUserByName(UserInfoFindRequestDTO userInfoFindRequestDTO);
    List<UserFollowResponseDTO> findUserByNameInListFollowingByUserId(UserFindRequestDTO userFindRequestDTO);
    List<UserFollowResponseDTO> findUserByNameInListFollowerByUserId(UserFindRequestDTO userFindRequestDTO);


    //follow
    String follow(UserFollowRequestDTO userFollowRequestDTO);
    List<UserFollowResponseDTO> getFollowsByUserId(UserGetRequestDTO userGetRequestDTO);
    List<UserFollowResponseDTO> getOtherPeopleFollowByUserId(UserGetRequestDTO userGetRequestDTO);

    //group
    List<GroupResponseDTO> getGroupByUserId(Long userId);

    //forgot password
    String sendEmailResetPassword(EmailRequestDTO emailRequestDTO) throws MessagingException, UnsupportedEncodingException;
    Long checkToken(String token) throws Exception;
    String resetPassword(PasswordResetRequestDTO passwordResetRequestDTO) throws Exception;
    String updateAvatar(UserImageUpdateRequestDTO userImageUpdateRequestDTO);

    String sendEmailAuthenticationRegister(EmailRequestDTO emailRequestDTO) throws MessagingException, UnsupportedEncodingException;
    String authenRegister(TokenRequestDTO tokenRequestDTO) throws Exception;
    String changePassword(PasswordChangeRequestDTO passwordChangeRequestDTO);
}
