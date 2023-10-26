package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.FacultyInfoResponseDTO;
import com.chatapp.entity.FacultyInfoEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacultyInfoResponseConverter extends BaseConverter<FacultyInfoEntity, FacultyInfoResponseDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowResponseConverter followResponseConverter;

    @Override
    public FacultyInfoResponseDTO toDTO(FacultyInfoEntity entity) {
        FacultyInfoResponseDTO facultyInfoResponeDTO = super.toDTO(entity);
        UserEntity userEntity = userRepository.findOneById(entity.getUser().getId());
        facultyInfoResponeDTO.setId(userEntity.getId());
        facultyInfoResponeDTO.setEmail(userEntity.getEmail());
        facultyInfoResponeDTO.setImage(userEntity.getImage());
        facultyInfoResponeDTO.setName(userEntity.getName());
        facultyInfoResponeDTO.setPhone(userEntity.getPhone());
        facultyInfoResponeDTO.setStatus(userEntity.getStatus());
        facultyInfoResponeDTO.setCode(userEntity.getCode());
        String roleCodes = "";
        for (int i = 0; i < entity.getUser().getRoles().size(); i++) {
            if (i != 0) {
                roleCodes += ",";
            }
            roleCodes += entity.getUser().getRoles().get(i).getCode();
        }
        facultyInfoResponeDTO.setRoleCodes(roleCodes);
        facultyInfoResponeDTO.setFollows(followResponseConverter.toDTOGroup(userEntity.getFollowUsers()));
        return facultyInfoResponeDTO;
    }
}