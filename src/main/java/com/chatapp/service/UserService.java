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
import com.chatapp.dto.request.UserInfoFindRequestDTO;
import com.chatapp.dto.request.UserLoginRequestDTO;
import com.chatapp.dto.response.BusinessInfoResponseDTO;
import com.chatapp.dto.response.FacultyInfoResponseDTO;
import com.chatapp.dto.response.StudentInfoResponseDTO;
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

    //student service
    AuthTokenDTO studentRegister(StudentInfoRegisterRequestDTO studentRegisterDTO);
    List<StudentInfoResponseDTO> findAllStudentInfo();
    UserInfoResponseDTO studentUpdateOrSave(StudentInfoUpdateOrSaveRequestDTO studentInfoUpdateOrSaveRequestDTO);

    //faculty service
    List<FacultyInfoResponseDTO> findAllFacultyInfo();
    AuthTokenDTO facultyRegister(FacultyInfoRegisterRequestDTO facultyInfoRegisterRequestDTO);
    List<AuthTokenDTO> facultiesRegister(List<FacultyInfoRegisterRequestDTO> facultyInfoRegisterRequestDTOs);
    UserInfoResponseDTO facultyUpdateOrSave(FacultyInfoUpdateOrSaveRequestDTO facultyInfoUpdateOrSaveRequestDTO);

    List<BusinessInfoResponseDTO> findAllBusinessInfo();
    AuthTokenDTO businessRegister(BusinessInfoRegisterRequestDTO businessInfoRegisterRequestDTO);
    UserInfoResponseDTO businessUpdateOrSave(BusinessInfoUpdateOrSaveRequestDTO businessInfoUpdateOrSaveRequestDTO);

    //find
    List<BaseDTO> findUserByName(UserInfoFindRequestDTO userInfoFindRequestDTO);
}
