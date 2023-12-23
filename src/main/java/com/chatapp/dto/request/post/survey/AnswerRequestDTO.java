package com.chatapp.dto.request.post.survey;

import java.util.List;

public class AnswerRequestDTO {
    private Long question_id;
    private String content;
    private List<Long> choices_ids;

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Long> getChoices_ids() {
        return choices_ids;
    }

    public void setChoices_ids(List<Long> choices_ids) {
        this.choices_ids = choices_ids;
    }
}
