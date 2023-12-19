package com.chatapp.converter.request.post.normal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatapp.converter.abstracts.BaseConverter;
import com.chatapp.dto.request.post.normal.NormalPostUpdateRequestDTO;
import com.chatapp.entity.NormalPostEntity;
import com.chatapp.entity.PostImageEntity;
import com.chatapp.repository.NormalPostRepository;

@Component
public class NormalPostUpdateRequestConverter extends BaseConverter<NormalPostEntity, NormalPostUpdateRequestDTO> {

    @Autowired
    private NormalPostRepository normalPostRepository;

    @Override
    public NormalPostEntity toEntity(NormalPostUpdateRequestDTO dto) {
        NormalPostEntity normalPostEntity = normalPostRepository.findOneByPost_Id(dto.getPostId());
        normalPostEntity.setContent(dto.getContent());
        List<PostImageEntity> postImageEntityList = normalPostEntity.getPost().getImages();
        if (dto.getImages() != null) {
            if (dto.getImages().size() >= postImageEntityList.size()) {
                for (int index = 0; index < dto.getImages().size(); index++) {
                    if (postImageEntityList.size() <= index) {
                        PostImageEntity postImageEntity = new PostImageEntity();
                        postImageEntity.setPost(normalPostEntity.getPost());
                        postImageEntity.setUri(dto.getImages().get(index));
                        postImageEntityList.add(postImageEntity);
                    } else {
                        postImageEntityList.get(index).setUri(dto.getImages().get(index));
                    }
                }
            } else {
                postImageEntityList.removeAll(postImageEntityList);
                for (int index = 0; index < dto.getImages().size(); index++) {
                    PostImageEntity postImageEntity = new PostImageEntity();
                    postImageEntity.setPost(normalPostEntity.getPost());
                    postImageEntity.setUri(dto.getImages().get(index));
                    postImageEntityList.add(postImageEntity);
                }
            }
        }
        normalPostEntity.getPost().setImages(postImageEntityList);
        normalPostEntity.getPost().setActive((byte)0);
        return normalPostEntity;
    }
}
