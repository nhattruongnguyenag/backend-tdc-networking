package com.chatapp.dto.response.user.follow;

public class UserFollowResponseDTO {
    private Long id;
    private String name;
    private String image;
    private Boolean isFollow;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getFollowing() {
        return isFollow;
    }

    public void setFollowing(Boolean following) {
        isFollow = following;
    }
}
