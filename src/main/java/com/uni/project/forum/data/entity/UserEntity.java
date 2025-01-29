package com.uni.project.forum.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserEntity extends BaseEntity {

    @Column(unique = true)
    private String username;
    private String email;
    private String password;
    private String name;
    private String role;

//    @Column
//    @OneToMany(mappedBy = "user")
//    private Set<ReplyEntity> replies;
//
//    @Column
//    @OneToMany(mappedBy = "user")
//    private Set<TopicEntity> topics;
}
