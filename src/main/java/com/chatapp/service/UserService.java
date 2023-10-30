package com.chatapp.service;

import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.UserDTO;
import com.chatapp.dto.request.BusinessInfoRegisterRequestDTO;
import com.chatapp.dto.request.BusinessInfoUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.FacultyInfoRegisterRequestDTO;
import com.chatapp.dto.request.FacultyInfoUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.StudentInfoRegisterRequestDTO;
import com.chatapp.dto.request.StudentInfoUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.UserFindRequestDTO;
import com.chatapp.dto.request.UserFollowRequestDTO;
import com.chatapp.dto.request.UserGetRequestDTO;
import com.chatapp.dto.request.UserInfoFindRequestDTO;
import com.chatapp.dto.request.UserLoginRequestDTO;
import com.chatapp.dto.response.BusinessInfoResponseDTO;
import com.chatapp.dto.response.FacultyInfoResponseDTO;
import com.chatapp.dto.response.StudentInfoResponseDTO;
import com.chatapp.dto.response.UserFindResponseDTO;
import com.chatapp.dto.response.UserFollowResponseDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;

import java.util.List;

public interface UserService {
    List<UserInfoResponseDTO> findAll();
    UserInfoResponseDTO findByEmailAndPassword(String email, String password);
    UserInfoResponseDTO saveOrUpdate(UserDTO userDTO);

    boolean setIsMessageFocusIn(Long userId);

    boolean setIsMessageFocusOut(Long userId);

    boolean setIsTypingOn(Long userId);

    boolean setIsTypingOff(Long userId);

    UserInfoResponseDTO delete(Long userId);
    UserInfoResponseDTO changeStatus(Long userId, Byte status);
    AuthTokenDTO login(UserLoginRequestDTO userDTORequest);
    BaseDTO getUserFromToken(String token);
    UserInfoResponseDTO getUserByEmail(String email);
    UserInfoResponseDTO getUserById(UserGetRequestDTO userGetRequestDTO);

    //student service
    AuthTokenDTO studentRegister(StudentInfoRegisterRequestDTO studentRegisterDTO);
    List<StudentInfoResponseDTO> findAllStudentInfo();
    UserInfoResponseDTO studentUpdateOrSave(StudentInfoUpdateOrSaveRequestDTO studentInfoUpdateOrSaveRequestDTO);
    StudentInfoResponseDTO getStudentDetailByUserId(Long userId);

    //faculty service
    List<FacultyInfoResponseDTO> findAllFacultyInfo();
    AuthTokenDTO facultyRegister(FacultyInfoRegisterRequestDTO facultyInfoRegisterRequestDTO);
    List<AuthTokenDTO> facultiesRegister(List<FacultyInfoRegisterRequestDTO> facultyInfoRegisterRequestDTOs);
    UserInfoResponseDTO facultyUpdateOrSave(FacultyInfoUpdateOrSaveRequestDTO facultyInfoUpdateOrSaveRequestDTO);
    FacultyInfoResponseDTO getFacultyDetailByUserId(Long userId);

    //business
    List<BusinessInfoResponseDTO> findAllBusinessInfo();
    AuthTokenDTO businessRegister(BusinessInfoRegisterRequestDTO businessInfoRegisterRequestDTO);
    UserInfoResponseDTO businessUpdateOrSave(BusinessInfoUpdateOrSaveRequestDTO businessInfoUpdateOrSaveRequestDTO);
    BusinessInfoResponseDTO getBusinessDetailByUserId(Long userId);

    //find
    List<UserFindResponseDTO> findUserByName(UserInfoFindRequestDTO userInfoFindRequestDTO);
    List<UserFollowResponseDTO> findUserByNameInListFollowingByUserId(UserFindRequestDTO userFindRequestDTO);
    List<UserFollowResponseDTO> findUserByNameInListFollowerByUserId(UserFindRequestDTO userFindRequestDTO);


    //follow
    String follow(UserFollowRequestDTO userFollowRequestDTO);
    List<UserFollowResponseDTO> getFollowsByUserId(UserGetRequestDTO userGetRequestDTO);
    List<UserFollowResponseDTO> getOtherPeopleFollowByUserId(UserGetRequestDTO userGetRequestDTO);
}
