package com.uni.project.forum.data.mapping;

import com.uni.project.forum.config.GlobalMapperConfig;
import com.uni.project.forum.data.dto.PostDto;
import com.uni.project.forum.data.entity.PostEntity;
import com.uni.project.forum.data.entity.TopicEntity;
import com.uni.project.forum.data.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = GlobalMapperConfig.class, uses = CommentMapper.class)
public interface PostMapper {

    PostEntity toEntity(PostDto dto);

    @Mapping(source = "topic.id", target = "topicId")
    PostDto toDto(PostEntity entity);

    @Mapping(target = "topic", source = "topicEntity")
    @Mapping(target = "user", source = "userEntity")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", ignore = true)
    @Mapping(target = "username", source = "dto.username")
    PostEntity toEntity(PostDto dto, TopicEntity topicEntity, UserEntity userEntity);

    List<PostDto> buildListOfPostDto(List<PostEntity> allByTopic);

    @Mapping(target = "user", source = "userEntity")
    @Mapping(target = "topic", source = "topicEntity")
    @Mapping(target = "id", source = "postDto.id")
    @Mapping(target = "username", source = "postDto.username")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", ignore = true)
    void updateEntity(@MappingTarget PostEntity postEntity, PostDto postDto, UserEntity userEntity, TopicEntity topicEntity);
}
