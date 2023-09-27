package com.chatapp.service.impl;

import com.chatapp.converter.request.StudentInfoRegisterRequestConverter;
import com.chatapp.converter.request.StudentInfoUpdateOrSaveRequestConverter;
import com.chatapp.converter.request.UserRequestConverter;
import com.chatapp.converter.response.FalcutyInfoResponeConverter;
import com.chatapp.converter.response.StudentInfoResponeConverter;
import com.chatapp.converter.response.UserInfoResponseConverter;
import com.chatapp.dto.AuthTokenDTO;
import com.chatapp.dto.UserDTO;
import com.chatapp.dto.request.StudentInfoRegisterRequestDTO;
import com.chatapp.dto.request.StudentInfoUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.UserLoginRequestDTO;
import com.chatapp.dto.response.FalcutyInfoResponeDTO;
import com.chatapp.dto.response.StudentInfoResponeDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.entity.FalcutyInfoEntity;
import com.chatapp.entity.RoleEntity;
import com.chatapp.entity.StudentInfoEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.exception.DuplicateUsernameException;
import com.chatapp.repository.FalcutyInfoRepository;
import com.chatapp.repository.RoleRepository;
import com.chatapp.repository.StudentInfoRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.UserService;
import com.chatapp.util.TokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private Long STUDENT_CODE = Long.valueOf(2);
    private String DEFAULT_PASSWORD = "123456";

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StudentInfoRepository studentInfoRepository;
    @Autowired
    private FalcutyInfoRepository falcutyInfoRepository;

    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;
    @Autowired
    private StudentInfoResponeConverter studentInfoResponeConverter;
    @Autowired
    private FalcutyInfoResponeConverter falcutyInfoResponeConverter;

    @Autowired
    private UserRequestConverter userConverter;
    @Autowired
    private StudentInfoRegisterRequestConverter studentInfoRegisterRequestConverter;
    @Autowired
    private StudentInfoUpdateOrSaveRequestConverter studentInfoUpdateOrSaveRequestConverter;

    @Override
    public List<UserInfoResponseDTO> findAll() {
        return userInfoResponseConverter.toDTOGroup(userRepository.findAllByStatusNot((byte) -1));
    }

    @Override
    public UserInfoResponseDTO findByEmailAndPassword(String email, String password) {
        return userInfoResponseConverter.toDTO(userRepository.findOneByEmailAndPassword(email, password));
    }

    @Override
    public UserInfoResponseDTO saveOrUpdate(UserDTO userDTO) {
        UserEntity userEntity;
        if (userDTO.getId() != null) {
            if (userRepository.findOneById(userDTO.getId()) == null) {
                throw new RuntimeException("user_does_not_exists");
            }
        } else {
            if (userRepository.findOneByEmail(userDTO.getEmail()) != null) {
                throw new DuplicateUsernameException("user_already_exists");
            }

            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userDTO.setStatus((byte) 0);
        }

        userEntity = userConverter.toEntity(userDTO);

        return userInfoResponseConverter.toDTO(userRepository.save(userEntity));
    }

    @Override
    public UserInfoResponseDTO changeStatus(Long userId, Byte status) {
        UserEntity userEntity = userRepository.findOneById(userId);
        if (userEntity == null) {
            throw new RuntimeException("user_does_not_exists");
        }
        userEntity.setStatus(status);

        return userInfoResponseConverter.toDTO(userRepository.save(userEntity));
    }

    @Override
    public UserInfoResponseDTO delete(Long userId) {
        UserEntity userEntity = userRepository.findOneById(userId);

        if (userEntity == null) {
            throw new RuntimeException("user_does_not_exits");
        }

        userEntity.setStatus((byte) 1);
        return userInfoResponseConverter.toDTO(userRepository.save(userEntity));
    }

    @Override
    public AuthTokenDTO login(UserLoginRequestDTO userLoginRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getEmail(),
                        userLoginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication.getName());
        return new AuthTokenDTO(token);
    }

    @Override
    public UserInfoResponseDTO getUserFromToken(String token) {
        String email = tokenProvider.extractEmailFromToken(token);
        UserEntity userEntity = userRepository.findOneByEmail(email);
        return userInfoResponseConverter.toDTO(userEntity);
    }

    @Override
    public UserInfoResponseDTO getUserByEmail(String email) {
        return userInfoResponseConverter.toDTO(userRepository.findOneByEmail(email));
    }

    // studentInfo service
    @Override
    public AuthTokenDTO studentRegister(StudentInfoRegisterRequestDTO studentRegisterDTO) {
        UserEntity userEntity;
        RoleEntity roleEntity = roleRepository.findOneById(STUDENT_CODE);
        List<RoleEntity> roles = new ArrayList<RoleEntity>();
        roles.add(roleEntity);

        if (userRepository.findOneByEmail(studentRegisterDTO.getEmail()) != null) {
            throw new DuplicateUsernameException("user_already_exists");
        }
        studentRegisterDTO.setPassword(passwordEncoder.encode(studentRegisterDTO.getPassword()));
        studentRegisterDTO.setStatus((byte) 0);

        userEntity = studentInfoRegisterRequestConverter.toEntity(studentRegisterDTO);
        userEntity.setRoles(roles);
        userRepository.save(userEntity);

        // final Authentication authentication = authenticationManager.authenticate(
        // new UsernamePasswordAuthenticationToken(
        // studentRegisterDTO.getEmail(),
        // studentRegisterDTO.getPassword()));

        // SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(studentRegisterDTO.getEmail());
        return new AuthTokenDTO(token);
    }

    @Override
    public List<StudentInfoResponeDTO> findAllStudentInfo() {
        return studentInfoResponeConverter.toDTOGroup(studentInfoRepository.findAll());
    }

    private UserEntity studentUpdate(StudentInfoUpdateOrSaveRequestDTO studentInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity;
        userEntity = studentInfoUpdateOrSaveRequestConverter.toUpdatEntity(studentInfoUpdateOrSaveRequestDTO);
        return userEntity;
    }

    private UserEntity studentSave(StudentInfoUpdateOrSaveRequestDTO studentInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity = studentInfoUpdateOrSaveRequestConverter.toEntity(studentInfoUpdateOrSaveRequestDTO);
        RoleEntity roleEntity = roleRepository.findOneById(STUDENT_CODE);
        List<RoleEntity> roles = new ArrayList<RoleEntity>();
        roles.add(roleEntity);
        userEntity.setPassword(passwordEncoder.encode(this.DEFAULT_PASSWORD));
        userEntity.setStatus((byte) 0);
        userEntity.setRoles(roles);
        return userEntity;
    }

    @Override
    public UserInfoResponseDTO studentUpdateOrSave(
            StudentInfoUpdateOrSaveRequestDTO studentInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity;
        if (userRepository.findOneByEmail(studentInfoUpdateOrSaveRequestDTO.getEmail()) != null) {
            throw new DuplicateUsernameException("user_already_exists");
        }
        if (studentInfoUpdateOrSaveRequestDTO.getId() != null) {
            userEntity = this.studentUpdate(studentInfoUpdateOrSaveRequestDTO);
        } else {
            userEntity = this.studentSave(studentInfoUpdateOrSaveRequestDTO);
        }
        return userInfoResponseConverter.toDTO(userRepository.save(userEntity));
    }

    
    // falcutyInfo service
    @Override
    public List<FalcutyInfoResponeDTO> findAllFalcutyInfo() {
        return falcutyInfoResponeConverter.toDTOGroup(falcutyInfoRepository.findAll());
    }
}
