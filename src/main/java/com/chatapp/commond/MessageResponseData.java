package com.chatapp.commond;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public class MessageResponseData {
    @JsonIgnore
    private HttpStatus status;
    @JsonProperty("status")
    private int statusCode;
    @JsonProperty("message")
    private String message;

    public MessageResponseData(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.statusCode = status.value();
    }
}
