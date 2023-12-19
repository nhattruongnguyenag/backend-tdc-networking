package com.chatapp.dto.response.user;

import com.chatapp.dto.response.post.PostSearchResponseDTO;

import java.util.List;

public class UserDetailInGroupResponseDTO{
    UserInfoResponseDTO user;
    List<PostSearchResponseDTO> posts;
    Boolean isFollow;

    public UserInfoResponseDTO getUser() {
        return user;
    }

    public void setUser(UserInfoResponseDTO user) {
        this.user = user;
    }

    public List<PostSearchResponseDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostSearchResponseDTO> posts) {
        this.posts = posts;
    }

    public Boolean getIsFollow() {
        return this.isFollow;
    }

    public void setIsFollow(Boolean isFollow) {
        this.isFollow = isFollow;
    }

}
