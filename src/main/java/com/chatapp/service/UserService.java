package com.chatapp.service;

import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.UserDTO;
import com.chatapp.dto.request.BusinessInfoRegisterRequestDTO;
import com.chatapp.dto.request.BusinessInfoUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.FacultyInfoUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.StudentInfoRegisterRequestDTO;
import com.chatapp.dto.request.StudentInfoUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.UserLoginRequestDTO;
import com.chatapp.dto.response.BusinessInfoResponseDTO;
import com.chatapp.dto.response.FacultyInfoResponeDTO;
import com.chatapp.dto.response.StudentInfoResponeDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;

import java.util.List;

public interface UserService {
    List<UserInfoResponseDTO> findAll();
    UserInfoResponseDTO findByEmailAndPassword(String email, String password);
    UserInfoResponseDTO saveOrUpdate(UserDTO userDTO);
    UserInfoResponseDTO delete(Long userId);
    UserInfoResponseDTO changeStatus(Long userId, Byte status);
    AuthTokenDTO login(UserLoginRequestDTO userDTORequest);
    UserInfoResponseDTO getUserFromToken(String token);
    UserInfoResponseDTO getUserByEmail(String email);

    //student service
    AuthTokenDTO studentRegister(StudentInfoRegisterRequestDTO studentRegisterDTO);
    List<StudentInfoResponeDTO> findAllStudentInfo();
    UserInfoResponseDTO studentUpdateOrSave(StudentInfoUpdateOrSaveRequestDTO studentInfoUpdateOrSaveRequestDTO);

    //faculty service
    List<FacultyInfoResponeDTO> findAllFacultyInfo();
    UserInfoResponseDTO facultyUpdateOrSave(FacultyInfoUpdateOrSaveRequestDTO facultyInfoUpdateOrSaveRequestDTO);

    //bussiness service
    List<BusinessInfoResponseDTO> findAllBusinessInfo();
    AuthTokenDTO businessRegister(BusinessInfoRegisterRequestDTO businessInfoRegisterRequestDTO);
    UserInfoResponseDTO businessUpdateOrSave(BusinessInfoUpdateOrSaveRequestDTO businessInfoUpdateOrSaveRequestDTO);
}
