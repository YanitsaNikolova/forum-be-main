package com.uni.project.forum;

import com.uni.project.forum.data.dto.UserDto;
import com.uni.project.forum.data.entity.UserEntity;
import com.uni.project.forum.data.mapping.UserMapper;
import com.uni.project.forum.exceptions.ExistingEntityException;
import com.uni.project.forum.exceptions.MissingInputDataException;
import com.uni.project.forum.exceptions.NonExistingEntityException;
import com.uni.project.forum.repository.UserPagingRepository;
import com.uni.project.forum.repository.UserRepository;
import com.uni.project.forum.service.PageableUtils;
import com.uni.project.forum.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserPagingRepository pagingRepository;

    @Mock
    private UserMapper mapper;

    @Mock
    private PageableUtils pageableUtils;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_ShouldThrowMissingInputDataException_WhenUserIsNull() {
        assertThrows(RuntimeException.class, () -> userService.createUser(null));
    }

    @Test
    void createUser_ShouldThrowExistingEntityException_WhenUserExists() {
        UserDto userDto = new UserDto();
        userDto.setUsername("existingUser");
        userDto.setRole("user");
        userDto.setPassword("password");
        when(repository.existsByUsername(userDto.getUsername())).thenReturn(true);

        assertThrows(ExistingEntityException.class, () -> userService.createUser(userDto));
    }

    @Test
    void createUser_ShouldReturnUserDto_WhenUserIsCreated() {
        UserDto userDto = new UserDto();
        userDto.setUsername("newUser");
        userDto.setRole("user");
        userDto.setPassword("password");
        UserEntity userEntity = new UserEntity();
        when(repository.existsByUsername(userDto.getUsername())).thenReturn(false);
        when(mapper.toEntity(userDto)).thenReturn(userEntity);
        when(repository.save(userEntity)).thenReturn(userEntity);
        when(mapper.toDto(userEntity)).thenReturn(userDto);

        UserDto createdUser = userService.createUser(userDto);

        assertEquals(userDto, createdUser);
    }

    @Test
    void fetchByUserName_ShouldThrowMissingInputDataException_WhenUserDtoIsNull() {
        assertThrows(MissingInputDataException.class, () -> userService.fetchByUserName(null));
    }

    @Test
    void fetchByUserName_ShouldThrowNonExistingEntityException_WhenUserDoesNotExist() {
        UserDto userDto = new UserDto();
        userDto.setUsername("nonExistingUser");
        when(repository.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword())).thenReturn(Optional.empty());

        assertThrows(NonExistingEntityException.class, () -> userService.fetchByUserName(userDto));
    }

    @Test
    void getAllUsers_ShouldReturnListOfUserDtos() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<UserEntity> userPage = new PageImpl<>(Collections.singletonList(new UserEntity()));
        when(pagingRepository.findAll(pageRequest)).thenReturn(userPage);
        when(mapper.buildListUsers(userPage)).thenReturn(Collections.singletonList(new UserDto()));
        when(pageableUtils.determinePageSize(0, 10)).thenReturn(10);

        List<UserDto> users = userService.getAllUsers(0, 10);

        assertEquals(1, users.size());
    }
}