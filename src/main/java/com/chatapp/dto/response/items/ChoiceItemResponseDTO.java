package com.chatapp.dto.response.items;

public class ChoiceItemResponseDTO {
    private String content;
    private Integer votes;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
