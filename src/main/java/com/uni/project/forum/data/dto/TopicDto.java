package com.uni.project.forum.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicDto {
    private Long id;
    private String title;
    private String username;
    private Long userId;
    private int numberOfPosts;
    private int views;
    private Date created;
    private Date modified;
}
