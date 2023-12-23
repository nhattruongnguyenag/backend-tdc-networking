package com.chatapp.dto.request.post.survey;

import java.util.List;

public class SurveyAnswerRequestDTO {
    private Long user_id;
    private Long post_id;
    private List<AnswerRequestDTO> answers;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public List<AnswerRequestDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerRequestDTO> answers) {
        this.answers = answers;
    }
}
