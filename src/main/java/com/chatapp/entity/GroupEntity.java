package com.chatapp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "_groups")
public class GroupEntity extends BaseEntity {

    @Column(name = "active", nullable = false)
    private Byte active;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false , unique = true)
    private String code;

    @Column(name = "background", nullable = true)
    private String background;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PostEntity> posts = new ArrayList<>();

    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private List<UserEntity> users = new ArrayList<>();

    public Byte getActive() {
        return this.active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostEntity> getPosts() {
        return this.posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }

    public List<UserEntity> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBackground() {
        return this.background;
    }

    public void setBackground(String background) {
        this.background = background;
    }


}