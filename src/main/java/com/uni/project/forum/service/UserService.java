package com.uni.project.forum.service;

import com.uni.project.forum.data.dto.UserDto;
import com.uni.project.forum.data.entity.UserEntity;
import com.uni.project.forum.data.mapping.UserMapper;
import com.uni.project.forum.exceptions.ExistingEntityException;
import com.uni.project.forum.exceptions.MissingInputDataException;
import com.uni.project.forum.exceptions.NonExistingEntityException;
import com.uni.project.forum.repository.UserPagingRepository;
import com.uni.project.forum.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserPagingRepository pagingRepository;
    private final UserMapper mapper;
    private final PageableUtils pageableUtils;

    public UserDto createUser(UserDto user) {
        if (user == null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getRole()) || StringUtils.isBlank(user.getPassword()))
            throw new RuntimeException("Missing input data for user");
        if (repository.existsByUsername(user.getUsername())) throw new ExistingEntityException();
        UserEntity entity = mapper.toEntity(user);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    public UserDto fetchByUserName(UserDto userDto) {
        if (userDto == null || StringUtils.isEmpty(userDto.getUsername()))
            throw new MissingInputDataException("Missing input data for user");
        UserEntity entity = repository.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword())
                .orElseThrow(() -> new NonExistingEntityException("User with username " + userDto.getUsername() + " does not exist"));
        return mapper.toDto(entity);
    }

    public UserDto updateUser(Long userId, UserDto userDto) {
        UserEntity userEntity = fetchUserById(userId);
        mapper.updateUser(userEntity, userDto);
        repository.save(userEntity);
        return mapper.toDto(userEntity);
    }

    public List<UserDto> getAllUsers(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageableUtils.determinePageSize(page, pageSize));
        Page<UserEntity> entityPage = pagingRepository.findAll(pageRequest);
        return mapper.buildListUsers(entityPage);
    }

    public UserEntity fetchUser(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new NonExistingEntityException("User with username " + username + " does not exist"));
    }

    public UserEntity fetchUserById(Long userId) {
        return repository.findById(userId).orElseThrow(() -> new NonExistingEntityException("User with id " + userId + " does not exist"));
    }
}
