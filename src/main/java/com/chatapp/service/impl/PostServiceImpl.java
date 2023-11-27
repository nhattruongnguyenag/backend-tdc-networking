package com.chatapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.chatapp.converter.response.*;
import com.chatapp.converter.response.post.PostSearchResponseConverter;
import com.chatapp.dto.*;
import com.chatapp.dto.request.*;
import com.chatapp.dto.response.*;
import com.chatapp.dto.response.postSearch.PostSearchResponseDTO;
import com.chatapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.converter.request.CommentSaveRequestConverter;
import com.chatapp.converter.request.LikeRequestConverter;
import com.chatapp.converter.request.NormalPostUpdateOrSaveRequestConverter;
import com.chatapp.converter.request.NormalPostUpdateRequestConverter;
import com.chatapp.converter.request.PostLogAddRequestConverter;
import com.chatapp.converter.request.RecruitmentPostUpdateRequestConverter;
import com.chatapp.converter.request.RecruitmentPosyUpdateOrSaveRequestConverter;
import com.chatapp.converter.request.SurveySaveRequestConverter;
import com.chatapp.converter.request.SurveyUpdateRequestConverter;
import com.chatapp.converter.request.UserSavePostRequestConverter;
import com.chatapp.entity.FollowEntity;
import com.chatapp.entity.NormalPostEntity;
import com.chatapp.entity.PostApprovalLogEntity;
import com.chatapp.entity.PostCommentEntity;
import com.chatapp.entity.PostEntity;
import com.chatapp.entity.PostLikeEntity;
import com.chatapp.entity.QuestionEntity;
import com.chatapp.entity.RecruitmentPostEntity;
import com.chatapp.entity.ShortAnswerEntity;
import com.chatapp.entity.SurveyPostEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.entity.VoteAnswerEntity;
import com.chatapp.enums.PostType;
import com.chatapp.enums.QuestionType;
import com.chatapp.enums.Role;
import com.chatapp.exception.DuplicateUsernameException;
import com.chatapp.service.PostService;
import com.chatapp.util.TokenProvider;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostApprovalLogRepository postApprovalLogRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NormalPostRepository normalPostRepository;
    @Autowired
    private RecruitmentPostRepository recruitmentPostRepository;
    @Autowired
    private SurveyPostRepository surveyPostRepository;
    @Autowired
    private PostLikeRepository postLikeRepository;
    @Autowired
    private ShortAnswerRepository shortAnswerRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private VoteAnswerRepository voteAnswerRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private StudentInfoRepository studentInfoRepository;
    @Autowired
    private FacultyInfoRepository facultyInfoRepository;
    @Autowired
    private BusinessInfoRepository businessInfoRepository;
    @Autowired
    private JobProfileRepository jobProfileRepository;
    @Autowired
    private PostInfoResponseConverter postInfoResponeConverter;
    @Autowired
    private NormalPostResponseConverter normalPostResponeConverter;
    @Autowired
    private RecruitmentPostResponseConverter recruitmentPostResponeConverter;
    @Autowired
    private SurveyResponeConverter surveyResponeConverter;
    @Autowired
    private CommentResponseConverter commentResponseConverter;
    @Autowired
    private UserInfoResponseConverter userInfoResponseConverter;
    @Autowired
    private StudentInfoResponseConverter studentInfoResponseConverter;
    @Autowired
    private FacultyInfoResponseConverter facultyInfoResponseConverter;
    @Autowired
    private BusinessInfoResponseConverter businessInfoResponseConverter;
    @Autowired
    private SurveyResultResponseConverter surveyResultResponseConverter;
    @Autowired
    private SurveyReviewResponseConverter surveyReviewResponseConverter;
    @Autowired
    private PostRejectLogConverter postRejectLogConverter;
    @Autowired
    private NormalPostUpdateOrSaveRequestConverter normalPostUpdateOrSaveRequestConverter;
    @Autowired
    private NormalPostUpdateRequestConverter normalPostUpdateRequestConverter;
    @Autowired
    private RecruitmentPosyUpdateOrSaveRequestConverter recruitmentPosyUpdateOrSaveRequestConverter;
    @Autowired
    private RecruitmentPostUpdateRequestConverter recruitmentPostUpdateRequestConverter;
    @Autowired
    private PostLogAddRequestConverter postLogAddRequestConverter;
    @Autowired
    private SurveySaveRequestConverter surveySaveRequestConverter;
    @Autowired
    private SurveyUpdateRequestConverter surveyUpdateRequestConverter;
    @Autowired
    private LikeRequestConverter likeRequestConverter;
    @Autowired
    private CommentSaveRequestConverter commentSaveRequestConverter;
    @Autowired
    private UserSavePostRequestConverter userSavePostRequestConverter;
    @Autowired
    private CustomizedPostRepository customizedPostRepository;
    @Autowired
    private PostSearchResponseConverter postSearchResponseConverter;

    @Autowired
    TokenProvider tokenProvider;

    @Override
    public boolean updateSurvey(SurveyDTO surveyDTO) {
        PostEntity postEntity = postRepository.findOneById(surveyDTO.getPostId());
        SurveyPostEntity surveyPostEntity = postEntity.getSurveyPost();
        if (surveyDTO.getTitle() != null) {
            surveyPostEntity.setTitle(surveyDTO.getTitle());
        }

        if (surveyDTO.getDescription() != null) {
            surveyPostEntity.setDescription(surveyDTO.getDescription());
        }

        int questionIndex = 0;
        final int QUESTION_ENTITY_LENGTH = surveyDTO.getQuestions().size();

        while (questionIndex < surveyDTO.getQuestions().size()) {
            QuestionDTO questionDTO = surveyDTO.getQuestions().get(questionIndex);
            if (questionIndex < QUESTION_ENTITY_LENGTH) {
                QuestionEntity questionEntity = surveyPostEntity.getQuestions().get(questionIndex);

                if (questionDTO.getTitle() != null) {
                    questionEntity.setTitle(questionDTO.getTitle());
                }

                int choiceIndex = 0;
                final int CHOICE_ENTITY_LENGTH = questionEntity.getVoteAnswers().size();

                while (choiceIndex < questionDTO.getChoices().size()) {
                    ChoiceDTO choiceDTO = questionDTO.getChoices().get(choiceIndex);
                    if (choiceIndex < CHOICE_ENTITY_LENGTH) {
                        questionEntity.getVoteAnswers().get(choiceIndex).setContent(choiceDTO.getContent());
                    } else {
                        VoteAnswerEntity voteAnswerEntity = new VoteAnswerEntity();
                        voteAnswerEntity.setContent(choiceDTO.getContent());
                    }
                    choiceIndex++;
                }

                while (choiceIndex < questionEntity.getVoteAnswers().size()) {
                    questionEntity.getVoteAnswers().remove(choiceIndex);
                }
            } else {
                QuestionEntity questionEntity = new QuestionEntity();
                questionEntity.setTitle(questionDTO.getTitle());
                questionEntity.setType(questionDTO.getType());
                questionEntity.setRequired(questionDTO.getRequired());

                for (ChoiceDTO choiceDTO : questionDTO.getChoices()) {
                    VoteAnswerEntity voteAnswerEntity = new VoteAnswerEntity();
                    voteAnswerEntity.setContent(choiceDTO.getContent());
                    questionEntity.getVoteAnswers().add(voteAnswerEntity);
                }

                surveyPostEntity.getQuestions().add(questionEntity);
            }

            questionIndex++;
        }

        while (questionIndex < QUESTION_ENTITY_LENGTH) {
            surveyPostEntity.getQuestions().remove(questionIndex);
        }

        try {
           return postRepository.save(postEntity) != null
        } catch (Exception ex) {
            return false;
        }

        return false;
    }

    @Override
    public List<PostSearchResponseDTO> findPosts(PostSearchRequestDTO requestDTO) {
        List<PostEntity> postEntities = customizedPostRepository.findPosts(requestDTO);
        return postSearchResponseConverter.toDTOGroup(postEntities);
    }

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

    @Override
    public List<BaseDTO> findAllByUserId(Long id) {
        List<BaseDTO> dtos = new ArrayList<>();
        List<PostInfoResponseDTO> responseDTOs = postInfoResponeConverter
                .toDTOGroup(postRepository.findAllByUser_IdOrderByUpdatedAtDesc(id));
        for (int i = 0; i < responseDTOs.size(); i++) {
            BaseDTO dto = null;
            if (responseDTOs.get(i).getType().equals(PostType.NORMAL.getName())) {
                NormalPostResponseDTO normalPostResponseDTO = normalPostResponeConverter
                        .toDTO(normalPostRepository.findOneByPost_Id(responseDTOs.get(i).getId()));
                this.setUserDetailOfPost(normalPostResponseDTO);
                dto = normalPostResponseDTO;
            } else if (responseDTOs.get(i).getType().equals(PostType.RECRUIMENT.getName())) {
                RecruitmentPostResponseDTO recruitmentPostResponseDTO = recruitmentPostResponeConverter
                        .toDTO(recruitmentPostRepository.findOneByPost_Id(responseDTOs.get(i).getId()));
                this.setUserDetailOfPost(recruitmentPostResponseDTO);
                dto = recruitmentPostResponseDTO;
            } else if (responseDTOs.get(i).getType().equals(PostType.SURVEY.getName())) {
                SurveyResponeDTO surveyResponeDTO = surveyResponeConverter
                        .toDTO(surveyPostRepository.findOneByPost_Id(responseDTOs.get(i).getId()));
                this.setUserDetailOfPost(surveyResponeDTO);
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
        postEntity.setActive((byte) 0);
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
        postEntity.setActive((byte) 0);
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
        postEntity.setActive((byte) 0);
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
            for (NormalPostResponseDTO normalPostResponseDTO : normalPostResponseDTOs) {
                NormalPostEntity normalPostEntity = normalPostRepository
                        .findOneByPost_Id(normalPostResponseDTO.getId());
                normalPostResponseDTO.setIsSave(this
                        .checkUserLoginHadSavePost(postRepository.findOneById(normalPostEntity.getPost().getId()),
                                postFindRequestDTO.getUserLogin()));
            }
            dtos.addAll(normalPostResponseDTOs);
        } else if (postFindRequestDTO.getType().equals(PostType.RECRUIMENT.getName())) {
            List<RecruitmentPostResponseDTO> recruitmentPostResponseDTOs = recruitmentPostResponeConverter
                    .toDTOGroup(recruitmentPostRepository.findAllByTitleContains(postFindRequestDTO.getName()));
            for (RecruitmentPostResponseDTO recruitmentPostResponseDTO : recruitmentPostResponseDTOs) {
                RecruitmentPostEntity recruitmentPostEntity = recruitmentPostRepository
                        .findOneByPost_Id(recruitmentPostResponseDTO.getId());
                Long isApplyJob = this.checkUserLoginHadApplied(
                        recruitmentPostRepository.findOneByPost_Id(recruitmentPostEntity.getPost().getId()),
                        postFindRequestDTO.getUserLogin());
                recruitmentPostResponseDTO.setIsApplyJob(isApplyJob);
                recruitmentPostResponseDTO.setIsSave(this
                        .checkUserLoginHadSavePost(postRepository.findOneById(recruitmentPostEntity.getPost().getId()),
                                postFindRequestDTO.getUserLogin()));
            }
            dtos.addAll(recruitmentPostResponseDTOs);
        } else if (postFindRequestDTO.getType().equals(PostType.SURVEY.getName())) {
            List<SurveyResponeDTO> surveyResponeDTOs = surveyResponeConverter
                    .toDTOGroup(surveyPostRepository.findAllByTitleContains(postFindRequestDTO.getName()));
            for (SurveyResponeDTO surveyResponeDTO : surveyResponeDTOs) {
                SurveyPostEntity surveyPostEntity = surveyPostRepository
                        .findOneByPost_Id(surveyResponeDTO.getId());
                Long isConducted = this.checkUserLoginHadConducted(
                        surveyPostRepository.findOneByPost_Id(surveyPostEntity.getPost().getId()),
                        postFindRequestDTO.getUserLogin());
                surveyResponeDTO.setIsConduct(isConducted);
                surveyResponeDTO.setIsSave(this
                        .checkUserLoginHadSavePost(postRepository.findOneById(surveyPostEntity.getPost().getId()),
                                postFindRequestDTO.getUserLogin()));
            }
            dtos.addAll(surveyResponeDTOs);
        }
        return dtos;
    }

    @Override
    public List<CommentResponeseDTO> findCommentByPostId(Long postId) {
        PostEntity postEntity = postRepository.findOneById(postId);
        List<CommentResponeseDTO> commentResponeseDTOs = commentResponseConverter.toDTOGroup(postEntity.getComments());
        return commentResponeseDTOs;
    }

    @Override
    public List<BaseDTO> findAllByRoleCode(String code) {
        List<BaseDTO> dtos = new ArrayList<>();
        List<PostInfoResponseDTO> responseDTOs = postInfoResponeConverter
                .toDTOGroup(postRepository.findAllByUser_Roles_CodeOrderByUpdatedAtDesc(code));
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

    @Override
    public SurveyResponeDTO getSurveyDetailByPostId(Long postId, Long userLogin) {
        PostEntity postEntity = postRepository.findOneById(postId);
        if (postEntity.getType().equals(PostType.SURVEY.getName())) {
            SurveyResponeDTO surveyResponeDTO = surveyResponeConverter
                    .toDTO(surveyPostRepository.findOneByPost_Id(postId));
            Long isConducted = this.checkUserLoginHadConducted(
                    surveyPostRepository.findOneByPost_Id(postId), userLogin);
            surveyResponeDTO.setIsConduct(isConducted);
            return surveyResponeDTO;
        } else {
            throw new RuntimeException("survey_at_this_post_id_not_exist");
        }
    }

    @Override
    public String answerSurvey(SurveyAnswerRequestDTO surveyAnswerRequestDTO) {
        UserEntity userEntity = userRepository.findOneById(surveyAnswerRequestDTO.getUser_id());
        List<VoteAnswerEntity> voteAnswers = new ArrayList<>();
        // check if user already conducted the survey
        if (userRepository.findOneById(surveyAnswerRequestDTO.getUser_id()).getVoteAnswers().size() != 0) {
            List<VoteAnswerEntity> voteAnswerEntities = userRepository.findOneById(surveyAnswerRequestDTO.getUser_id())
                    .getVoteAnswers();
            for (VoteAnswerEntity voteAnswerEntity : voteAnswerEntities) {
                Integer newCount = voteAnswerEntity.getCountVote() - 1;
                voteAnswerEntity.setCountVote(newCount);
                voteAnswerRepository.save(voteAnswerEntity);
            }
        }
        for (AnswerRequestDTO answerRequestDTO : surveyAnswerRequestDTO.getAnswers()) {
            QuestionEntity questionEntity = questionRepository.findOneById(answerRequestDTO.getQuestion_id());
            if (questionEntity.getType().equalsIgnoreCase(QuestionType.SHORT.getName())) {
                if (shortAnswerRepository.findOneByUser_IdAndQuestion_Id(userEntity.getId(),
                        questionEntity.getId()) != null) {
                    ShortAnswerEntity shortAnswerEntity = shortAnswerRepository
                            .findOneByUser_IdAndQuestion_Id(userEntity.getId(), questionEntity.getId());
                    shortAnswerEntity.setContent(answerRequestDTO.getContent());
                    shortAnswerRepository.save(shortAnswerEntity);
                } else {
                    ShortAnswerEntity shortAnswerEntity = new ShortAnswerEntity();
                    shortAnswerEntity.setUser(userEntity);
                    shortAnswerEntity.setContent(answerRequestDTO.getContent());
                    shortAnswerEntity.setQuestion(questionEntity);
                    shortAnswerRepository.save(shortAnswerEntity);
                }

            } else {
                for (Long voteAnswerId : answerRequestDTO.getChoices_ids()) {
                    VoteAnswerEntity voteAnswerEntity = voteAnswerRepository.findOneById(voteAnswerId);
                    Integer newCount = voteAnswerEntity.getCountVote() + 1;
                    voteAnswerEntity.setCountVote(newCount);
                    voteAnswers.add(voteAnswerEntity);
                }
                userEntity.setVoteAnswers(voteAnswers);
                userRepository.save(userEntity);
            }
        }
        return "";
    }

    @Override
    public RecruitmentPostResponseDTO getRecruimentDetailByPostId(Long postId, Long userLogin) {
        PostEntity postEntity = postRepository.findOneById(postId);
        if (postEntity.getType().equals(PostType.RECRUIMENT.getName())) {
            RecruitmentPostResponseDTO recruitmentPostResponseDTO = recruitmentPostResponeConverter
                    .toDTO(recruitmentPostRepository.findOneByPost_Id(postId));
            Long isApplyJob = this.checkUserLoginHadApplied(
                    recruitmentPostRepository.findOneByPost_Id(postId), userLogin);
            recruitmentPostResponseDTO.setIsApplyJob(isApplyJob);
            return recruitmentPostResponseDTO;
        } else {
            throw new RuntimeException("recruitment_at_this_post_id_not_exist");
        }
    }

    @Override
    public List<BaseDTO> findAllByGroupCode(String groupCode, Long userLogin) {
        if (groupRepository.findOneByCode(groupCode) == null) {
            throw new DuplicateUsernameException("group_does_not_exist");
        }
        List<PostInfoResponseDTO> responseDTOs = postInfoResponeConverter
                .toDTOGroup(postRepository
                        .findAllByGroup_IdOrderByUpdatedAtDesc(groupRepository.findOneByCode(groupCode).getId()));
        List<BaseDTO> dtos = toCustomListPost(responseDTOs, userLogin);
        return dtos;
    }

    @Override
    public NormalPostResponseDTO getNormalDetailByPostId(Long postId) {
        PostEntity postEntity = postRepository.findOneById(postId);
        if (postEntity.getType().equals(PostType.NORMAL.getName())) {
            NormalPostResponseDTO normalPostResponseDTO = normalPostResponeConverter
                    .toDTO(normalPostRepository.findOneByPost_Id(postId));
            return normalPostResponseDTO;
        } else {
            throw new RuntimeException("normal_post_at_this_post_id_not_exist");
        }
    }

    @Override
    public List<BaseDTO> getAllPostByUserIdAndType(Long userId, String type) {

        List<BaseDTO> dtos = new ArrayList<BaseDTO>();
        if (type.equals(PostType.NORMAL.getName())) {
            List<PostEntity> posts = postRepository.findAllByUser_IdAndTypeOrderByUpdatedAtDesc(userId, type);
            for (PostEntity post : posts) {
                NormalPostResponseDTO normalPostResponseDTO = normalPostResponeConverter
                        .toDTO(normalPostRepository.findOneByPost_Id(post.getId()));
                setUserDetailOfPost(normalPostResponseDTO);
                dtos.add(normalPostResponseDTO);
            }
            return dtos;
        } else if (type.equals(PostType.RECRUIMENT.getName())) {
            List<PostEntity> posts = postRepository.findAllByUser_IdAndTypeOrderByUpdatedAtDesc(userId, type);
            for (PostEntity post : posts) {
                RecruitmentPostResponseDTO recruitmentPostResponseDTO = recruitmentPostResponeConverter
                        .toDTO(recruitmentPostRepository.findOneByPost_Id(post.getId()));
                setUserDetailOfPost(recruitmentPostResponseDTO);
                dtos.add(recruitmentPostResponseDTO);
            }
            return dtos;
        } else if (type.equals(PostType.SURVEY.getName())) {
            List<PostEntity> posts = postRepository.findAllByUser_IdAndTypeOrderByUpdatedAtDesc(userId, type);
            for (PostEntity post : posts) {
                SurveyResponeDTO surveyResponeDTO = surveyResponeConverter
                        .toDTO(surveyPostRepository.findOneByPost_Id(post.getId()));
                setUserDetailOfPost(surveyResponeDTO);
                dtos.add(surveyResponeDTO);
            }
            return dtos;
        } else if (type.equals("null")) {
            dtos = this.findAllByUserId(userId);
            return dtos;
        } else {
            throw new RuntimeException("can_not_get_list_" + type);
        }
    }

    private PostInfoResponseDTO setUserDetailOfPost(PostInfoResponseDTO dto) {
        UserInfoResponseDTO userInfoResponseDTO = userInfoResponseConverter
                .toDTO(userRepository.findOneById(dto.getUser().getId()));
        if (userInfoResponseDTO.getRoleCodes().equals(Role.STUDENT.getName())) {
            StudentInfoResponseDTO studentInfoResponseDTO = studentInfoResponseConverter
                    .toDTO(studentInfoRepository.findOneByUser_Id(dto.getUser().getId()));
            dto.setUser(studentInfoResponseDTO);
        } else if (userInfoResponseDTO.getRoleCodes().equals(Role.BUSINESS.getName())) {
            BusinessInfoResponseDTO businessInfoResponseDTO = businessInfoResponseConverter
                    .toDTO(businessInfoRepository.findOneByUser_Id(dto.getUser().getId()));
            dto.setUser(businessInfoResponseDTO);
        } else if (userInfoResponseDTO.getRoleCodes().equals(Role.FACULTY.getName())) {
            FacultyInfoResponseDTO facultyInfoResponseDTO = facultyInfoResponseConverter
                    .toDTO(facultyInfoRepository.findOneByUser_Id(dto.getUser().getId()));
            dto.setUser(facultyInfoResponseDTO);
        }
        return dto;
    }

    @Override
    public String delete(Long postid) {
        if (postRepository.findOneById(postid) == null) {
            throw new DuplicateUsernameException("post_does_not_exist");
        }
        PostEntity postEntity = postRepository.findOneById(postid);
        postRepository.delete(postEntity);
        return "";
    }

    @Override
    public List<BaseDTO> getAllPostByUserIdAndGroupCode(
            AllPostByUserAndGroupResponseDTO allPostByUserAndGroupResponseDTO) {
        List<PostInfoResponseDTO> responseDTOs = postInfoResponeConverter
                .toDTOGroup(postRepository.findAllByUser_IdAndGroup_CodeOrderByUpdatedAtDesc(
                        allPostByUserAndGroupResponseDTO.getUserId(), allPostByUserAndGroupResponseDTO.getCode()));
        List<BaseDTO> dtos = toCustomListPost(responseDTOs, allPostByUserAndGroupResponseDTO.getUserLogin());
        return dtos;
    }

    @Override
    public String userSavePost(UserSavePostRequestDTO userSavePostRequestDTO) {
        if (userRepository.findOneById(userSavePostRequestDTO.getUserId()) == null) {
            throw new DuplicateUsernameException("user_does_not_exist");
        }
        UserEntity userEntity = userSavePostRequestConverter.toEntity(userSavePostRequestDTO);
        userRepository.save(userEntity);
        return "";
    }

    @Override
    public List<BaseDTO> getPostSaveByUserId(Long userId) {
        if (userRepository.findOneById(userId) == null) {
            throw new DuplicateUsernameException("user_does_not_exist");
        }
        List<PostInfoResponseDTO> responseDTOs = postInfoResponeConverter
                .toDTOGroup(userRepository.findOneById(userId).getPostSave());
        List<BaseDTO> dtos = toCustomListPost(responseDTOs, userId);
        return dtos;
    }

    private Long checkUserLoginHadConducted(SurveyPostEntity surveyPostEntity, Long userLogin) {
        Long isConducted = Long.valueOf(0);
        for (QuestionEntity questionEntity : surveyPostEntity.getQuestions()) {
            if (questionEntity.getType().equals(QuestionType.SHORT.getName())) {
                if (shortAnswerRepository.findOneByUser_Id(userLogin) != null) {
                    isConducted = Long.valueOf(1);
                    return isConducted;
                }
            } else {
                List<VoteAnswerEntity> voteAnswerEntities = voteAnswerRepository
                        .findAllByQuestion_Id(questionEntity.getId());
                for (VoteAnswerEntity voteAnswerEntity : voteAnswerEntities) {
                    if (userRepository.findOneById(userLogin).getVoteAnswers().contains(voteAnswerEntity)) {
                        isConducted = Long.valueOf(1);
                        return isConducted;
                    }
                }
            }
        }
        return isConducted;
    }

    private Long checkUserLoginHadApplied(RecruitmentPostEntity recruitmentEntity, Long userLogin) {
        Long isApplied = Long.valueOf(0);
        if (jobProfileRepository.findOneByPost_IdAndUser_Id(recruitmentEntity.getPost().getId(), userLogin) != null) {
            isApplied = Long.valueOf(1);
        }
        return isApplied;
    }

    private Long checkUserLoginHadSavePost(PostEntity postEntity, Long userLogin) {
        Long isSave = Long.valueOf(0);
        if (userRepository.findOneById(userLogin).getPostSave().contains(postEntity)) {
            isSave = Long.valueOf(1);
        }
        return isSave;
    }

    private UserInfoResponseDTO setUserDetail(UserInfoResponseDTO userInfoResponseDTO) {
        if (userInfoResponseDTO.getRoleCodes().equals(Role.STUDENT.getName())) {
            StudentInfoResponseDTO studentInfoResponseDTO = studentInfoResponseConverter
                    .toDTO(studentInfoRepository.findOneByUser_Id(userInfoResponseDTO.getId()));
            return studentInfoResponseDTO;
        } else if (userInfoResponseDTO.getRoleCodes().equals(Role.BUSINESS.getName())) {
            BusinessInfoResponseDTO businessInfoResponseDTO = businessInfoResponseConverter
                    .toDTO(businessInfoRepository.findOneByUser_Id(userInfoResponseDTO.getId()));
            return businessInfoResponseDTO;
        } else if (userInfoResponseDTO.getRoleCodes().equals(Role.FACULTY.getName())) {
            FacultyInfoResponseDTO facultyInfoResponseDTO = facultyInfoResponseConverter
                    .toDTO(facultyInfoRepository.findOneByUser_Id(userInfoResponseDTO.getId()));
            return facultyInfoResponseDTO;
        } else {
            return null;
        }
    }

    private List<BaseDTO> toCustomListPost(List<PostInfoResponseDTO> responseDTOs, Long userLogin) {
        List<BaseDTO> dtos = new ArrayList<BaseDTO>();
        for (int i = 0; i < responseDTOs.size(); i++) {
            BaseDTO dto = null;
            if (responseDTOs.get(i).getType().equals(PostType.NORMAL.getName())) {
                NormalPostResponseDTO normalPostResponseDTO = normalPostResponeConverter
                        .toDTO(normalPostRepository.findOneByPost_Id(responseDTOs.get(i).getId()));
                normalPostResponseDTO.setIsSave(this
                        .checkUserLoginHadSavePost(postRepository.findOneById(responseDTOs.get(i).getId()), userLogin));
                dto = normalPostResponseDTO;
            } else if (responseDTOs.get(i).getType().equals(PostType.RECRUIMENT.getName())) {
                RecruitmentPostResponseDTO recruitmentPostResponseDTO = recruitmentPostResponeConverter
                        .toDTO(recruitmentPostRepository.findOneByPost_Id(responseDTOs.get(i).getId()));
                Long isApplyJob = this.checkUserLoginHadApplied(
                        recruitmentPostRepository.findOneByPost_Id(responseDTOs.get(i).getId()), userLogin);
                recruitmentPostResponseDTO.setIsApplyJob(isApplyJob);
                recruitmentPostResponseDTO.setIsSave(this
                        .checkUserLoginHadSavePost(postRepository.findOneById(responseDTOs.get(i).getId()), userLogin));
                dto = recruitmentPostResponseDTO;
            } else if (responseDTOs.get(i).getType().equals(PostType.SURVEY.getName())) {
                SurveyResponeDTO surveyResponeDTO = surveyResponeConverter
                        .toDTO(surveyPostRepository.findOneByPost_Id(responseDTOs.get(i).getId()));
                Long isConducted = this.checkUserLoginHadConducted(
                        surveyPostRepository.findOneByPost_Id(responseDTOs.get(i).getId()), userLogin);
                surveyResponeDTO.setIsConduct(isConducted);
                surveyResponeDTO.setIsSave(this
                        .checkUserLoginHadSavePost(postRepository.findOneById(responseDTOs.get(i).getId()), userLogin));
                dto = surveyResponeDTO;
            }
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<SurveyResultResponseDTO> getSurveyResultByPostId(Long postId) {
        PostEntity postEntity = postRepository.findOneById(postId);
        SurveyPostEntity surveyPostEntity = surveyPostRepository.findOneByPost_Id(postId);
        if (postEntity.getType().equals(PostType.SURVEY.getName())) {
            return surveyResultResponseConverter
                    .toDTOGroup(questionRepository.findAllBySurvey_Id(surveyPostEntity.getId()));
        } else {
            throw new RuntimeException("survey_at_this_post_id_not_exist");
        }
    }

    @Override
    public UserDetailInGroupResponseDTO getUserPageInGroup(UserDetailInGroupRequestDTO userDetailInGroupRequestDTO) {
        AllPostByUserAndGroupResponseDTO allPostByUserAndGroupResponseDTO = new AllPostByUserAndGroupResponseDTO();
        allPostByUserAndGroupResponseDTO.setUserId(userDetailInGroupRequestDTO.getUserId());
        allPostByUserAndGroupResponseDTO.setCode(userDetailInGroupRequestDTO.getGroupCode());
        allPostByUserAndGroupResponseDTO.setUserLogin(userDetailInGroupRequestDTO.getUserLogin());

        UserDetailInGroupResponseDTO userDetailInGroupResponseDTO = new UserDetailInGroupResponseDTO();

        UserInfoResponseDTO userInfoResponseDTO = userInfoResponseConverter
                .toDTO(userRepository.findOneById(userDetailInGroupRequestDTO.getUserId()));
        userInfoResponseDTO = this.setUserDetail(userInfoResponseDTO);

        // set isFollow
        UserEntity userLogin = userRepository.findOneById(userDetailInGroupRequestDTO.getUserLogin());
        UserEntity userInPage = userRepository.findOneById(userDetailInGroupRequestDTO.getUserId());
        userDetailInGroupResponseDTO.setIsFollow(false);
        for (FollowEntity entity : userLogin.getFollowUsers()) {
            if (entity.getUserFollow().getId() == userInPage.getId()) {
                userDetailInGroupResponseDTO.setIsFollow(true);
                break;
            }
        }

        userDetailInGroupResponseDTO.setUser(userInfoResponseDTO);
        List<BaseDTO> posts = this.getAllPostByUserIdAndGroupCode(allPostByUserAndGroupResponseDTO);

        userDetailInGroupResponseDTO.setPosts(posts);
        return userDetailInGroupResponseDTO;
    }

    @Override
    public List<SurveyPreviewResponseDTO> reviewSurveyResultByPostIdAndUserId(Long postId, Long userId) {
        // if(postRepository.findOneByIdAndUser_Id(postId , userId) == null){
        // throw new RuntimeException("survey_at_this_post_id_not_exist");
        // }
        PostEntity postEntity = postRepository.findOneById(postId);
        SurveyPostEntity surveyPostEntity = surveyPostRepository.findOneByPost_Id(postEntity.getId());
        if (postEntity.getType().equals(PostType.SURVEY.getName())) {
            return surveyReviewResponseConverter
                    .toSurveyReviewDTOs(questionRepository.findAllBySurvey_Id(surveyPostEntity.getId()), userId);
        } else {
            throw new RuntimeException("survey_at_this_post_id_not_exist");
        }
    }

    @Override
    public String updateNormalPost(NormalPostUpdateRequestDTO normalPostUpdateRequestDTO) {
        if (postRepository.findOneById(normalPostUpdateRequestDTO.getPostId()) == null) {
            throw new RuntimeException("this_post_not_exist");
        }
        if (normalPostRepository.findOneByPost_Id(normalPostUpdateRequestDTO.getPostId()) == null) {
            throw new RuntimeException("normal_post_at_this_post_id_not_exist");
        }
        NormalPostEntity normalPostEntity = normalPostUpdateRequestConverter.toEntity(normalPostUpdateRequestDTO);
        normalPostRepository.save(normalPostEntity);
        return "";
    }

    @Override
    public String updateRecruitmentPost(RecruitmentPostUpdateRequestDTO recruitmentPostUpdateRequestDTO) {
        if (postRepository.findOneById(recruitmentPostUpdateRequestDTO.getId()) == null) {
            throw new RuntimeException("this_post_not_exist");
        }
        if (recruitmentPostRepository.findOneByPost_Id(recruitmentPostUpdateRequestDTO.getId()) == null) {
            throw new RuntimeException("recruitment_post_at_this_post_id_not_exist");
        }
        RecruitmentPostEntity entity = recruitmentPostUpdateRequestConverter.toEntity(recruitmentPostUpdateRequestDTO);
        recruitmentPostRepository.save(entity);
        return "";
    }

    @Override
    public String addPostLog(PostLogRequestDTO postLogRequestDTO) {
        if (postApprovalLogRepository.findOneByPost_Id(postLogRequestDTO.getPostId()) != null) {
            throw new DuplicateUsernameException("this_post_is");
        }
        PostApprovalLogEntity entity = postLogAddRequestConverter.toEntity(postLogRequestDTO);
        postApprovalLogRepository.save(entity);
        return "";
    }

    @Override
    public String deletePostLog(Long postId) {
        if (postApprovalLogRepository.findOneByPost_Id(postId) == null) {
            throw new DuplicateUsernameException("this_post_approval_log_is_not_exists");
        }
        PostApprovalLogEntity entity = postApprovalLogRepository.findOneByPost_Id(postId);
        postApprovalLogRepository.delete(entity);
        return "";
    }

    @Override
    public String acceptPost(Long postId) {
        if (postRepository.findOneById(postId) == null) {
            throw new DuplicateUsernameException("this_post_is_not_exists");
        }
        PostEntity entity = postRepository.findOneById(postId);
        entity.setActive((byte) 1);
        postRepository.save(entity);
        return "";
    }

    @Override
    public String updateSurvey(SurveyUpdateRequestDTO surveyUpdateRequestDTO) {
        PostEntity postEntity = surveyUpdateRequestConverter.toEntity(surveyUpdateRequestDTO);
        postRepository.save(postEntity);
        return "";
    }

    @Override
    public PostRejectLogDTO findRejectLogByPostId(Long postId) {
        try {
            return postRejectLogConverter.toDTO(postApprovalLogRepository.findOneByPost_Id(postId));
        } catch (Exception e) {
            return null;
        }
    }
}
