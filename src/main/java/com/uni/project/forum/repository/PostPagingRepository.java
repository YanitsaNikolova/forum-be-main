package com.uni.project.forum.repository;

import com.uni.project.forum.data.entity.PostEntity;
import com.uni.project.forum.data.entity.TopicEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostPagingRepository extends PagingAndSortingRepository<PostEntity, Long> {
    List<PostEntity> findAllByTopic(TopicEntity topicEntity, Pageable pageable);
}
