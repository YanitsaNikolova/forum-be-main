package com.uni.project.forum.data.mapping;

import com.uni.project.forum.config.GlobalMapperConfig;
import com.uni.project.forum.data.dto.UserDto;
import com.uni.project.forum.data.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

@Mapper(config = GlobalMapperConfig.class)
public interface UserMapper {

    UserEntity toEntity(UserDto dto);

    UserDto toDto(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserDto userDto, Long id, Date created);

    @Mapping(target = "id", ignore = true)
    void updateUser(@MappingTarget UserEntity userEntity, UserDto userDto);

    List<UserDto> buildListUsers(Page<UserEntity> userEntityPage);
}
