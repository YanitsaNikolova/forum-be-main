package com.uni.project.forum.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class CommentEntity extends BaseEntity {

    @Column(unique = true)
    private String text;

    @JoinColumn
    @ManyToOne
    private UserEntity user;

    @JoinColumn
    @ManyToOne
    private PostEntity post;
}
