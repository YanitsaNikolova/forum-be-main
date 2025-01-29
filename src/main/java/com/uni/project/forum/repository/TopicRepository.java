package com.uni.project.forum.repository;

import com.uni.project.forum.data.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<TopicEntity, Long> {

    Optional<TopicEntity> findById(Long id);
}
