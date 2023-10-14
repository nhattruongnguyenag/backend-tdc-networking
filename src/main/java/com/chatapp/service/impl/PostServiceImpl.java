package com.chatapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.converter.request.CommentSaveRequestConverter;
import com.chatapp.converter.request.LikeRequestConverter;
import com.chatapp.converter.request.NormalPostUpdateOrSaveRequestConverter;
import com.chatapp.converter.request.RecruitmentPosyUpdateOrSaveRequestConverter;
import com.chatapp.converter.request.SurveySaveRequestConverter;
import com.chatapp.converter.response.NormalPostResponseConverter;
import com.chatapp.converter.response.PostInfoResponseConverter;
import com.chatapp.converter.response.RecruitmentPostResponseConverter;
import com.chatapp.converter.response.SurveyResponeConverter;
import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.request.CommentDeleteRequestDTO;
import com.chatapp.dto.request.CommentSaveRequestDTO;
import com.chatapp.dto.request.LikeRequestDTO;
import com.chatapp.dto.request.NormalPostUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.PostFindRequestDTO;
import com.chatapp.dto.request.RecruitmentPostUpdateOrSageRequestDTO;
import com.chatapp.dto.request.SurveySaveRequestDTO;
import com.chatapp.dto.response.NormalPostResponseDTO;
import com.chatapp.dto.response.PostInfoResponseDTO;
import com.chatapp.dto.response.RecruitmentPostResponseDTO;
import com.chatapp.dto.response.SurveyResponeDTO;
import com.chatapp.entity.PostCommentEntity;
import com.chatapp.entity.PostEntity;
import com.chatapp.entity.PostLikeEntity;
import com.chatapp.enums.PostType;
import com.chatapp.exception.DuplicateUsernameException;
import com.chatapp.repository.NormalPostRepository;
import com.chatapp.repository.PostCommentRepository;
import com.chatapp.repository.PostLikeRepository;
import com.chatapp.repository.PostRepository;
import com.chatapp.repository.QuestionRepository;
import com.chatapp.repository.RecruitmentPostRepository;
import com.chatapp.repository.SurveyPostRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.PostService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NormalPostRepository normalPostRepository;
    @Autowired
    private RecruitmentPostRepository recruitmentPostRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private SurveyPostRepository surveyPostRepository;
    @Autowired
    private PostLikeRepository postLikeRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;

    @Autowired
    private PostInfoResponseConverter postInfoResponeConverter;
    @Autowired
    private NormalPostResponseConverter normalPostResponeConverter;
    @Autowired
    private RecruitmentPostResponseConverter recruitmentPostResponeConverter;
    @Autowired
    private SurveyResponeConverter surveyResponeConverter;

    @Autowired
    private NormalPostUpdateOrSaveRequestConverter normalPostUpdateOrSaveRequestConverter;
    @Autowired
    private RecruitmentPosyUpdateOrSaveRequestConverter recruitmentPosyUpdateOrSaveRequestConverter;
    @Autowired
    private SurveySaveRequestConverter surveySaveRequestConverter;
    @Autowired
    private LikeRequestConverter likeRequestConverter;
    @Autowired
    private CommentSaveRequestConverter commentSaveRequestConverter;

    @Override
    public List<BaseDTO> findAll() {
        List<BaseDTO> dtos = new ArrayList<>();
        List<PostInfoResponseDTO> responseDTOs = postInfoResponeConverter
                .toDTOGroup(postRepository.findAllByOrderByUpdatedAtDesc());
        for (int i = 0; i < responseDTOs.size(); i++) {
            BaseDTO dto = null;
            if (responseDTOs.get(i).getType().equals(PostType.NORMAL.getName())) {
                NormalPostResponseDTO normalPostResponseDTO = normalPostResponeConverter
                        .toDTO(normalPostRepository.findOneByPost_Id(responseDTOs.get(i).getId()));
                dto = normalPostResponseDTO;
            } else if (responseDTOs.get(i).getType().equals(PostType.RECRUIMENT.getName())) {
                RecruitmentPostResponseDTO recruitmentPostResponseDTO = recruitmentPostResponeConverter
                        .toDTO(recruitmentPostRepository.findOneByPost_Id(responseDTOs.get(i).getId()));
                dto = recruitmentPostResponseDTO;
            } else if (responseDTOs.get(i).getType().equals(PostType.SURVEY.getName())) {
                SurveyResponeDTO surveyResponeDTO = surveyResponeConverter
                        .toDTO(surveyPostRepository.findOneByPost_Id(responseDTOs.get(i).getId()));
                dto = surveyResponeDTO;
            }
            dtos.add(dto);
        }
        return dtos;
    }

    // normal post
    @Override
    public List<NormalPostResponseDTO> findAllNormalPost() {
        return normalPostResponeConverter.toDTOGroup(normalPostRepository.findAll());
    }

    private PostEntity normalPostUpdate(NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO) {
        PostEntity postEntity;
        postEntity = normalPostUpdateOrSaveRequestConverter.toUpdatEntity(normalPostUpdateOrSaveRequestDTO);
        return postEntity;
    }

    private PostEntity normalPostSave(NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO) {
        PostEntity postEntity = normalPostUpdateOrSaveRequestConverter.toEntity(normalPostUpdateOrSaveRequestDTO);
        postEntity.setActive((byte) 1);
        postEntity.setStatus((byte) 0);
        return postEntity;
    }

    @Override
    public String normalPostUpdateOrSave(
            NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO) {
        PostEntity postEntity;
        if (normalPostUpdateOrSaveRequestDTO.getId() != null) {
            postEntity = this.normalPostUpdate(normalPostUpdateOrSaveRequestDTO);
        } else {
            postEntity = this.normalPostSave(normalPostUpdateOrSaveRequestDTO);
        }
        postRepository.save(postEntity);
        return "";
    }

    // recruitment post
    @Override
    public List<RecruitmentPostResponseDTO> findAllRecruitmentPost() {
        return recruitmentPostResponeConverter.toDTOGroup(recruitmentPostRepository.findAll());
    }

    private PostEntity recruitmentPostUpdate(
            RecruitmentPostUpdateOrSageRequestDTO recruitmentPostUpdateOrSageRequestDTO) {
        PostEntity postEntity;
        postEntity = recruitmentPosyUpdateOrSaveRequestConverter.toUpdatEntity(recruitmentPostUpdateOrSageRequestDTO);
        return postEntity;
    }

    private PostEntity recruitmentPostSave(
            RecruitmentPostUpdateOrSageRequestDTO recruitmentPostUpdateOrSageRequestDTO) {
        PostEntity postEntity = recruitmentPosyUpdateOrSaveRequestConverter
                .toEntity(recruitmentPostUpdateOrSageRequestDTO);
        postEntity.setActive((byte) 1);
        postEntity.setStatus((byte) 0);
        return postEntity;
    }

    @Override
    public String recruitmentPostUpdateOrSave(
            RecruitmentPostUpdateOrSageRequestDTO recruitmentPostUpdateOrSageRequestDTO) {
        PostEntity postEntity;
        if (recruitmentPostUpdateOrSageRequestDTO.getId() != null) {
            postEntity = this.recruitmentPostUpdate(recruitmentPostUpdateOrSageRequestDTO);
        } else {
            postEntity = this.recruitmentPostSave(recruitmentPostUpdateOrSageRequestDTO);
        }
        postRepository.save(postEntity);
        return "";
    }

    @Override
    public String saveSurvey(SurveySaveRequestDTO saveRequestDTO) {
        PostEntity postEntity = surveySaveRequestConverter.toPostEntity(saveRequestDTO);
        postEntity.setActive((byte) 1);
        postEntity.setStatus((byte) 0);
        postRepository.save(postEntity);
        return "";
    }

    @Override
    public String likePost(LikeRequestDTO likeRequestDTO) {
        if (userRepository.findById(likeRequestDTO.getUserId()) == null) {
            throw new DuplicateUsernameException("user_is_not_exist");
        }
        PostLikeEntity postLikeEntity = likeRequestConverter.toEntity(likeRequestDTO);
        if (postLikeRepository.findByPost_IdAndUser_Id(likeRequestDTO.getPostId(),
                likeRequestDTO.getUserId()) != null) {
            PostLikeEntity entity = postLikeRepository.findByPost_IdAndUser_Id(likeRequestDTO.getPostId(),
                    likeRequestDTO.getUserId());
            postLikeRepository.delete(entity);
        } else {
            postLikeRepository.save(postLikeEntity);
        }
        return "";
    }

    @Override
    public String commentPost(CommentSaveRequestDTO commentSaveRequestDTO) {
        if (userRepository.findById(commentSaveRequestDTO.getUserId()) == null) {
            throw new DuplicateUsernameException("user_is_not_exist");
        }
        if (postRepository.findById(commentSaveRequestDTO.getPostId()) == null) {
            throw new DuplicateUsernameException("post_is_not_exist");
        }
        if (postCommentRepository.findById(commentSaveRequestDTO.getParentCommentId()) == null) {
            throw new DuplicateUsernameException("comment_is_not_exist");
        }
        PostCommentEntity entity = commentSaveRequestConverter.toEntity(commentSaveRequestDTO);
        postCommentRepository.save(entity);
        return "";
    }

    @Override
    public String deleteComment(CommentDeleteRequestDTO commentDeleteRequestDTO) {
        PostCommentEntity entity = postCommentRepository.findByIdAndUser_IdAndPost_Id(
                commentDeleteRequestDTO.getCommentId(), commentDeleteRequestDTO.getUserId(),
                commentDeleteRequestDTO.getPostId());
        if (entity.getPostComments().size() > 0) {
            for (PostCommentEntity postCommentEntity : entity.getPostComments()) {
                postCommentRepository.delete(postCommentEntity);
            }
        }
        postCommentRepository.delete(entity);
        return "";
    }

    @Override
    public List<BaseDTO> findPostByName(PostFindRequestDTO postFindRequestDTO) {
        List<BaseDTO> dtos = new ArrayList<>();
        if (postFindRequestDTO.getType().equals(PostType.NORMAL.getName())) {
            List<NormalPostResponseDTO> normalPostResponseDTOs = normalPostResponeConverter
                    .toDTOGroup(normalPostRepository.findAllByContentContains(postFindRequestDTO.getName()));
            dtos.addAll(normalPostResponseDTOs);
        } else if (postFindRequestDTO.getType().equals(PostType.RECRUIMENT.getName())) {
            List<RecruitmentPostResponseDTO> recruitmentPostResponseDTOs = recruitmentPostResponeConverter
                    .toDTOGroup(recruitmentPostRepository.findAllByTitleContains(postFindRequestDTO.getName()));
            dtos.addAll(recruitmentPostResponseDTOs);
        } else if (postFindRequestDTO.getType().equals(PostType.SURVEY.getName())) {
            List<SurveyResponeDTO> surveyResponeDTOs = surveyResponeConverter
                    .toDTOGroup(surveyPostRepository.findAllByTitleContains(postFindRequestDTO.getName()));
            dtos.addAll(surveyResponeDTOs);
        }
        return dtos;
    }

}
