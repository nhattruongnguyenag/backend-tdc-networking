package com.chatapp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topics")
public class TopicEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image", nullable = false)
    private String image;

    @ManyToMany(mappedBy = "topics", fetch = FetchType.LAZY)
    private List<PostEntity> posts = new ArrayList<>();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<PostEntity> getPosts() {
        return this.posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }


}