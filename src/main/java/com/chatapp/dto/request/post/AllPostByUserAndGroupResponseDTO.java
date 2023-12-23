package com.chatapp.dto.request.post;

public class AllPostByUserAndGroupResponseDTO {
    Long userLogin;
    Integer userId;
    String code;

    public Long getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(Long userLogin) {
        this.userLogin = userLogin;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
