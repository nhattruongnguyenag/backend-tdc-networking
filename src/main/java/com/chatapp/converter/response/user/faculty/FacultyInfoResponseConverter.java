package com.chatapp.converter.response.user.faculty;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.converter.response.user.follow.FollowResponseConverter;
import com.chatapp.dto.response.user.faculty.FacultyInfoResponseDTO;
import com.chatapp.entity.FacultyInfoEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.enums.GroupDefault;
import com.chatapp.repository.FacultyInfoRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.util.DateTimeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacultyInfoResponseConverter extends BaseConverter<FacultyInfoEntity, FacultyInfoResponseDTO> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FacultyInfoRepository facultyInfoRepository;

    @Autowired
    private FollowResponseConverter followResponseConverter;

    @Override
    public FacultyInfoResponseDTO toDTO(FacultyInfoEntity entity) {
        FacultyInfoResponseDTO facultyInfoResponeDTO = super.toDTO(entity);
        UserEntity userEntity = userRepository.findOneById(entity.getUser().getId());
        facultyInfoResponeDTO.setId(userEntity.getId());
        facultyInfoResponeDTO.setEmail(userEntity.getEmail());
        facultyInfoResponeDTO.setImage(userEntity.getImage());
        facultyInfoResponeDTO.setBackground(userEntity.getBackground());
        facultyInfoResponeDTO.setName(userEntity.getName());
        facultyInfoResponeDTO.setPhone(userEntity.getPhone());
        facultyInfoResponeDTO.setStatus(userEntity.getStatus());
        facultyInfoResponeDTO.setCode(userEntity.getCode());
        if (userEntity.getLastActiveAt() != null) {
            facultyInfoResponeDTO.setLastActive(DateTimeUtil.convertToTimestamp(userEntity.getLastActiveAt()));
        }
        String roleCodes = "";
        for (int i = 0; i < entity.getUser().getRoles().size(); i++) {
            if (i != 0) {
                roleCodes += ",";
            }
            roleCodes += entity.getUser().getRoles().get(i).getCode();
        }
        facultyInfoResponeDTO.setRoleCodes(roleCodes);
        if (facultyInfoRepository.findOneByUser_Id(userEntity.getId()).getMajors().size() > 0) {
            facultyInfoResponeDTO.setFacultyGroupCode(getFacultyGroupCode(userEntity).getCodeGroup());
            facultyInfoResponeDTO.setFacultyGroupId(getFacultyGroupCode(userEntity).getIdGroup());
        }
        facultyInfoResponeDTO.setFollows(followResponseConverter.toDTOGroup(userEntity.getFollowUsers()));
        return facultyInfoResponeDTO;
    }

    private GroupDefault getFacultyGroupCode(UserEntity userEntity) {
        String falcutyName = userEntity.getCode();
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