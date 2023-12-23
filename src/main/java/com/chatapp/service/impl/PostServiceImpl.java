package com.chatapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.chatapp.converter.request.post.comment.*;
import com.chatapp.converter.request.post.log.*;
import com.chatapp.converter.request.post.normal.*;
import com.chatapp.converter.request.post.recruitment.*;
import com.chatapp.converter.request.post.survey.*;
import com.chatapp.converter.request.user.like.*;
import com.chatapp.converter.request.user.post_save.*;
import com.chatapp.converter.response.post.PostInfoResponseConverter;
import com.chatapp.converter.response.post.PostSearchResponseConverter;
import com.chatapp.converter.response.post.comment.CommentResponseConverter;
import com.chatapp.converter.response.post.log.PostRejectLogConverter;
import com.chatapp.converter.response.post.normal.NormalPostResponseConverter;
import com.chatapp.converter.response.post.recruitment.RecruitmentPostResponseConverter;
import com.chatapp.converter.response.post.survey.SurveyConverter;
import com.chatapp.converter.response.post.survey.SurveyResponeConverter;
import com.chatapp.converter.response.post.survey.SurveyResultResponseConverter;
import com.chatapp.converter.response.post.survey.SurveyReviewResponseConverter;
import com.chatapp.converter.response.user.UserInfoResponseConverter;
import com.chatapp.converter.response.user.business.BusinessInfoResponseConverter;
import com.chatapp.converter.response.user.faculty.FacultyInfoResponseConverter;
import com.chatapp.converter.response.user.student.StudentInfoResponseConverter;
import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.request.post.AllPostByUserAndGroupResponseDTO;
import com.chatapp.dto.request.post.PostFindRequestDTO;
import com.chatapp.dto.request.post.PostGetRequestDTO;
import com.chatapp.dto.request.post.PostSearchRequestDTO;
import com.chatapp.dto.request.post.comment.CommentDeleteRequestDTO;
import com.chatapp.dto.request.post.comment.CommentSaveRequestDTO;
import com.chatapp.dto.request.post.log.PostLogRequestDTO;
import com.chatapp.dto.request.post.normal.NormalPostUpdateOrSaveRequestDTO;
import com.chatapp.dto.request.post.normal.NormalPostUpdateRequestDTO;
import com.chatapp.dto.request.post.recruitment.RecruitmentPostUpdateOrSageRequestDTO;
import com.chatapp.dto.request.post.recruitment.RecruitmentPostUpdateRequestDTO;
import com.chatapp.dto.request.post.survey.AnswerRequestDTO;
import com.chatapp.dto.request.post.survey.SurveyAnswerRequestDTO;
import com.chatapp.dto.request.post.survey.SurveySaveRequestDTO;
import com.chatapp.dto.request.user.UserDetailInGroupRequestDTO;
import com.chatapp.dto.request.user.like.LikeRequestDTO;
import com.chatapp.dto.request.user.post_save.UserSavePostFindRequestDTO;
import com.chatapp.dto.request.user.post_save.UserSavePostRequestDTO;
import com.chatapp.dto.response.post.PostInfoResponseDTO;
import com.chatapp.dto.response.post.PostSearchResponseDTO;
import com.chatapp.dto.response.post.comment.CommentResponeseDTO;
import com.chatapp.dto.response.post.log.PostRejectLogDTO;
import com.chatapp.dto.response.post.normal.NormalPostResponseDTO;
import com.chatapp.dto.response.post.recruitment.RecruitmentPostResponseDTO;
import com.chatapp.dto.response.post.survey.*;
import com.chatapp.dto.response.user.UserDetailInGroupResponseDTO;
import com.chatapp.dto.response.user.UserInfoResponseDTO;
import com.chatapp.dto.response.user.business.BusinessInfoResponseDTO;
import com.chatapp.dto.response.user.faculty.FacultyInfoResponseDTO;
import com.chatapp.dto.response.user.student.StudentInfoResponseDTO;
import com.chatapp.entity.*;
import com.chatapp.enums.Notification;
import com.chatapp.enums.PostType;
import com.chatapp.enums.QuestionType;
import com.chatapp.enums.Role;
import com.chatapp.exception.DuplicateUsernameException;
import com.chatapp.repository.*;
import com.chatapp.service.NotificationService;
import com.chatapp.service.PostService;
import com.chatapp.util.TokenProvider;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private NotificationService notificationService;
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
    private SurveyConverter surveyConverter;
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

        postEntity.setActive((byte) 0);
        postEntity.setUpdatedAt(new Date());

        if (surveyDTO.getTitle() != null) {
            surveyPostEntity.setTitle(surveyDTO.getTitle());
        }

        if (surveyDTO.getDescription() != null) {
            surveyPostEntity.setDescription(surveyDTO.getDescription());
        }

        List<QuestionDTO> questionUpdateDTOs = surveyDTO.getQuestions().stream()
                .filter(question -> question.getId() != null).collect(Collectors.toList());
        List<QuestionDTO> questionAddNewDTOs = surveyDTO.getQuestions().stream()
                .filter(question -> question.getId() == null).collect(Collectors.toList());

        processUpdateOldQuestion(questionUpdateDTOs, surveyPostEntity);

        processDeleteRedundantQuestions(surveyPostEntity, questionUpdateDTOs);

        processAddNewQuestion(questionAddNewDTOs, surveyPostEntity);

        try {
            return postRepository.save(postEntity) != null;
        } catch (Exception ex) {
            return false;
        }
    }

    private static void processUpdateOldQuestion(List<QuestionDTO> questionUpdateDTOs,
            SurveyPostEntity surveyPostEntity) {
        int questionIndex = 0;
        final int QUESTION_ENTITY_LENGTH = surveyPostEntity.getQuestions().size();

        while (questionIndex < questionUpdateDTOs.size()) {
            QuestionDTO questionDTO = questionUpdateDTOs.get(questionIndex);
            if (questionIndex < QUESTION_ENTITY_LENGTH) {
                QuestionEntity questionEntity = surveyPostEntity.getQuestions().stream()
                        .filter(item -> item.getId().equals(questionDTO.getId()))
                        .findFirst().orElse(null);

                if (questionEntity != null) {
                    if (questionDTO.getTitle() != null) {
                        questionEntity.setId(questionDTO.getId());
                        questionEntity.setTitle(questionDTO.getTitle());
                    }

                    questionEntity.setRequired(questionDTO.getRequired());

                    int choiceIndex = 0;
                    final int CHOICE_ENTITY_LENGTH = questionEntity.getVoteAnswers().size();

                    List<ChoiceDTO> choiceUpdateDTOs = questionDTO.getChoices().stream()
                            .filter(choice -> choice.getId() != null).collect(Collectors.toList());
                    List<ChoiceDTO> choiceAddNewDTOs = questionDTO.getChoices().stream()
                            .filter(choice -> choice.getId() == null).collect(Collectors.toList());

                    while (choiceIndex < choiceUpdateDTOs.size()) {
                        ChoiceDTO choiceDTO = choiceUpdateDTOs.get(choiceIndex);
                        if (choiceIndex < CHOICE_ENTITY_LENGTH) {
                            VoteAnswerEntity choiceEntity = questionEntity.getVoteAnswers().stream()
                                    .filter(choice -> choice.getId().equals(choiceDTO.getId())).findFirst()
                                    .orElse(null);
                            if (choiceEntity != null) {
                                choiceEntity.setId(choiceDTO.getId());
                                choiceEntity.setContent(choiceDTO.getContent());
                            }
                        }
                        choiceIndex++;
                    }

                    choiceIndex = 0;
                    while (choiceIndex < questionEntity.getVoteAnswers().size()) {
                        VoteAnswerEntity choiceEntity = questionEntity.getVoteAnswers().get(choiceIndex);
                        ChoiceDTO choiceDTO = choiceUpdateDTOs.stream()
                                .filter(item -> item.getId().equals(choiceEntity.getId())).findFirst().orElse(null);
                        if (choiceDTO == null) {
                            questionEntity.getVoteAnswers().remove(choiceIndex);
                            choiceIndex--;
                        }
                        choiceIndex++;
                    }

                    for (ChoiceDTO choiceDTO : choiceAddNewDTOs) {
                        VoteAnswerEntity voteAnswerEntity = new VoteAnswerEntity();
                        voteAnswerEntity.setContent(choiceDTO.getContent());
                        voteAnswerEntity.setQuestion(questionEntity);
                        questionEntity.getVoteAnswers().add(voteAnswerEntity);
                    }
                }
            }
            questionIndex++;
        }
    }

    private static void processAddNewQuestion(List<QuestionDTO> questionAddNewDTOs, SurveyPostEntity surveyPostEntity) {
        for (QuestionDTO questionDTO : questionAddNewDTOs) {
            QuestionEntity questionEntity = new QuestionEntity();
            questionEntity.setTitle(questionDTO.getTitle());
            questionEntity.setType(questionDTO.getType());
            questionEntity.setRequired(questionDTO.getRequired());
            questionEntity.setSurvey(surveyPostEntity);

            for (ChoiceDTO choiceDTO : questionDTO.getChoices()) {
                VoteAnswerEntity voteAnswerEntity = new VoteAnswerEntity();
                voteAnswerEntity.setContent(choiceDTO.getContent());
                voteAnswerEntity.setQuestion(questionEntity);
                questionEntity.getVoteAnswers().add(voteAnswerEntity);
            }

            surveyPostEntity.getQuestions().add(questionEntity);
        }
    }

    private static void processDeleteRedundantQuestions(SurveyPostEntity surveyPostEntity,
            List<QuestionDTO> questionUpdateDTOs) {
        int questionIndex = 0;
        while (questionIndex < surveyPostEntity.getQuestions().size()) {
            QuestionEntity questionEntity = surveyPostEntity.getQuestions().get(questionIndex);
            QuestionDTO questionDTO = questionUpdateDTOs.stream()
                    .filter(item -> item.getId().equals(questionEntity.getId())).findFirst().orElse(null);
            if (questionDTO == null) {
                surveyPostEntity.getQuestions().remove(questionIndex);
                questionIndex--;
            }
            questionIndex++;
        }
    }

    @Override
    public List<PostSearchResponseDTO> findPosts(PostSearchRequestDTO requestDTO) {
        return toCustomListPost(requestDTO);
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
        if (postEntity.getUser().getFalcutyInfo() != null) {
            postEntity.setActive((byte) 1);
        } else {
            postEntity.setActive((byte) 0);
        }
        postEntity.setStatus((byte) 0);
        return postEntity;
    }

    @Override
    public String normalPostUpdateOrSave(
            NormalPostUpdateOrSaveRequestDTO normalPostUpdateOrSaveRequestDTO) {
        PostEntity postEntity;
        if (normalPostUpdateOrSaveRequestDTO.getId() != null) {
            postEntity = this.normalPostUpdate(normalPostUpdateOrSaveRequestDTO);
            postRepository.save(postEntity);
        } else {
            postEntity = this.normalPostSave(normalPostUpdateOrSaveRequestDTO);
            postRepository.save(postEntity);
            if (postEntity.getUser().getFalcutyInfo() != null && postEntity.getNormalPost() != null) {
                Long idFaculty = postEntity.getUser().getFalcutyInfo().getId();
                for (StudentInfoEntity studentInfoEntity : studentInfoRepository.findALLByFaculty_Id(idFaculty)) {
                    notificationService.addNotification(Notification.FACULTY_CREATE_NORMAL.getValue(),
                            Notification.FACULTY_CREATE_NORMAL.getValue(), studentInfoEntity.getUser().getId(),
                            "id:" + postEntity.getId(), postEntity.getUser().getId());
                }
            }
        }
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
        if (postEntity.getUser().getFalcutyInfo() != null) {
            postEntity.setActive((byte) 1);
        } else {
            postEntity.setActive((byte) 0);
        }
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
        if (postEntity.getUser().getFalcutyInfo() != null) {
            postEntity.setActive((byte) 1);
        } else {
            postEntity.setActive((byte) 0);
        }
        postEntity.setStatus((byte) 0);
        postRepository.save(postEntity);
        if (postEntity.getUser().getFalcutyInfo() != null && postEntity.getSurveyPost() != null) {
            Long idFaculty = postEntity.getUser().getFalcutyInfo().getId();
            for (StudentInfoEntity studentInfoEntity : studentInfoRepository.findALLByFaculty_Id(idFaculty)) {
                notificationService.addNotification(Notification.FACULTY_CREATE_SURVEY.getValue(),
                        Notification.FACULTY_CREATE_SURVEY.getValue(), studentInfoEntity.getUser().getId(),
                        "id:" + postEntity.getId(), postEntity.getUser().getId());
            }
        }
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
            if (likeRequestDTO.getUserId() != postLikeEntity.getPost().getUser().getId()) {
                notificationService.addNotification(Notification.USER_LIKE_POST.getValue(),
                        Notification.USER_LIKE_POST.getValue(), postLikeEntity.getPost().getUser().getId(),
                        "id:" + postLikeEntity.getPost().getId(), likeRequestDTO.getUserId());
            }
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
        PostCommentEntity entity = commentSaveRequestConverter.toEntity(commentSaveRequestDTO);
        if (commentSaveRequestDTO.getUserId() != entity.getPost().getUser().getId()) {
            if (commentSaveRequestDTO.getParentCommentId() == 0) {
                notificationService.addNotification(Notification.USER_COMMENT_POST.getValue(),
                        Notification.USER_COMMENT_POST.getValue(), entity.getPost().getUser().getId(),
                        "id:" + entity.getPost().getId(), commentSaveRequestDTO.getUserId());
            } else {
                notificationService.addNotification(Notification.USER_REPLY_COMMENT_POST.getValue(),
                        Notification.USER_REPLY_COMMENT_POST.getValue(), entity.getParentComment().getUser().getId(),
                        "id:" + entity.getPost().getId(), commentSaveRequestDTO.getUserId());
            }
        }
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
    public List<PostSearchResponseDTO> findPostByName(PostFindRequestDTO postFindRequestDTO) {
        PostSearchRequestDTO postSearchRequestDTO = new PostSearchRequestDTO();
        postSearchRequestDTO.setType(postFindRequestDTO.getType());
        postSearchRequestDTO.setSearch(postFindRequestDTO.getName());
        postSearchRequestDTO.setUserLogin(postFindRequestDTO.getUserLogin());
        return toCustomListPost(postSearchRequestDTO);
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
        if (postRepository.findOneById(surveyAnswerRequestDTO.getPost_id()) == null) {
            throw new RuntimeException("this_post_not_exist");
        }
        if (surveyPostRepository.findOneByPost_Id(surveyAnswerRequestDTO.getPost_id()) == null) {
            throw new RuntimeException("survey_post_at_this_post_id_not_exist");
        }
        UserEntity userEntity = userRepository.findOneById(surveyAnswerRequestDTO.getUser_id());
        List<VoteAnswerEntity> voteAnswers = userEntity.getVoteAnswers();

        // get all vote answers of survey post
        SurveyPostEntity surveyPostEntity = surveyPostRepository.findOneByPost_Id(surveyAnswerRequestDTO.getPost_id());
        List<QuestionEntity> questions = surveyPostEntity.getQuestions();
        List<Long> oldVoteAnswerIds = new ArrayList<Long>();
        for (QuestionEntity questionEntity : questions) {
            if (!questionEntity.getType().equalsIgnoreCase(QuestionType.SHORT.getName())) {
                for (VoteAnswerEntity voteAnswerEntity : questionEntity.getVoteAnswers()) {
                    oldVoteAnswerIds.add(voteAnswerEntity.getId());
                }
            }
        }

        // check if vote answer of user has anyone in all vote answers of survey post,
        // delete them
        for (Long id : oldVoteAnswerIds) {
            VoteAnswerEntity voteAnswerEntity = voteAnswerRepository.findOneById(id);
            if (voteAnswers.contains(voteAnswerEntity)) {
                voteAnswers.remove(voteAnswerEntity);
            }
        }

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
                    if (!voteAnswers.contains(voteAnswerEntity)) {
                        voteAnswers.add(voteAnswerEntity);
                    }
                }
                userEntity.setVoteAnswers(voteAnswers);
                userRepository.save(userEntity);
            }
        }
        notificationService.addNotification(Notification.USER_CONDUCT_SURVEY.getValue(),
                Notification.USER_CONDUCT_SURVEY.getValue(), surveyPostEntity.getPost().getUser().getId(),
                "id:" + surveyPostEntity.getPost().getId(), surveyAnswerRequestDTO.getUser_id());
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
    public List<PostSearchResponseDTO> findAllByGroupCode(String groupCode, Long userLogin) {
        if (groupRepository.findOneByCode(groupCode) == null) {
            throw new DuplicateUsernameException("group_does_not_exist");
        }
        PostSearchRequestDTO requestDTO = new PostSearchRequestDTO();
        requestDTO.setGroup(groupCode);
        requestDTO.setUserLogin(userLogin);
        return toCustomListPost(requestDTO);
    }

    private List<PostSearchResponseDTO> toCustomListPost(PostSearchRequestDTO requestDTO) {
        List<PostEntity> postEntities = customizedPostRepository.findPosts(requestDTO);
        for (PostEntity postEntity : postEntities) {
            postEntity.setUserLogin(requestDTO.getUserLogin());
        }
        return postSearchResponseConverter.toDTOGroup(postEntities);
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
    public List<PostSearchResponseDTO> getAllPostByUserIdAndGroupCode(
            AllPostByUserAndGroupResponseDTO allPostByUserAndGroupResponseDTO) {
        PostSearchRequestDTO postSearchRequestDTO = new PostSearchRequestDTO();
        postSearchRequestDTO.setGroup(allPostByUserAndGroupResponseDTO.getCode());
        postSearchRequestDTO.setUserId(allPostByUserAndGroupResponseDTO.getUserId());
        postSearchRequestDTO.setUserLogin(allPostByUserAndGroupResponseDTO.getUserLogin());
        return toCustomListPost(postSearchRequestDTO);
    }

    @Override
    public String userSavePost(UserSavePostRequestDTO userSavePostRequestDTO) {
        if (userRepository.findOneById(userSavePostRequestDTO.getUserId()) == null) {
            throw new DuplicateUsernameException("user_does_not_exist");
        }
        if (postRepository.findOneById(userSavePostRequestDTO.getPostId()) == null) {
            throw new DuplicateUsernameException("post_does_not_exist");
        }
        UserEntity userEntity = userSavePostRequestConverter.toEntity(userSavePostRequestDTO);
        userRepository.save(userEntity);
        return "";
    }

    @Override
    public List<PostSearchResponseDTO> getPostSaveByUserId(Long userId) {
        if (userRepository.findOneById(userId) == null) {
            throw new DuplicateUsernameException("user_does_not_exist");
        }
        List<PostEntity> postEntities = userRepository.findOneById(userId).getPostSave();
        for (PostEntity postEntity : postEntities) {
            postEntity.setUserLogin(userId);
        }
        List<PostSearchResponseDTO> dtos = postSearchResponseConverter.toDTOGroup(postEntities);
        return dtos;
    }

    private Long checkUserLoginHadConducted(SurveyPostEntity surveyPostEntity, Long userLogin) {
        Long isConducted = Long.valueOf(0);
        for (QuestionEntity questionEntity : surveyPostEntity.getQuestions()) {
            if (questionEntity.getType().equals(QuestionType.SHORT.getName())) {
                if (shortAnswerRepository.findOneByUser_IdAndQuestion_Id(userLogin, questionEntity.getId()) != null) {
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
        UserDetailInGroupResponseDTO userDetailInGroupResponseDTO = new UserDetailInGroupResponseDTO();

        UserInfoResponseDTO userInfoResponseDTO = userInfoResponseConverter
                .toDTO(userRepository.findOneById(userDetailInGroupRequestDTO.getUserId()));
        userInfoResponseDTO = this.setUserDetail(userInfoResponseDTO);

        // set isFollow
        UserEntity userLogin = userRepository.findOneById(userDetailInGroupRequestDTO.getUserLogin());
        UserEntity userInPage = userRepository.findOneById(userDetailInGroupRequestDTO.getUserId());
        userDetailInGroupResponseDTO.setIsFollow(false);
        if (Long.valueOf(userDetailInGroupRequestDTO.getUserId()) != userDetailInGroupRequestDTO.getUserLogin()) {
            for (FollowEntity entity : userLogin.getFollowUsers()) {
                if (entity.getUserFollow().getId() == userInPage.getId()) {
                    userDetailInGroupResponseDTO.setIsFollow(true);
                    break;
                }
            }
        }

        userDetailInGroupResponseDTO.setUser(userInfoResponseDTO);

        List<PostSearchResponseDTO> posts = null;

        if (userDetailInGroupRequestDTO.getGroupCode() != null) {
            AllPostByUserAndGroupResponseDTO allPostByUserAndGroupResponseDTO = new AllPostByUserAndGroupResponseDTO();
            allPostByUserAndGroupResponseDTO.setUserId(userDetailInGroupRequestDTO.getUserId());
            allPostByUserAndGroupResponseDTO.setCode(userDetailInGroupRequestDTO.getGroupCode());
            allPostByUserAndGroupResponseDTO.setUserLogin(userDetailInGroupRequestDTO.getUserLogin());
            posts = this.getAllPostByUserIdAndGroupCode(allPostByUserAndGroupResponseDTO);
        }

        userDetailInGroupResponseDTO.setPosts(posts);
        return userDetailInGroupResponseDTO;
    }

    @Override
    public List<SurveyPreviewResponseDTO> reviewSurveyResultByPostIdAndUserId(Long postId, Long userId) {
        if (postRepository.findOneByIdAndUser_Id(postId, userId) == null) {
            throw new RuntimeException("survey_at_this_post_id_not_exist");
        }
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
        PostApprovalLogEntity entity = postLogAddRequestConverter.toEntity(postLogRequestDTO);
        postApprovalLogRepository.save(entity);
        PostApprovalLogEntity entity1 = postApprovalLogRepository.findAll()
                .get(postApprovalLogRepository.findAll().size() - 1);
        notificationService.addNotification(Notification.POST_LOG.getValue(),
                Notification.POST_LOG.getValue(), entity.getPost().getUser().getId(),
                "logId:" + entity1.getId(), null);
        return "";
    }

    @Override
    public String deletePostLog(Long postId) {
        if (postApprovalLogRepository.findAllByPost_Id(postId) == null) {
            throw new DuplicateUsernameException("post_approvals_log_is_not_exists");
        }
        for (PostApprovalLogEntity postApprovalLogEntity : postApprovalLogRepository.findAllByPost_Id(postId)) {
            postApprovalLogRepository.delete(postApprovalLogEntity);
        }
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
        if (entity.getUser().getBusinessesInfos() != null && entity.getSurveyPost() != null) {
            for (StudentInfoEntity studentInfoEntity : studentInfoRepository.findAll()) {
                notificationService.addNotification(Notification.CREATE_SURVEY.getValue(),
                        Notification.CREATE_SURVEY.getValue(), studentInfoEntity.getUser().getId(),
                        "id:" + entity.getId(), entity.getUser().getId());
            }
        }
        if (entity.getUser().getBusinessesInfos() != null && entity.getRecruitmentPost() != null) {
            for (StudentInfoEntity studentInfoEntity : studentInfoRepository.findAll()) {
                notificationService.addNotification(Notification.CREATE_RECRUITMENT.getValue(),
                        Notification.CREATE_RECRUITMENT.getValue(), studentInfoEntity.getUser().getId(),
                        "id:" + entity.getId(), entity.getUser().getId());
            }
        }
        notificationService.addNotification(Notification.ACCEPT_POST.getValue(),
                Notification.ACCEPT_POST.getValue(), entity.getUser().getId(),
                "id:" + postId, null);
        return "";
    }

    @Override
    public PostRejectLogDTO findRejectLogByPostId(Long postId) {
        try {
            List<PostApprovalLogEntity> logEntities = postApprovalLogRepository.findAllByPost_Id(postId);
            return postRejectLogConverter.toDTO(logEntities.get(logEntities.size() - 1));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SurveyDTO getSurveyByPostId(Long postId) {
        SurveyDTO surveyDTO = surveyConverter.toDTO(surveyPostRepository.findOneByPost_Id(postId));
        return surveyDTO;
    }

    @Override
    public List<PostSearchResponseDTO> getPostSaveByUserIdAndSearch(
            UserSavePostFindRequestDTO userSavePostFindRequestDTO) {
        if (userRepository.findOneById(userSavePostFindRequestDTO.getUserId()) == null) {
            throw new DuplicateUsernameException("user_does_not_exist");
        }
        String lower = userSavePostFindRequestDTO.getSearch().toLowerCase();
        List<PostEntity> postEntities = userRepository.findOneById(userSavePostFindRequestDTO.getUserId())
                .getPostSave();
        List<PostEntity> result = new ArrayList<PostEntity>();
        for (PostEntity postEntity : postEntities) {
            postEntity.setUserLogin(userSavePostFindRequestDTO.getUserId());
            if (postEntity.getNormalPost() != null) {
                if (postEntity.getNormalPost().getContent().contains(lower)) {
                    result.add(postEntity);
                }
            }
            if (postEntity.getRecruitmentPost() != null) {
                if (postEntity.getRecruitmentPost().getTitle().contains(lower)) {
                    result.add(postEntity);
                }
            }
            if (postEntity.getSurveyPost() != null) {
                if (postEntity.getSurveyPost().getTitle().contains(lower)) {
                    result.add(postEntity);
                }
            }
        }
        List<PostSearchResponseDTO> dtos = postSearchResponseConverter.toDTOGroup(result);
        return dtos;
    }

    @Override
    public PostSearchResponseDTO findById(PostGetRequestDTO requestDTO, Long userLogin) {
        if (postRepository.findOneById(requestDTO.getPostId()) == null) {
            throw new DuplicateUsernameException("this_post_is_not_exists");
        }
        PostEntity postEntity = postRepository.findOneById(requestDTO.getPostId());
        postEntity.setUserLogin(userLogin);
        return postSearchResponseConverter.toDTO(postEntity);
    }
}
