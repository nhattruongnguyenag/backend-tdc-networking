package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.StudentInfoResponseDTO;
import com.chatapp.entity.StudentInfoEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentInfoResponseConverter extends BaseConverter<StudentInfoEntity, StudentInfoResponseDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowResponseConverter followResponseConverter;

    @Override
    public StudentInfoResponseDTO toDTO(StudentInfoEntity entity) {
        StudentInfoResponseDTO studentInfoResponeDTO = super.toDTO(entity);
        UserEntity userEntity = userRepository.findOneById(entity.getUser().getId());
        studentInfoResponeDTO.setId(userEntity.getId());
        studentInfoResponeDTO.setEmail(userEntity.getEmail());
        studentInfoResponeDTO.setImage(userEntity.getImage());
        studentInfoResponeDTO.setName(userEntity.getName());
        studentInfoResponeDTO.setStatus(userEntity.getStatus());
        studentInfoResponeDTO.setCode(userEntity.getCode());
        String roleCodes = "";
        for (int i = 0; i < entity.getUser().getRoles().size(); i++) {
            if (i != 0) {
                roleCodes += ",";
            }
            roleCodes += entity.getUser().getRoles().get(i).getCode();
        }
        studentInfoResponeDTO.setRoleCodes(roleCodes);
        studentInfoResponeDTO.setFollows(followResponseConverter.toDTOGroup(userEntity.getFollowUsers()));
        return studentInfoResponeDTO;
    }
}
