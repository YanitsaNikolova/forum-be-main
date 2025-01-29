package com.uni.project.forum.repository;

import com.uni.project.forum.data.entity.PostEntity;
import com.uni.project.forum.data.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    boolean existsByTextAndTopic(String text, TopicEntity topic);
}
