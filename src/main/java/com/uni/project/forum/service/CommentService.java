package com.uni.project.forum.service;

import com.uni.project.forum.data.dto.CommentDto;
import com.uni.project.forum.data.entity.CommentEntity;
import com.uni.project.forum.data.entity.PostEntity;
import com.uni.project.forum.data.mapping.CommentMapper;
import com.uni.project.forum.exceptions.NonExistingEntityException;
import com.uni.project.forum.repository.CommentRepository;
import com.uni.project.forum.repository.PostRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository repository;
    private final CommentMapper mapper;

    public CommentDto createComment(CommentDto dto) {
        if (dto == null || StringUtils.isEmpty(dto.getText())) throw new NonExistingEntityException();
        PostEntity replyEntity = postRepository.findById(dto.getPostId()).orElseThrow(() -> new NonExistingEntityException("Reply not found by Id: " + dto.getPostId()));
        CommentEntity entity = mapper.toEntity(dto, replyEntity);
        repository.save(entity);
        return mapper.toDto(entity);
    }
}
