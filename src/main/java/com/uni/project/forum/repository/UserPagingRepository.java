package com.uni.project.forum.repository;

import com.uni.project.forum.data.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserPagingRepository extends PagingAndSortingRepository<UserEntity, Long> {
}
