package com.chatapp.enums;

public enum Notification {
    REGISTER_SUCCESS("resgister_success"),
    CHANGE_PASSWORD_SUCCESS("change_password_success"),
    CREATE_SURVEY("create_survey"),
    CREATE_NORMAL("create_normal"),
    CREATE_RECRUITMENT("create_recruitment"),
    FACULTY_CREATE_SURVEY("faculty_create_survey"),
    FACULTY_CREATE_NORMAL("faculty_create_normal"),
    UPDATE_POST("update_post"),
    SAVE_POST("save_post"),
    USER_LIKE_POST("user_like_post"),
    USER_COMMENT_POST("user_comment_post"),
    USER_REPLY_COMMENT_POST("user_reply_comment"),
    USER_CONDUCT_SURVEY("user_conduct_survey"),
    POST_LOG("post_log"),
    ACCEPT_POST("accept_post"),
    USER_UPDATE("user_update"),
    USER_FOLLOW("user_follow"),
    USER_UPDATE_AVATAR("user_update_avatar"),
    USER_CHANGE_LANGUAGE("user_change_language"),
    USER_APPLY_JOB("user_apply_job"),
    USER_CREATE_POST_WATCH_APPLY_JOB("user_create_watch_job");

    private String value;

    Notification(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
