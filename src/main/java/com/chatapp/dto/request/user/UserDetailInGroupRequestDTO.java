package com.chatapp.dto.request.user;

public class UserDetailInGroupRequestDTO {
    private Integer userId;
    private String groupCode;
    private Long userLogin;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Long getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(Long userLogin) {
        this.userLogin = userLogin;
    }
}
