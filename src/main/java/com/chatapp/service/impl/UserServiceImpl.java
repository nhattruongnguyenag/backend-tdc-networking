package com.chatapp.service.impl;

import com.chatapp.constant.SystemConstant;
import com.chatapp.converter.request.BusinessInfoRegisterRequestConverter;
import com.chatapp.converter.request.BusinessInfoUpdateOrSaveRequestConverter;
import com.chatapp.converter.request.FacultyInfoRegisterRequestConverter;
import com.chatapp.converter.request.FacultyInfoUpdateOrSaveRequestConverter;
import com.chatapp.converter.request.StudentInfoRegisterRequestConverter;
import com.chatapp.converter.request.StudentInfoUpdateOrSaveRequestConverter;
import com.chatapp.converter.request.UserFollowRequestConverter;
import com.chatapp.converter.request.UserRequestConverter;
import com.chatapp.converter.response.BusinessInfoResponseConverter;
import com.chatapp.converter.response.FacultyInfoResponseConverter;
import com.chatapp.converter.response.FollowByOtherResponseConverter;
import com.chatapp.converter.response.FollowResponseConverter;
import com.chatapp.converter.response.GroupResponseConverter;
import com.chatapp.converter.response.StudentInfoResponseConverter;
import com.chatapp.converter.response.UserFindResponseConverter;
import com.chatapp.converter.response.UserInfoResponseConverter;
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
import com.chatapp.dto.response.GroupResponseDTO;
import com.chatapp.dto.response.StudentInfoResponseDTO;
import com.chatapp.dto.response.UserFindResponseDTO;
import com.chatapp.dto.response.UserFollowResponseDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.entity.FollowEntity;
import com.chatapp.entity.GroupEntity;
import com.chatapp.entity.RoleEntity;
import com.chatapp.entity.TokenResetPasswordEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.enums.GroupDefault;
import com.chatapp.enums.Role;
import com.chatapp.exception.DuplicateUsernameException;
import com.chatapp.repository.BusinessInfoRepository;
import com.chatapp.repository.FacultyInfoRepository;
import com.chatapp.repository.FollowReposittory;
import com.chatapp.repository.GroupRepository;
import com.chatapp.repository.RoleRepository;
import com.chatapp.repository.StudentInfoRepository;
import com.chatapp.repository.TokenRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.EmailService;
import com.chatapp.service.UserService;
import com.chatapp.util.TokenProvider;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
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
    private FacultyInfoRepository facultyInfoRepository;
    @Autowired
    private BusinessInfoRepository businessInfoRepository;
    @Autowired
    private FollowReposittory followReposittory;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;
    @Autowired
    private StudentInfoResponseConverter studentInfoResponeConverter;
    @Autowired
    private FacultyInfoResponseConverter facultyInfoResponeConverter;
    @Autowired
    private BusinessInfoResponseConverter businessInfoResponeConverter;
    @Autowired
    private UserFindResponseConverter userFindResponseConverter;
    @Autowired
    private FollowResponseConverter followResponseConverter;
    @Autowired
    private FollowByOtherResponseConverter followByOtherResponseConverter;
    @Autowired
    private GroupResponseConverter groupResponseConverter;

    @Autowired
    private UserRequestConverter userConverter;
    @Autowired
    private StudentInfoRegisterRequestConverter studentInfoRegisterRequestConverter;
    @Autowired
    private StudentInfoUpdateOrSaveRequestConverter studentInfoUpdateOrSaveRequestConverter;
    @Autowired
    private FacultyInfoUpdateOrSaveRequestConverter facultyInfoUpdateOrSaveRequestConverter;
    @Autowired
    private BusinessInfoUpdateOrSaveRequestConverter businessInfoUpdateOrSaveRequestConverter;
    @Autowired
    private FacultyInfoRegisterRequestConverter facultyInfoRegisterRequestConverter;
    @Autowired
    private BusinessInfoRegisterRequestConverter businessInfoRegisterRequestConverter;
    @Autowired
    private UserFollowRequestConverter userFollowRequestConverter;

    @Autowired
    private EmailService emailService;

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
        userEntity.setUpdatedAt(new Date());

        return userInfoResponseConverter.toDTO(userRepository.save(userEntity));
    }

    @Override
    public boolean setIsMessageFocusIn(Long userId) {
        return setMessengerConnectState(userId, true);
    }

    @Override
    public boolean setIsMessageFocusOut(Long userId) {
        return setMessengerConnectState(userId, false);
    }

    @Override
    public boolean setIsTypingOn(Long userId) {
        return setTypingState(userId, true);
    }

    @Override
    public boolean setIsTypingOff(Long userId) {
        return setTypingState(userId, false);
    }

    private boolean setMessengerConnectState(Long userId, boolean status) {
        UserEntity userEntity = userRepository.findOneById(userId);

        if (userEntity == null) {
            throw new RuntimeException("user_does_not_exists");
        }
        userEntity.setMessageConnect(status);

        return userRepository.save(userEntity) != null;
    }

    private boolean setTypingState(Long userId, boolean status) {
        UserEntity userEntity = userRepository.findOneById(userId);

        if (userEntity == null) {
            throw new RuntimeException("user_does_not_exists");
        }
        userEntity.setTyping(status);

        return userRepository.save(userEntity) != null;
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
    public BaseDTO getUserFromToken(String token) {
        BaseDTO dto = null;
        String email = tokenProvider.extractEmailFromToken(token);
        UserEntity userEntity = userRepository.findOneByEmail(email);
        if (studentInfoRepository.findOneByUser_Id(userEntity.getId()) != null) {
            StudentInfoResponseDTO studentInfoResponeDTO = studentInfoResponeConverter
                    .toDTO(studentInfoRepository.findOneByUser_Id(userEntity.getId()));
            dto = studentInfoResponeDTO;
        } else if (facultyInfoRepository.findOneByUser_Id(userEntity.getId()) != null) {
            FacultyInfoResponseDTO facultyInfoResponeDTO = facultyInfoResponeConverter
                    .toDTO(facultyInfoRepository.findOneByUser_Id(userEntity.getId()));
            dto = facultyInfoResponeDTO;
        } else if (businessInfoRepository.findOneByUser_Id(userEntity.getId()) != null) {
            BusinessInfoResponseDTO businessInfoResponseDTO = businessInfoResponeConverter
                    .toDTO(businessInfoRepository.findOneByUser_Id(userEntity.getId()));
            dto = businessInfoResponseDTO;
        }
        return dto;
    }

    @Override
    public UserInfoResponseDTO getUserByEmail(String email) {
        return userInfoResponseConverter.toDTO(userRepository.findOneByEmail(email));
    }

    @Override
    public UserInfoResponseDTO getUserById(UserGetRequestDTO userGetRequestDTO) {
        return userInfoResponseConverter.toDTO(userRepository.findOneById(userGetRequestDTO.getId()));
    }

    // studentInfo service
    @Override
    public AuthTokenDTO studentRegister(StudentInfoRegisterRequestDTO studentRegisterDTO) {
        UserEntity userEntity;
        RoleEntity roleEntity = roleRepository.findOneByCode(Role.STUDENT.getName());
        List<RoleEntity> roles = new ArrayList<RoleEntity>();
        roles.add(roleEntity);

        if (userRepository.findOneByEmail(studentRegisterDTO.getEmail()) != null) {
            throw new DuplicateUsernameException("user_already_exists");
        }
        if (userRepository.findOneByCode(studentRegisterDTO.getCode()) != null) {
            throw new DuplicateUsernameException("user_code_already_exists");
        }
        studentRegisterDTO.setPassword(passwordEncoder.encode(studentRegisterDTO.getPassword()));

        userEntity = studentInfoRegisterRequestConverter.toEntity(studentRegisterDTO);
        setGroupToStudent(userEntity);
        userEntity.setRoles(roles);
        userEntity.setStatus((byte) 0);
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
    public List<StudentInfoResponseDTO> findAllStudentInfo() {
        return studentInfoResponeConverter.toDTOGroup(studentInfoRepository.findAll());
    }

    private UserEntity studentUpdate(StudentInfoUpdateOrSaveRequestDTO studentInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity;
        userEntity = studentInfoUpdateOrSaveRequestConverter.toUpdateEntity(studentInfoUpdateOrSaveRequestDTO);
        return userEntity;
    }

    private UserEntity studentSave(StudentInfoUpdateOrSaveRequestDTO studentInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity = studentInfoUpdateOrSaveRequestConverter.toEntity(studentInfoUpdateOrSaveRequestDTO);
        RoleEntity roleEntity = roleRepository.findOneByCode(Role.STUDENT.getName());
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
        if (userRepository.findOneByCode(studentInfoUpdateOrSaveRequestDTO.getCode()) != null) {
            throw new DuplicateUsernameException("user_code_already_exists");
        }
        if (studentInfoUpdateOrSaveRequestDTO.getId() != null) {
            userEntity = this.studentUpdate(studentInfoUpdateOrSaveRequestDTO);
        } else {
            userEntity = this.studentSave(studentInfoUpdateOrSaveRequestDTO);
        }
        return userInfoResponseConverter.toDTO(userRepository.save(userEntity));
    }

    // facultyInfo service
    @Override
    public AuthTokenDTO facultyRegister(FacultyInfoRegisterRequestDTO facultyInfoRegisterRequestDTO) {
        UserEntity userEntity;
        RoleEntity roleEntity = roleRepository.findOneByCode(Role.FACULTY.getName());
        List<RoleEntity> roles = new ArrayList<RoleEntity>();
        roles.add(roleEntity);

        if (userRepository.findOneByEmail(facultyInfoRegisterRequestDTO.getEmail()) != null) {
            throw new DuplicateUsernameException("user_already_exists");
        }
        if (userRepository.findOneByCode(facultyInfoRegisterRequestDTO.getCode()) != null) {
            throw new DuplicateUsernameException("user_code_already_exists");
        }
        facultyInfoRegisterRequestDTO
                .setPassword(passwordEncoder.encode(facultyInfoRegisterRequestDTO.getPassword()));

        userEntity = facultyInfoRegisterRequestConverter.toEntity(facultyInfoRegisterRequestDTO);
        userEntity.setRoles(roles);
        userEntity.setStatus((byte) 0);
        userRepository.save(userEntity);

        final String token = tokenProvider.generateToken(facultyInfoRegisterRequestDTO.getEmail());
        return new AuthTokenDTO(token);
    }

    @Override
    public List<FacultyInfoResponseDTO> findAllFacultyInfo() {
        return facultyInfoResponeConverter.toDTOGroup(facultyInfoRepository.findAll());
    }

    private UserEntity facultyUpdate(FacultyInfoUpdateOrSaveRequestDTO facultyInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity;
        userEntity = facultyInfoUpdateOrSaveRequestConverter.toUpdateEntity(facultyInfoUpdateOrSaveRequestDTO);
        return userEntity;
    }

    private UserEntity facultySave(FacultyInfoUpdateOrSaveRequestDTO facultyInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity = facultyInfoUpdateOrSaveRequestConverter.toEntity(facultyInfoUpdateOrSaveRequestDTO);
        RoleEntity roleEntity = roleRepository.findOneByCode(Role.FACULTY.getName());
        List<RoleEntity> roles = new ArrayList<RoleEntity>();
        roles.add(roleEntity);
        userEntity.setPassword(passwordEncoder.encode(this.DEFAULT_PASSWORD));
        userEntity.setStatus((byte) 0);
        userEntity.setRoles(roles);
        return userEntity;
    }

    @Override
    public UserInfoResponseDTO facultyUpdateOrSave(
            FacultyInfoUpdateOrSaveRequestDTO facultyInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity;
        if (userRepository.findOneByEmail(facultyInfoUpdateOrSaveRequestDTO.getEmail()) != null) {
            throw new DuplicateUsernameException("user_already_exists");
        }
        if (userRepository.findOneByCode(facultyInfoUpdateOrSaveRequestDTO.getCode()) != null) {
            throw new DuplicateUsernameException("user_code_already_exists");
        }
        if (facultyInfoUpdateOrSaveRequestDTO.getId() != null) {
            userEntity = this.facultyUpdate(facultyInfoUpdateOrSaveRequestDTO);
        } else {
            userEntity = this.facultySave(facultyInfoUpdateOrSaveRequestDTO);
        }
        return userInfoResponseConverter.toDTO(userRepository.save(userEntity));
    }

    // business service
    @Override
    public List<BusinessInfoResponseDTO> findAllBusinessInfo() {
        return businessInfoResponeConverter.toDTOGroup(businessInfoRepository.findAll());
    }

    @Override
    public AuthTokenDTO businessRegister(BusinessInfoRegisterRequestDTO businessInfoRegisterRequestDTO) {
        UserEntity userEntity;
        RoleEntity roleEntity = roleRepository.findOneByCode(Role.BUSINESS.getName());
        List<RoleEntity> roles = new ArrayList<RoleEntity>();
        roles.add(roleEntity);

        if (userRepository.findOneByEmail(businessInfoRegisterRequestDTO.getEmail()) != null) {
            throw new DuplicateUsernameException("user_already_exists");
        }
        if (userRepository.findOneByCode(businessInfoRegisterRequestDTO.getCode()) != null) {
            throw new DuplicateUsernameException("user_code_already_exists");
        }
        businessInfoRegisterRequestDTO
                .setPassword(passwordEncoder.encode(businessInfoRegisterRequestDTO.getPassword()));

        userEntity = businessInfoRegisterRequestConverter.toEntity(businessInfoRegisterRequestDTO);
        userEntity.setRoles(roles);
        userEntity.setStatus((byte) 0);
        userRepository.save(userEntity);

        final String token = tokenProvider.generateToken(businessInfoRegisterRequestDTO.getEmail());
        return new AuthTokenDTO(token);
    }

    private UserEntity businessUpdate(BusinessInfoUpdateOrSaveRequestDTO businessInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity;
        userEntity = businessInfoUpdateOrSaveRequestConverter.toUpdateEntity(businessInfoUpdateOrSaveRequestDTO);
        return userEntity;
    }

    private UserEntity businessSave(BusinessInfoUpdateOrSaveRequestDTO businessInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity = businessInfoUpdateOrSaveRequestConverter.toEntity(businessInfoUpdateOrSaveRequestDTO);
        RoleEntity roleEntity = roleRepository.findOneByCode(Role.BUSINESS.getName());
        List<RoleEntity> roles = new ArrayList<RoleEntity>();
        roles.add(roleEntity);
        userEntity.setPassword(passwordEncoder.encode(this.DEFAULT_PASSWORD));
        userEntity.setStatus((byte) 0);
        userEntity.setRoles(roles);
        return userEntity;
    }

    @Override
    public UserInfoResponseDTO businessUpdateOrSave(
            BusinessInfoUpdateOrSaveRequestDTO businessInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity;
        if (userRepository.findOneByEmail(businessInfoUpdateOrSaveRequestDTO.getEmail()) != null) {
            throw new DuplicateUsernameException("user_already_exists");
        }
        if (userRepository.findOneByCode(businessInfoUpdateOrSaveRequestDTO.getCode()) != null) {
            throw new DuplicateUsernameException("user_code_already_exists");
        }
        if (businessInfoUpdateOrSaveRequestDTO.getId() != null) {
            userEntity = this.businessUpdate(businessInfoUpdateOrSaveRequestDTO);
        } else {
            userEntity = this.businessSave(businessInfoUpdateOrSaveRequestDTO);
        }
        return userInfoResponseConverter.toDTO(userRepository.save(userEntity));
    }

    @Override
    public List<UserFindResponseDTO> findUserByName(UserInfoFindRequestDTO userInfoFindRequestDTO) {
        List<UserFindResponseDTO> userFindResponseDTOs = userFindResponseConverter
                .toDTOGroup(userRepository.findAllByNameContainsAndRoles_Code(userInfoFindRequestDTO.getName(),
                        userInfoFindRequestDTO.getType()));
        userFindResponseDTOs.remove(
                userFindResponseConverter.toDTO(userRepository.findOneById(userInfoFindRequestDTO.getUserId())));
        for (UserFindResponseDTO userFindResponseDTO : userFindResponseDTOs) {
            // set follow
            UserEntity entity = userRepository.findOneById(userInfoFindRequestDTO.getUserId());
            if (entity.getFollowUsers().size() > 0) {
                for (FollowEntity followEntity : entity.getFollowUsers()) {
                    if (followEntity.getUserFollow().getId() == userFindResponseDTO.getId()) {
                        userFindResponseDTO.setIsFollow(true);
                        break;
                    }
                    userFindResponseDTO.setIsFollow(false);
                }
            } else {
                userFindResponseDTO.setIsFollow(false);
            }
        }
        return userFindResponseDTOs;
    }

    @Override
    public List<AuthTokenDTO> facultiesRegister(List<FacultyInfoRegisterRequestDTO> facultyInfoRegisterRequestDTOs) {
        List<AuthTokenDTO> tokenDTOs = new ArrayList<AuthTokenDTO>();
        for (FacultyInfoRegisterRequestDTO facultyInfoRegisterRequestDTO : facultyInfoRegisterRequestDTOs) {
            AuthTokenDTO token = this.facultyRegister(facultyInfoRegisterRequestDTO);
            tokenDTOs.add(token);
        }
        return tokenDTOs;
    }

    @Override
    public String follow(UserFollowRequestDTO userFollowRequestDTO) {
        if (userRepository.findById(userFollowRequestDTO.getUserId()) == null) {
            throw new DuplicateUsernameException("user_not_exists");
        }
        if (userRepository.findById(userFollowRequestDTO.getUserFollowId()) == null) {
            throw new DuplicateUsernameException("user_follow_not_exists");
        }
        if (followReposittory.findOneByUser_IdAndFollow_Id(userFollowRequestDTO.getUserId(),
                userFollowRequestDTO.getUserFollowId()) != null) {
            FollowEntity followEntity = followReposittory.findOneByUser_IdAndFollow_Id(
                    userFollowRequestDTO.getUserId(), userFollowRequestDTO.getUserFollowId());
            followReposittory.delete(followEntity);
        } else {
            FollowEntity followEntity = userFollowRequestConverter.toEntity(userFollowRequestDTO);
            followReposittory.save(followEntity);
        }
        return "";
    }

    @Override
    public StudentInfoResponseDTO getStudentDetailByUserId(Long userId) {
        UserEntity userEntity = userRepository.findOneById(userId);
        UserInfoResponseDTO userInfoResponseDTO = userInfoResponseConverter.toDTO(userEntity);
        if (userInfoResponseDTO.getRoleCodes().equals(Role.STUDENT.getName())) {
            StudentInfoResponseDTO studentInfoResponseDTO = studentInfoResponeConverter
                    .toDTO(studentInfoRepository.findOneByUser_Id(userId));
            return studentInfoResponseDTO;
        } else {
            throw new RuntimeException("student_at_this_user_id_not_exist");
        }
    }

    @Override
    public FacultyInfoResponseDTO getFacultyDetailByUserId(Long userId) {
        UserEntity userEntity = userRepository.findOneById(userId);
        UserInfoResponseDTO userInfoResponseDTO = userInfoResponseConverter.toDTO(userEntity);
        if (userInfoResponseDTO.getRoleCodes().equals(Role.FACULTY.getName())) {
            FacultyInfoResponseDTO facultyInfoResponseDTO = facultyInfoResponeConverter
                    .toDTO(facultyInfoRepository.findOneByUser_Id(userId));
            return facultyInfoResponseDTO;
        } else {
            throw new RuntimeException("faculty_at_this_user_id_not_exist");
        }
    }

    @Override
    public BusinessInfoResponseDTO getBusinessDetailByUserId(Long userId) {
        UserEntity userEntity = userRepository.findOneById(userId);
        UserInfoResponseDTO userInfoResponseDTO = userInfoResponseConverter.toDTO(userEntity);
        if (userInfoResponseDTO.getRoleCodes().equals(Role.BUSINESS.getName())) {
            BusinessInfoResponseDTO businessInfoResponseDTO = businessInfoResponeConverter
                    .toDTO(businessInfoRepository.findOneByUser_Id(userId));
            return businessInfoResponseDTO;
        } else {
            throw new RuntimeException("business_at_this_user_id_not_exist");
        }
    }

    @Override
    public List<UserFollowResponseDTO> getFollowsByUserId(UserGetRequestDTO userGetRequestDTO) {
        if (userRepository.findOneById(userGetRequestDTO.getId()) == null) {
            throw new DuplicateUsernameException("user_not_exists");
        }
        UserEntity userEntity = userRepository.findOneById(userGetRequestDTO.getId());
        List<UserFollowResponseDTO> userFollowResponseDTOs = followResponseConverter
                .toDTOGroup(userEntity.getFollowUsers());
        return userFollowResponseDTOs;
    }

    @Override
    public List<UserFollowResponseDTO> getOtherPeopleFollowByUserId(UserGetRequestDTO userGetRequestDTO) {
        if (userRepository.findOneById(userGetRequestDTO.getId()) == null) {
            throw new DuplicateUsernameException("user_not_exists");
        }
        UserEntity userEntity = userRepository.findOneById(userGetRequestDTO.getId());
        List<UserFollowResponseDTO> userFollowResponseDTOs = followByOtherResponseConverter
                .toDTOGroup(userEntity.getFollowByUsers());
        return userFollowResponseDTOs;
    }

    @Override
    public List<UserFollowResponseDTO> findUserByNameInListFollowingByUserId(UserFindRequestDTO userFindRequestDTO) {
        UserGetRequestDTO userGetRequestDTO = new UserGetRequestDTO();
        userGetRequestDTO.setId(userFindRequestDTO.getUserId());
        List<UserFollowResponseDTO> userFollowResponseDTOs = this.getFollowsByUserId(userGetRequestDTO);
        if (userFindRequestDTO.getSearch().equals("")) {
            return userFollowResponseDTOs;
        }
        List<UserFollowResponseDTO> result = new ArrayList<UserFollowResponseDTO>();
        for (UserFollowResponseDTO userFollowResponseDTO : userFollowResponseDTOs) {
            UserEntity userEntity = userRepository.findOneById(userFollowResponseDTO.getId());
            if (userEntity.getName().contains(userFindRequestDTO.getSearch())) {
                result.add(userFollowResponseDTO);
            }
        }
        return result;
    }

    @Override
    public List<UserFollowResponseDTO> findUserByNameInListFollowerByUserId(UserFindRequestDTO userFindRequestDTO) {
        UserGetRequestDTO userGetRequestDTO = new UserGetRequestDTO();
        userGetRequestDTO.setId(userFindRequestDTO.getUserId());
        List<UserFollowResponseDTO> userFollowResponseDTOs = this.getOtherPeopleFollowByUserId(userGetRequestDTO);
        if (userFindRequestDTO.getSearch().equals("")) {
            return userFollowResponseDTOs;
        }
        List<UserFollowResponseDTO> result = new ArrayList<UserFollowResponseDTO>();
        for (UserFollowResponseDTO userFollowResponseDTO : userFollowResponseDTOs) {
            UserEntity userEntity = userRepository.findOneById(userFollowResponseDTO.getId());
            if (userEntity.getName().contains(userFindRequestDTO.getSearch())) {
                result.add(userFollowResponseDTO);
            }
        }
        return result;
    }

    private void setGroupToStudent(UserEntity userEntity) {
        String falcutyName = userEntity.getStudentInfo().getFacultyName();
        List<GroupEntity> groups = new ArrayList<>();
        groups.add(groupRepository.findOneByCode(GroupDefault.GROUP_TDC.getCodeGroup()));
        groups.add(groupRepository.findOneByCode(GroupDefault.GROUP_KET_NOI_DOANH_NGHIEP.getCodeGroup()));
        if (falcutyName.equals(GroupDefault.GROUP_DIEN_DIEN_TU.getName())) {
            groups.add(groupRepository.findOneByCode(GroupDefault.GROUP_DIEN_DIEN_TU.getCodeGroup()));
        } else if (falcutyName.equals(GroupDefault.GROUP_CONG_NGHE_THONG_TIN.getName())) {
            groups.add(groupRepository.findOneByCode(GroupDefault.GROUP_CONG_NGHE_THONG_TIN.getCodeGroup()));
        } else if (falcutyName.equals(GroupDefault.GROUP_CONG_NGHE_TU_DONG.getName())) {
            groups.add(groupRepository.findOneByCode(GroupDefault.GROUP_CONG_NGHE_TU_DONG.getCodeGroup()));
        } else if (falcutyName.equals(GroupDefault.GROUP_CO_KHI_CHE_TAO_MAY.getName())) {
            groups.add(groupRepository.findOneByCode(GroupDefault.GROUP_CO_KHI_CHE_TAO_MAY.getCodeGroup()));
        } else if (falcutyName.equals(GroupDefault.GROUP_CO_KHI_OTO.getName())) {
            groups.add(groupRepository.findOneByCode(GroupDefault.GROUP_CO_KHI_OTO.getCodeGroup()));
        } else if (falcutyName.equals(GroupDefault.GROUP_TAI_CHINH_KE_TOAN.getName())) {
            groups.add(groupRepository.findOneByCode(GroupDefault.GROUP_TAI_CHINH_KE_TOAN.getCodeGroup()));
        } else if (falcutyName.equals(GroupDefault.GROUP_QUAN_TRI_KINH_DOANH.getName())) {
            groups.add(groupRepository.findOneByCode(GroupDefault.GROUP_QUAN_TRI_KINH_DOANH.getCodeGroup()));
        } else if (falcutyName.equals(GroupDefault.GROUP_DU_LICH.getName())) {
            groups.add(groupRepository.findOneByCode(GroupDefault.GROUP_DU_LICH.getCodeGroup()));
        } else if (falcutyName.equals(GroupDefault.GROUP_TIENG_ANH.getName())) {
            groups.add(groupRepository.findOneByCode(GroupDefault.GROUP_TIENG_ANH.getCodeGroup()));
        } else if (falcutyName.equals(GroupDefault.GROUP_TIENG_HAN.getName())) {
            groups.add(groupRepository.findOneByCode(GroupDefault.GROUP_TIENG_HAN.getCodeGroup()));
        } else if (falcutyName.equals(GroupDefault.GROUP_BO_MON_TIENG_NHAT.getName())) {
            groups.add(groupRepository.findOneByCode(GroupDefault.GROUP_BO_MON_TIENG_NHAT.getCodeGroup()));
        }
        userEntity.setGroups(groups);
    }

    @Override
    public List<GroupResponseDTO> getGroupByUserId(Long userId) {
        if (userRepository.findOneById(userId) == null) {
            throw new DuplicateUsernameException("user_not_exists");
        }
        UserEntity userEntity = userRepository.findOneById(userId);
        List<GroupResponseDTO> groupResponseDTOs = groupResponseConverter.toDTOGroup(userEntity.getGroups());
        return groupResponseDTOs;
    }

    @Override
    public Long checkEmailUser(String email) {
        return userRepository.findOneByEmail(email) != null ? Long.valueOf(0) : Long.valueOf(1);
    }

    // forgot password
    @Override
    public String sendEmail(String email) throws MessagingException, UnsupportedEncodingException{
        if (userRepository.findOneByEmail(email) == null) {
            throw new DuplicateUsernameException("this_email_have_not_registered");
        }
        UserEntity userEntity = userRepository.findOneByEmail(email);
        String token = tokenProvider.generateResetPasswordToken(userEntity.getId());

        TokenResetPasswordEntity tokenResetPasswordEntity = new TokenResetPasswordEntity();
        tokenResetPasswordEntity.setToken(token);
        tokenResetPasswordEntity.setStatus(Long.valueOf(1));
        tokenRepository.save(tokenResetPasswordEntity);

        String urlResetPassword = SystemConstant.BACK_END_MAIN_URL + "reset_password" + "?token=" + token;
        emailService.sendEmail(urlResetPassword , email);
        return "";
    }
}
