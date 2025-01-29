package com.uni.project.forum.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;
    private String text;
    private String username;
    private Long topicId;
    private Date created;
    private List<CommentDto> comments;
}
