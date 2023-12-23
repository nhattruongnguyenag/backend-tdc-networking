package com.chatapp.converter.response.user.student;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.converter.response.user.follow.FollowResponseConverter;
import com.chatapp.dto.response.user.student.StudentInfoResponseDTO;
import com.chatapp.entity.StudentInfoEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.enums.GroupDefault;
import com.chatapp.repository.GroupRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.util.DateTimeUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentInfoResponseConverter extends BaseConverter<StudentInfoEntity, StudentInfoResponseDTO> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    private FollowResponseConverter followResponseConverter;

    @Override
    public StudentInfoResponseDTO toDTO(StudentInfoEntity entity) {
        StudentInfoResponseDTO studentInfoResponeDTO = super.toDTO(entity);
        UserEntity userEntity = userRepository.findOneById(entity.getUser().getId());
        studentInfoResponeDTO.setId(userEntity.getId());
        studentInfoResponeDTO.setEmail(userEntity.getEmail());
        studentInfoResponeDTO.setImage(userEntity.getImage());
        studentInfoResponeDTO.setBackground(userEntity.getBackground());
        studentInfoResponeDTO.setName(userEntity.getName());
        studentInfoResponeDTO.setPhone(userEntity.getPhone());
        studentInfoResponeDTO.setStatus(userEntity.getStatus());
        studentInfoResponeDTO.setCode(userEntity.getCode());
        if (userEntity.getLastActiveAt() != null) {
            studentInfoResponeDTO.setLastActive(DateTimeUtil.convertToTimestamp(userEntity.getLastActiveAt()));
        }
        String roleCodes = "";
        for (int i = 0; i < entity.getUser().getRoles().size(); i++) {
            if (i != 0) {
                roleCodes += ",";
            }
            roleCodes += entity.getUser().getRoles().get(i).getCode();
        }
        studentInfoResponeDTO.setRoleCodes(roleCodes);
        studentInfoResponeDTO.setFacultyName(userEntity.getStudentInfo().getFaculty().getUser().getName());
        studentInfoResponeDTO.setMajor(userEntity.getStudentInfo().getMajor().getName());
        studentInfoResponeDTO.setFacultyGroupCode(getFacultyGroupCode(userEntity).getCodeGroup());
        studentInfoResponeDTO.setFacultyGroupId(getFacultyGroupCode(userEntity).getIdGroup());
        studentInfoResponeDTO.setFollows(followResponseConverter.toDTOGroup(userEntity.getFollowUsers()));
        return studentInfoResponeDTO;
    }

    private GroupDefault getFacultyGroupCode(UserEntity userEntity) {
        String falcutyName = userEntity.getStudentInfo().getFaculty().getUser().getCode();
        if (falcutyName.equals(GroupDefault.GROUP_DIEN_DIEN_TU.getName())) {
            return GroupDefault.GROUP_DIEN_DIEN_TU;
        } else if (falcutyName.equals(GroupDefault.GROUP_CONG_NGHE_THONG_TIN.getName())) {
            return GroupDefault.GROUP_CONG_NGHE_THONG_TIN;
        } else if (falcutyName.equals(GroupDefault.GROUP_CONG_NGHE_TU_DONG.getName())) {
            return GroupDefault.GROUP_CONG_NGHE_TU_DONG;
        } else if (falcutyName.equals(GroupDefault.GROUP_CO_KHI_CHE_TAO_MAY.getName())) {
            return GroupDefault.GROUP_CO_KHI_CHE_TAO_MAY;
        } else if (falcutyName.equals(GroupDefault.GROUP_CO_KHI_OTO.getName())) {
            return GroupDefault.GROUP_CO_KHI_OTO;
        } else if (falcutyName.equals(GroupDefault.GROUP_TAI_CHINH_KE_TOAN.getName())) {
            return GroupDefault.GROUP_TAI_CHINH_KE_TOAN;
        } else if (falcutyName.equals(GroupDefault.GROUP_QUAN_TRI_KINH_DOANH.getName())) {
            return GroupDefault.GROUP_QUAN_TRI_KINH_DOANH;
        } else if (falcutyName.equals(GroupDefault.GROUP_DU_LICH.getName())) {
            return GroupDefault.GROUP_DU_LICH;
        } else if (falcutyName.equals(GroupDefault.GROUP_TIENG_ANH.getName())) {
            return GroupDefault.GROUP_TIENG_ANH;
        } else if (falcutyName.equals(GroupDefault.GROUP_TIENG_HAN.getName())) {
            return GroupDefault.GROUP_TIENG_HAN;
        } else if (falcutyName.equals(GroupDefault.GROUP_BO_MON_TIENG_NHAT.getName())) {
            return GroupDefault.GROUP_BO_MON_TIENG_NHAT;
        } else {
            return null;
        }
    }
}
