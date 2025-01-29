package com.uni.project.forum.service;

import com.uni.project.forum.data.dto.PostDto;
import com.uni.project.forum.data.entity.PostEntity;
import com.uni.project.forum.data.entity.TopicEntity;
import com.uni.project.forum.data.entity.UserEntity;
import com.uni.project.forum.data.mapping.PostMapper;
import com.uni.project.forum.exceptions.DuplicationException;
import com.uni.project.forum.exceptions.NonExistingEntityException;
import com.uni.project.forum.repository.PostPagingRepository;
import com.uni.project.forum.repository.PostRepository;
import com.uni.project.forum.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserService userService;
    private final PostRepository postRepository;
    private final PostPagingRepository pagingRepository;
    private final TopicRepository topicRepository;
    private final PostMapper mapper;

    public PostDto createPost(PostDto dto) {
        if (dto == null || dto.getTopicId() == null) throw new NonExistingEntityException();
        TopicEntity topicEntity = findTopicById(dto.getTopicId());
        if (postRepository.existsByTextAndTopic(dto.getText(), topicEntity))
            throw new DuplicationException("Post already exist with this text in this topic");
        UserEntity userEntity = userService.fetchUser(dto.getUsername());
        PostEntity entity = mapper.toEntity(dto, topicEntity, userEntity);
        postRepository.save(entity);
        return mapper.toDto(entity);
    }

    public List<PostDto> getAllPostsByTopic(Long id, int page, int pageSize) {
        TopicEntity topic = findTopicById(id);
        int effectivePageSize = determinePageSize(pageSize, page);
//        PageRequest pageRequest = PageRequest.of(page, effectivePageSize, Sort.by("created").descending());
        List<PostEntity> allByTopic = pagingRepository.findAllByTopic(topic, Pageable.unpaged());
        return mapper.buildListOfPostDto(allByTopic);
    }

    private int determinePageSize(int page, int pageSize) {
        return (page == 0 && pageSize == 0) ? 5 : pageSize;
    }

    public List<PostDto> getAllPosts(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, determinePageSize(page, pageSize), Sort.by("created").descending());
        Page<PostEntity> entityPage = pagingRepository.findAll(pageRequest);
        return mapper.buildListOfPostDto(entityPage.getContent());
    }

    private TopicEntity findTopicById(Long topicId) {
        return topicRepository.findById(topicId).orElseThrow(() -> new NonExistingEntityException("Topic not found by Id: " + topicId));
    }

    public PostDto updatePost(Long postId, PostDto postDto) {
        PostEntity replyEntity = postRepository.findById(postId).orElseThrow(() -> new NonExistingEntityException("Post not found by Id: " + postId));
        UserEntity userEntity = userService.fetchUser(postDto.getUsername());
        TopicEntity topicEntity = findTopicById(postDto.getTopicId());
        mapper.updateEntity(replyEntity, postDto, userEntity, topicEntity);
        return mapper.toDto(replyEntity);
    }
}
