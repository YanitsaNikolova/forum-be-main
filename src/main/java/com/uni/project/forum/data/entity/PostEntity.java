package com.uni.project.forum.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class PostEntity extends BaseEntity {

    @Column(unique = true)
    private String text;

    private String username;

    @JoinColumn
    @ManyToOne
    private TopicEntity topic;

    @JoinColumn
    @ManyToOne
    private UserEntity user;

    @Column
    @OneToMany(mappedBy = "post")
    private List<CommentEntity> comments;
}
