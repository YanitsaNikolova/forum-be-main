package com.uni.project.forum.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;
    private Long postId;
    private String text;
    private Long userId;
    private String username;
    private Date created;
}
