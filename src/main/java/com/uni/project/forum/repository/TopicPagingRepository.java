package com.uni.project.forum.repository;

import com.uni.project.forum.data.entity.TopicEntity;
import com.uni.project.forum.data.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TopicPagingRepository extends PagingAndSortingRepository<TopicEntity, Long> {
    List<TopicEntity> findAllByUser(UserEntity user, Pageable pageable);
}
