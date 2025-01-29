package com.uni.project.forum;

import com.uni.project.forum.controller.UserController;
import com.uni.project.forum.data.dto.UserDto;
import com.uni.project.forum.exceptions.NonExistingEntityException;
import com.uni.project.forum.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_ShouldReturnOkStatus_WhenUserIsCreated() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("newUser");
        when(userService.createUser(any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"newUser\", \"password\": \"password\", \"role\": \"user\"}"))
                .andExpect(status().isOk());
    }


    @Test
    void getUser_ShouldReturnOkStatus_WhenUserExists() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("existingUser");
        when(userService.fetchByUserName(any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"existingUser\", \"password\": \"password\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void getUser_ShouldReturnNotFoundStatus_WhenUserDoesNotExist() throws Exception {
        when(userService.fetchByUserName(any(UserDto.class))).thenThrow(new NonExistingEntityException("User not found"));

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"nonExistingUser\", \"password\": \"password\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateUser_ShouldReturnOkStatus_WhenUserIsUpdated() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(100L);
        when(userService.updateUser(anyLong(), any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(put("/api/v1/users/100L")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"updatedUser\", \"password\": \"newPassword\", \"role\": \"user\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void updateUser_ShouldReturnNotFoundStatus_WhenUserDoesNotExist() throws Exception {
        when(userService.updateUser(anyLong(), any(UserDto.class))).thenThrow(new NonExistingEntityException("User not found"));

        mockMvc.perform(put("/api/v1/users/100L")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"nonExistingUser\", \"password\": \"newPassword\", \"role\": \"user\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllUsers_ShouldReturnOkStatus_WhenUsersExist() throws Exception {
        when(userService.getAllUsers(anyInt(), anyInt())).thenReturn(Collections.singletonList(new UserDto()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
                        .param("page", "0")
                        .param("pageSize", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllUsers_ShouldReturnEmptyList_WhenNoUsersExist() throws Exception {
        when(userService.getAllUsers(anyInt(), anyInt())).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
                        .param("page", "0")
                        .param("pageSize", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

}
