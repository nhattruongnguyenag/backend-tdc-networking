package com.chatapp.converter.response;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.response.NormalPostResponseDTO;
import com.chatapp.dto.response.UserInfoResponseDTO;
import com.chatapp.entity.NormalPostEntity;
import com.chatapp.entity.PostEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.enums.PostType;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NormalPostResponseConverter extends BaseConverter<NormalPostEntity, NormalPostResponseDTO> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;

    @Override
    public NormalPostResponseDTO toDTO(NormalPostEntity entity) {
        NormalPostResponseDTO normalPostResponeDTO = super.toDTO(entity);
        normalPostResponeDTO.setId(entity.getPost().getId());
        normalPostResponeDTO.setActive((byte) 1);
        normalPostResponeDTO.setStatus((byte) 0);
        normalPostResponeDTO.setType(PostType.NORMAL.getName());
        PostEntity postEntity = postRepository.findOneById(entity.getPost().getId());
        UserEntity userEntity = userRepository.findOneById(postEntity.getUser().getId());
        UserInfoResponseDTO userInfoResponseDTO = userInfoResponseConverter.toDTO(userEntity);
        String roleCodes = "";
        for (int i = 0; i < userEntity.getRoles().size(); i++) {
            if (i != 0) {
                roleCodes += ",";
            }
            roleCodes += userEntity.getRoles().get(i).getCode();
        }
        userInfoResponseDTO.setRoleCodes(roleCodes);
        normalPostResponeDTO.setUser(userInfoResponseDTO);
        return normalPostResponeDTO;
    }
}
