package com.uni.project.forum.repository;

import com.uni.project.forum.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);
}
