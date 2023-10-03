package com.chatapp.dto;


import lombok.Data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
public class BaseDTO {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private Date createdAt = new Date();
    @JsonIgnore
    private Date updatedAt = new Date();
}
