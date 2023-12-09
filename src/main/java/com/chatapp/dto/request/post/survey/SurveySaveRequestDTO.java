package com.chatapp.dto.request.post.survey;

import java.util.List;

import com.chatapp.dto.BaseDTO;

public class SurveySaveRequestDTO extends BaseDTO {
    private String type;
    private List<String> images;
    private Long userId;
    private Long groupId;
    private String title;
    private String description;
    private List<QuestionRequestDTO> questions;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionRequestDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionRequestDTO> questions) {
        this.questions = questions;
    }
}
