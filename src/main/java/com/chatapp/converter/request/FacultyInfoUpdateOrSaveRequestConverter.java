package com.chatapp.converter.request;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.FacultyInfoUpdateOrSaveRequestDTO;
import com.chatapp.entity.FacultyInfoEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.FacultyInfoRepository;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacultyInfoUpdateOrSaveRequestConverter
        extends BaseConverter<UserEntity, FacultyInfoUpdateOrSaveRequestDTO> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FacultyInfoRepository facultyInfoRepository;

    public UserEntity toUpdateEntity(FacultyInfoUpdateOrSaveRequestDTO dto) {
        UserEntity userEntity = userRepository.findOneById(dto.getId());
        userEntity.setId(dto.getId());
        userEntity.setEmail(dto.getEmail());
        userEntity.setName(dto.getName());
        if (dto.getImage() != null) {
            userEntity.setImage(dto.getImage());
        }
        FacultyInfoEntity facultyInfoEntity = facultyInfoRepository.findOneByUser_Id(dto.getId());
        facultyInfoEntity.setUser(userEntity);
        userEntity.setFalcutyInfo(facultyInfoEntity);
        return userEntity;
    }

    @Override
    public UserEntity toEntity(FacultyInfoUpdateOrSaveRequestDTO dto) {
        UserEntity userEntity = super.toEntity(dto);
        FacultyInfoEntity facultyInfoEntity = new FacultyInfoEntity();
        facultyInfoEntity.setUser(userEntity);
        userEntity.setFalcutyInfo(facultyInfoEntity);
        return userEntity;
    }
}