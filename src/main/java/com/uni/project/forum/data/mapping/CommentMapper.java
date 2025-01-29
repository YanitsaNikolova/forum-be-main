package com.uni.project.forum.data.mapping;

import com.uni.project.forum.config.GlobalMapperConfig;
import com.uni.project.forum.data.dto.CommentDto;
import com.uni.project.forum.data.entity.CommentEntity;
import com.uni.project.forum.data.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class)
public interface CommentMapper {
    @Mapping(target = "post", source = "postEntity")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "text", source = "dto.text")
    CommentEntity toEntity(CommentDto dto, PostEntity postEntity);


    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "postId", source = "post.id")
    CommentDto toDto(CommentEntity entity);
}
