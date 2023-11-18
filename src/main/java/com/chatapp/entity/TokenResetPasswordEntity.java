package com.chatapp.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "token_reset_password")
public class TokenResetPasswordEntity extends BaseEntity {
    @Column(name = "token", nullable = false , unique = true)
    private String token;

    @Column(name = "status", nullable = false)
    private Long status;


    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getStatus() {
        return this.status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}