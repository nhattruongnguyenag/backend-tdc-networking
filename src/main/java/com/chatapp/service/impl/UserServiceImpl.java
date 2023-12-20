package com.chatapp.service.impl;

import com.chatapp.constant.EmailTextConstant;
import com.chatapp.constant.SystemConstant;
import com.chatapp.converter.request.user.UserRequestConverter;
import com.chatapp.converter.request.user.business.BusinessInfoRegisterRequestConverter;
import com.chatapp.converter.request.user.business.BusinessInfoUpdateOrSaveRequestConverter;
import com.chatapp.converter.request.user.faculty.FacultyInfoRegisterRequestConverter;
import com.chatapp.converter.request.user.faculty.FacultyInfoUpdateOrSaveRequestConverter;
import com.chatapp.converter.request.user.follow.UserFollowRequestConverter;
import com.chatapp.converter.request.user.student.StudentInfoRegisterRequestConverter;
import com.chatapp.converter.request.user.student.StudentInfoUpdateOrSaveRequestConverter;
import com.chatapp.converter.response.group.GroupResponseConverter;
import com.chatapp.converter.response.user.UserFindResponseConverter;
import com.chatapp.converter.response.user.UserInfoResponseConverter;
import com.chatapp.converter.response.user.business.BusinessInfoResponseConverter;
import com.chatapp.converter.response.user.faculty.FacultyInfoResponseConverter;
import com.chatapp.converter.response.user.follow.FollowByOtherResponseConverter;
import com.chatapp.converter.response.user.follow.FollowResponseConverter;
import com.chatapp.converter.response.user.student.StudentInfoResponseConverter;
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
import com.chatapp.entity.FollowEntity;
import com.chatapp.entity.GroupEntity;
import com.chatapp.entity.OptionUserEntity;
import com.chatapp.entity.RoleEntity;
import com.chatapp.entity.TokenResetPasswordEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.enums.GroupDefault;
import com.chatapp.enums.Notification;
import com.chatapp.enums.Option;
import com.chatapp.enums.Role;
import com.chatapp.exception.DuplicateUsernameException;
import com.chatapp.repository.BusinessInfoRepository;
import com.chatapp.repository.FacultyInfoRepository;
import com.chatapp.repository.FollowReposittory;
import com.chatapp.repository.GroupRepository;
import com.chatapp.repository.OptionUserRepository;
import com.chatapp.repository.RoleRepository;
import com.chatapp.repository.StudentInfoRepository;
import com.chatapp.repository.TokenRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.EmailService;
import com.chatapp.service.NotificationService;
import com.chatapp.service.UserService;
import com.chatapp.util.TokenProvider;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
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
    private NotificationService notificationService;

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
    OptionUserRepository optionUserRepository;

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
    public UserInfoResponseDTO findById(Long id) {
        UserEntity userEntity = userRepository.findOneById(id);
        if (userEntity != null) {
            return userInfoResponseConverter.toDTO(userEntity);
        }
        throw new RuntimeException("user_does_not_exists");
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

    @Override
    public boolean setUserStatusInactive(Long userId) {
        UserEntity userEntity = userRepository.findOneById(userId);

        if (userEntity == null) {
            throw new RuntimeException("user_does_not_exists");
        }
        userEntity.setStatus((byte) 0);

        return userRepository.save(userEntity) != null;
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
        if (userRepository.findOneByEmail(userLoginRequest.getEmail()).getActive() == 0) {
            throw new DuplicateUsernameException("this_account_have_not_actived");
        }
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getEmail(),
                        userLoginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserEntity entity = userRepository.findOneByEmail(userLoginRequest.getEmail());
        entity.setLastActiveAt(new Date());
        entity.setStatus((byte) 1);
        userRepository.save(entity);
        final String token = tokenProvider.generateToken(authentication.getName());
        return new AuthTokenDTO(token);
    }

    @Override
    public BaseDTO getUserFromToken(String token) {
        String email = tokenProvider.extractEmailFromToken(token);
        UserEntity userEntity = userRepository.findOneByEmail(email);
        BaseDTO dto = userInfoResponseConverter.toDTO(userEntity);
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
    public AuthTokenDTO studentRegister(StudentInfoRegisterRequestDTO studentRegisterDTO)
            throws MessagingException, UnsupportedEncodingException {
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
        List<OptionUserEntity> options = new ArrayList<OptionUserEntity>();
        OptionUserEntity optionUserEntity = new OptionUserEntity();
        optionUserEntity.setUser(userEntity);
        optionUserEntity.setOptionKey(Option.LANGUAGE.getValue());
        optionUserEntity.setValue("vi");
        options.add(optionUserEntity);
        userEntity.setOptions(options);
        userRepository.save(userEntity);

        // final Authentication authentication = authenticationManager.authenticate(
        // new UsernamePasswordAuthenticationToken(
        // studentRegisterDTO.getEmail(),
        // studentRegisterDTO.getPassword()));

        // SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(studentRegisterDTO.getEmail());
        EmailRequestDTO emailRequestDTO = new EmailRequestDTO();
        emailRequestDTO.setTo(studentRegisterDTO.getEmail());
        emailRequestDTO.setSubject(studentRegisterDTO.getSubject());
        emailRequestDTO.setContent(studentRegisterDTO.getContent());
        sendEmailAuthenticationRegister(emailRequestDTO);
        notificationService.addNotification(Notification.REGISTER_SUCCESS.getValue(),
                Notification.REGISTER_SUCCESS.getValue(), userEntity.getId(),
                "", null);
        return new AuthTokenDTO(token);
    }

    @Override
    public List<StudentInfoResponseDTO> findAllStudentInfo() {
        return studentInfoResponeConverter.toDTOGroup(studentInfoRepository.findAll());
    }

    private UserEntity studentUpdate(StudentInfoUpdateOrSaveRequestDTO studentInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity;
        userEntity = studentInfoUpdateOrSaveRequestConverter.toUpdateEntity(studentInfoUpdateOrSaveRequestDTO);
        notificationService.addNotification(Notification.USER_UPDATE.getValue(),
                Notification.USER_UPDATE.getValue(), userEntity.getId(),
                "", null);
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
    public AuthTokenDTO studentUpdateOrSave(
            StudentInfoUpdateOrSaveRequestDTO studentInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity;
        if (userRepository.findOneByEmail(studentInfoUpdateOrSaveRequestDTO.getEmail()) != null
                && !userRepository.findOneByEmail(studentInfoUpdateOrSaveRequestDTO.getEmail())
                        .getEmail().equals(studentInfoUpdateOrSaveRequestDTO.getEmail())) {
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
        userRepository.save(userEntity);
        final String token = tokenProvider.generateToken(studentInfoUpdateOrSaveRequestDTO.getEmail());
        return new AuthTokenDTO(token);
    }

    // facultyInfo service
    @Override
    public AuthTokenDTO facultyRegister(FacultyInfoRegisterRequestDTO facultyInfoRegisterRequestDTO)
            throws MessagingException, UnsupportedEncodingException {
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
        userEntity.setActive((byte) 0);
        userRepository.save(userEntity);

        final String token = tokenProvider.generateToken(facultyInfoRegisterRequestDTO.getEmail());
        EmailRequestDTO emailRequestDTO = new EmailRequestDTO();
        emailRequestDTO.setTo(facultyInfoRegisterRequestDTO.getEmail());
        emailRequestDTO.setSubject(facultyInfoRegisterRequestDTO.getSubject());
        emailRequestDTO.setContent(facultyInfoRegisterRequestDTO.getContent());
        sendEmailAuthenticationRegister(emailRequestDTO);
        return new AuthTokenDTO(token);
    }

    @Override
    public List<FacultyInfoResponseDTO> findAllFacultyInfo() {
        return facultyInfoResponeConverter.toDTOGroup(facultyInfoRepository.findAll());
    }

    private UserEntity facultyUpdate(FacultyInfoUpdateOrSaveRequestDTO facultyInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity;
        userEntity = facultyInfoUpdateOrSaveRequestConverter.toUpdateEntity(facultyInfoUpdateOrSaveRequestDTO);
        notificationService.addNotification(Notification.USER_UPDATE.getValue(),
                Notification.USER_UPDATE.getValue(), userEntity.getId(),
                "", null);
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
    public AuthTokenDTO facultyUpdateOrSave(
            FacultyInfoUpdateOrSaveRequestDTO facultyInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity;
        if (userRepository.findOneByEmail(facultyInfoUpdateOrSaveRequestDTO.getEmail()) != null
                && !userRepository.findOneByEmail(facultyInfoUpdateOrSaveRequestDTO.getEmail())
                        .getEmail().equals(facultyInfoUpdateOrSaveRequestDTO.getEmail())) {
            throw new DuplicateUsernameException("email_already_exists");
        }
        if (facultyInfoUpdateOrSaveRequestDTO.getId() != null) {
            userEntity = this.facultyUpdate(facultyInfoUpdateOrSaveRequestDTO);
        } else {
            userEntity = this.facultySave(facultyInfoUpdateOrSaveRequestDTO);
        }
        userRepository.save(userEntity);
        final String token = tokenProvider.generateToken(facultyInfoUpdateOrSaveRequestDTO.getEmail());
        return new AuthTokenDTO(token);
    }

    // business service
    @Override
    public List<BusinessInfoResponseDTO> findAllBusinessInfo() {
        return businessInfoResponeConverter.toDTOGroup(businessInfoRepository.findAll());
    }

    @Override
    public AuthTokenDTO businessRegister(BusinessInfoRegisterRequestDTO businessInfoRegisterRequestDTO)
            throws MessagingException, UnsupportedEncodingException {
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
        userEntity.setActive((byte) 0);
        List<OptionUserEntity> options = new ArrayList<OptionUserEntity>();
        OptionUserEntity optionUserEntity = new OptionUserEntity();
        optionUserEntity.setUser(userEntity);
        optionUserEntity.setOptionKey(Option.LANGUAGE.getValue());
        optionUserEntity.setValue("vi");
        options.add(optionUserEntity);
        userEntity.setOptions(options);
        userRepository.save(userEntity);

        final String token = tokenProvider.generateToken(businessInfoRegisterRequestDTO.getEmail());
        EmailRequestDTO emailRequestDTO = new EmailRequestDTO();
        emailRequestDTO.setTo(businessInfoRegisterRequestDTO.getEmail());
        emailRequestDTO.setSubject(businessInfoRegisterRequestDTO.getSubject());
        emailRequestDTO.setContent(businessInfoRegisterRequestDTO.getContent());
        sendEmailAuthenticationRegister(emailRequestDTO);
        notificationService.addNotification(Notification.REGISTER_SUCCESS.getValue(),
                Notification.REGISTER_SUCCESS.getValue(), userEntity.getId(),
                "", null);
        return new AuthTokenDTO(token);
    }

    private UserEntity businessUpdate(BusinessInfoUpdateOrSaveRequestDTO businessInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity;
        userEntity = businessInfoUpdateOrSaveRequestConverter.toUpdateEntity(businessInfoUpdateOrSaveRequestDTO);
        notificationService.addNotification(Notification.USER_UPDATE.getValue(),
                Notification.USER_UPDATE.getValue(), userEntity.getId(),
                "", null);
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
    public AuthTokenDTO businessUpdateOrSave(
            BusinessInfoUpdateOrSaveRequestDTO businessInfoUpdateOrSaveRequestDTO) {
        UserEntity userEntity;
        if (userRepository.findOneByEmail(businessInfoUpdateOrSaveRequestDTO.getEmail()) != null
                && !userRepository.findOneByEmail(businessInfoUpdateOrSaveRequestDTO.getEmail())
                        .getEmail().equals(businessInfoUpdateOrSaveRequestDTO.getEmail())) {
            throw new DuplicateUsernameException("email_already_exists");
        }
        if (businessInfoUpdateOrSaveRequestDTO.getId() != null) {
            userEntity = this.businessUpdate(businessInfoUpdateOrSaveRequestDTO);
        } else {
            userEntity = this.businessSave(businessInfoUpdateOrSaveRequestDTO);
        }
        userRepository.save(userEntity);
        final String token = tokenProvider.generateToken(businessInfoUpdateOrSaveRequestDTO.getEmail());
        return new AuthTokenDTO(token);
    }

    @Override
    public List<UserFindResponseDTO> findUserByName(UserInfoFindRequestDTO userInfoFindRequestDTO) {
        List<UserFindResponseDTO> userFindResponseDTOs = userFindResponseConverter
                .toDTOGroup(userRepository.findAllByNameContainsAndRoles_Code(userInfoFindRequestDTO.getName(),
                        userInfoFindRequestDTO.getType()));
        for (UserFindResponseDTO userFindResponseDTO : userFindResponseDTOs) {
            if (userFindResponseDTO.getId() == userInfoFindRequestDTO.getUserId()) {
                userFindResponseDTOs.remove(userFindResponseDTO);
                break;
            }
        }
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
            notificationService.addNotification(Notification.USER_FOLLOW.getValue(),
                    Notification.USER_FOLLOW.getValue(), userFollowRequestDTO.getUserFollowId(),
                    "", userFollowRequestDTO.getUserId());
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
        String falcutyName = userEntity.getStudentInfo().getFaculty().getUser().getName();
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
    public String sendEmailResetPassword(EmailRequestDTO emailRequestDTO)
            throws MessagingException, UnsupportedEncodingException {
        if (userRepository.findOneByEmail(emailRequestDTO.getTo()) == null) {
            throw new DuplicateUsernameException("this_email_have_not_registered");
        }
        UserEntity userEntity = userRepository.findOneByEmail(emailRequestDTO.getTo());
        String token = tokenProvider.generateResetPasswordToken(userEntity.getId());

        TokenResetPasswordEntity tokenResetPasswordEntity = new TokenResetPasswordEntity();
        tokenResetPasswordEntity.setToken(token);
        tokenResetPasswordEntity.setStatus(Long.valueOf(1));
        tokenRepository.save(tokenResetPasswordEntity);
        String urlResetPassword = SystemConstant.RESET_PASSWORD_URL + token;

        if (optionUserRepository.findOneByUser_IdAndOptionKey(userEntity.getId(), "language").getValue()
                .equalsIgnoreCase("vi")) {
            emailService.sendEmail(emailRequestDTO.getTo(), emailRequestDTO.getSubject(),
                    EmailTextConstant.EMAIL_RESET_TEXT_VN(urlResetPassword, emailRequestDTO.getTo()));
        } else if (optionUserRepository.findOneByUser_IdAndOptionKey(userEntity.getId(), "language").getValue()
                .equalsIgnoreCase("en")) {
            emailService.sendEmail(emailRequestDTO.getTo(), emailRequestDTO.getSubject(),
                    EmailTextConstant.EMAIL_RESET_TEXT_EN(urlResetPassword, emailRequestDTO.getTo()));
        } else if (optionUserRepository.findOneByUser_IdAndOptionKey(userEntity.getId(), "language").getValue()
                .equalsIgnoreCase("ja")) {
            emailService.sendEmail(emailRequestDTO.getTo(), emailRequestDTO.getSubject(),
                    EmailTextConstant.EMAIL_RESET_TEXT_JP(urlResetPassword, emailRequestDTO.getTo()));
        }
        return "";
    }

    @Override
    public Long checkToken(String token) throws Exception {
        Long status = Long.valueOf(0);
        checkAllToken();
        if (tokenRepository.findOneByToken(token) != null) {
            if (tokenRepository.findOneByToken(token).getStatus() == 1) {
                status = Long.valueOf(1);
                return status;
            }
        }
        return status;
    }

    public void checkAllToken() throws Exception {
        for (TokenResetPasswordEntity token : tokenRepository.findAll()) {
            if (!tokenProvider.isTokenValid(token.getToken())) {
                token.setStatus(Long.valueOf(1));
                tokenRepository.save(token);
            }
            if (!tokenProvider.isTokenValid(token.getToken())) {
                token.setStatus(Long.valueOf(0));
                tokenRepository.save(token);
            }
        }
    }

    @Override
    public String resetPassword(PasswordResetRequestDTO passwordResetRequestDTO) throws Exception {
        checkAllToken();
        if (tokenRepository.findOneByToken(passwordResetRequestDTO.getToken()) != null) {
            if (tokenRepository.findOneByToken(passwordResetRequestDTO.getToken()).getStatus() == 1) {
                TokenResetPasswordEntity tokenResetPasswordEntity = tokenRepository
                        .findOneByToken(passwordResetRequestDTO.getToken());
                if (!passwordResetRequestDTO.getPassword().equals(passwordResetRequestDTO.getRepassword())) {
                    throw new DuplicateUsernameException("repassword_not_same_password");
                }
                String userId = tokenProvider.extractIdFromToken(passwordResetRequestDTO.getToken());
                UserEntity userEntity = userRepository.findOneById(Long.valueOf(userId));
                String password = passwordEncoder.encode(passwordResetRequestDTO.getPassword());
                userEntity.setPassword(password);
                userRepository.save(userEntity);
                tokenResetPasswordEntity.setStatus(Long.valueOf(0));
                tokenRepository.save(tokenResetPasswordEntity);
                notificationService.addNotification(Notification.CHANGE_PASSWORD_SUCCESS.getValue(),
                        Notification.CHANGE_PASSWORD_SUCCESS.getValue(), userEntity.getId(),
                        "", null);
                return "";
            } else {
                throw new DuplicateUsernameException("token_has_expired");
            }
        } else {
            throw new DuplicateUsernameException("token_has_not_exist");
        }
    }

    @Override
    public String updateAvatar(UserImageUpdateRequestDTO userImageUpdateRequestDTO) {
        UserEntity entity = userRepository.findOneById(userImageUpdateRequestDTO.getUserId());
        if (userImageUpdateRequestDTO.getAvatar() != null) {
            entity.setImage(userImageUpdateRequestDTO.getAvatar());
        }
        if (userImageUpdateRequestDTO.getBackground() != null) {
            entity.setBackground(userImageUpdateRequestDTO.getBackground());
        }
        notificationService.addNotification(Notification.USER_UPDATE_AVATAR.getValue(),
                Notification.USER_UPDATE_AVATAR.getValue(), entity.getId(),
                "", null);
        userRepository.save(entity);
        return "";
    }

    @Override
    public String sendEmailAuthenticationRegister(EmailRequestDTO emailRequestDTO)
            throws MessagingException, UnsupportedEncodingException {
        if (userRepository.findOneByEmail(emailRequestDTO.getTo()) == null) {
            throw new DuplicateUsernameException("this_email_have_not_registered");
        }
        String token = tokenProvider.generateToken(emailRequestDTO.getTo());

        TokenResetPasswordEntity tokenResetPasswordEntity = new TokenResetPasswordEntity();
        tokenResetPasswordEntity.setToken(token);
        tokenResetPasswordEntity.setStatus(Long.valueOf(1));
        tokenRepository.save(tokenResetPasswordEntity);

        String url = SystemConstant.AUTHEN_REGISTER__URL + token;
        emailService.sendEmail(emailRequestDTO.getTo(), emailRequestDTO.getSubject(),
                EmailTextConstant.EMAIL_AUTHEN_REGISTER_TEXT(url, emailRequestDTO.getTo()));
        return "";
    }

    @Override
    public String authenRegister(TokenRequestDTO tokenRequestDTO) throws Exception {
        checkAllToken();
        if (tokenRepository.findOneByToken(tokenRequestDTO.getToken()) != null) {
            if (tokenRepository.findOneByToken(tokenRequestDTO.getToken()).getStatus() == 1) {
                TokenResetPasswordEntity tokenResetPasswordEntity = tokenRepository
                        .findOneByToken(tokenRequestDTO.getToken());
                String email = tokenProvider.extractEmailFromToken(tokenRequestDTO.getToken());
                UserEntity userEntity = userRepository.findOneByEmail(email);
                userEntity.setActive((byte) 1);
                userRepository.save(userEntity);
                tokenResetPasswordEntity.setStatus(Long.valueOf(0));
                tokenRepository.save(tokenResetPasswordEntity);
                return "";
            } else {
                throw new DuplicateUsernameException("token_has_expired");
            }
        } else {
            throw new DuplicateUsernameException("token_has_not_exist");
        }
    }

    @Override
    public String changePassword(PasswordChangeRequestDTO passwordChangeRequestDTO) {
        if (userRepository.findOneById(passwordChangeRequestDTO.getUserId()) == null) {
            throw new DuplicateUsernameException("this_user_has_not_exist");
        }
        UserEntity userEntity = userRepository.findOneById(passwordChangeRequestDTO.getUserId());
        if (passwordEncoder.matches(passwordChangeRequestDTO.getOldPassword(), userEntity.getPassword()) == false) {
            throw new DuplicateUsernameException("old_password_not_correct");
        }
        String password = passwordEncoder.encode(passwordChangeRequestDTO.getPassword());
        userEntity.setPassword(password);
        userRepository.save(userEntity);
        return "alo";
    }
}
