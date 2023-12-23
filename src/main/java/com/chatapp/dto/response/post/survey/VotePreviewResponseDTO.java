package com.chatapp.dto.response.post.survey;

import java.util.List;

public class VotePreviewResponseDTO extends SurveyPreviewResponseDTO{
    private List<Long> choices_ids;

    public List<Long> getChoices_ids() {
        return choices_ids;
    }

    public void setChoices_ids(List<Long> choices_ids) {
        this.choices_ids = choices_ids;
    }
}
