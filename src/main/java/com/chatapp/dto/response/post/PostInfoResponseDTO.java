package com.chatapp.dto.response.post;

import com.chatapp.dto.BaseDTO;
import com.chatapp.dto.response.group.GroupResponseDTO;
import com.chatapp.dto.response.image.ImageResponseDTO;
import com.chatapp.dto.response.post.comment.CommentResponeseDTO;
import com.chatapp.dto.response.user.UserInfoResponseDTO;
import com.chatapp.dto.response.user.like.UserLikeResponeDTO;

import java.util.List;

public class PostInfoResponseDTO extends BaseDTO {
    private Byte status;
    private Byte active;
    private String type;
    private UserInfoResponseDTO user;
    private GroupResponseDTO group;
    private List<UserLikeResponeDTO> likes;
    private List<ImageResponseDTO> images;
    private List<CommentResponeseDTO> comment;
    private Long commentQuantity;
    private Long isSave;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserInfoResponseDTO getUser() {
        return user;
    }

    public void setUser(UserInfoResponseDTO user) {
        this.user = user;
    }

    public GroupResponseDTO getGroup() {
        return group;
    }

    public void setGroup(GroupResponseDTO group) {
        this.group = group;
    }

    public List<UserLikeResponeDTO> getLikes() {
        return likes;
    }

    public void setLikes(List<UserLikeResponeDTO> likes) {
        this.likes = likes;
    }

    public List<ImageResponseDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageResponseDTO> images) {
        this.images = images;
    }

    public List<CommentResponeseDTO> getComment() {
        return comment;
    }

    public void setComment(List<CommentResponeseDTO> comment) {
        this.comment = comment;
    }

    public Long getCommentQuantity() {
        return commentQuantity;
    }

    public void setCommentQuantity(Long commentQuantity) {
        this.commentQuantity = commentQuantity;
    }

    public Long getIsSave() {
        return isSave;
    }

    public void setIsSave(Long isSave) {
        this.isSave = isSave;
    }
}
