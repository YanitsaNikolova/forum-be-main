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
public class TopicEntity extends BaseEntity {

    @Column(unique = true)
    private String title;

    private int views;

    @Column
    @OneToMany(mappedBy = "topic")
    private List<PostEntity> posts;

    @JoinColumn
    @ManyToOne
    private UserEntity user;
}
