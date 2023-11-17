package com.chatapp.constant;

public class SystemConstant {
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "http://localhost:3000";
    public static final String FILE_PATH_ORIGIN = "D:/uploading-file/";
    public static final String BACK_END_MAIN_URL = "http://localhost:3000/thay-doi-mat-khau/";
    public static final String EMAIL_TDC_SOCIAL_NETWORK = "sonthu3333@gmail.com";
    public static final String EMAIL_TEXT(String url) {
        return "<p>Hello,</p>"
        + "<p>You have requested to reset your password.</p>"
        + "<p>Click the link below to change your password:</p>"
        + "<p><a href=\"" + url + "\">Change my password</a></p>"
        + "<br>"
        + "<p>Ignore this email if you do remember your password, "
        + "or you have not made the request.</p>";
    }
}
