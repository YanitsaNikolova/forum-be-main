package com.uni.project.forum.data.mapping;

import com.uni.project.forum.config.GlobalMapperConfig;
import com.uni.project.forum.data.dto.TopicDto;
import com.uni.project.forum.data.entity.PostEntity;
import com.uni.project.forum.data.entity.TopicEntity;
import com.uni.project.forum.data.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Mapper(config = GlobalMapperConfig.class)
public interface TopicMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", ignore = true)
    TopicEntity toEntity(TopicDto dto, UserEntity user);

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "numberOfPosts", source = "posts", qualifiedByName = "numberOfPosts")
    TopicDto toDto(TopicEntity topicEntity);

    @Named("numberOfPosts")
    default Integer numberOfPosts(List<PostEntity> posts) {
        if (CollectionUtils.isEmpty(posts)) return 0;
        return posts.size();
    }

    List<TopicDto> buildListOfTopics(List<TopicEntity> topicEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntity(@MappingTarget TopicEntity topicEntity, TopicDto topicDto);
}
