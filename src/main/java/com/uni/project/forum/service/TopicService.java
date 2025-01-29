package com.uni.project.forum.service;

import com.uni.project.forum.data.dto.TopicDto;
import com.uni.project.forum.data.entity.TopicEntity;
import com.uni.project.forum.data.entity.UserEntity;
import com.uni.project.forum.data.mapping.TopicMapper;
import com.uni.project.forum.exceptions.MissingInputDataException;
import com.uni.project.forum.exceptions.NonExistingEntityException;
import com.uni.project.forum.repository.TopicPagingRepository;
import com.uni.project.forum.repository.TopicRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository repository;
    private final UserService userService;
    private final TopicMapper mapper;
    private final TopicPagingRepository pagingRepository;
    private final PageableUtils pageableUtils;

    public TopicDto createTopic(TopicDto topic) {
        if (topic == null || topic.getUserId() == null || StringUtils.isEmpty(topic.getTitle()))
            throw new MissingInputDataException("Missing input data for topic");
        UserEntity user = userService.fetchUserById(topic.getUserId());
        TopicEntity entity = mapper.toEntity(topic, user);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    public TopicDto getTopic(Long id) {
        TopicEntity topicEntity = repository.findById(id).orElseThrow(() -> new NonExistingEntityException("Topic not found by Id: " + id));
        return mapper.toDto(topicEntity);
    }

    public List<TopicDto> getAllTopicsByUsername(String username, int page, int pageSize) {
        int effectivePageSize = pageableUtils.determineTopicPageSize(page, pageSize);
        UserEntity userEntity = userService.fetchUser(username);
        PageRequest pageRequest = PageRequest.of(page, effectivePageSize, Sort.by("created").descending());
        List<TopicEntity> topicEntities = pagingRepository.findAllByUser(userEntity, pageRequest);
        return mapper.buildListOfTopics(topicEntities);
    }

    public List<TopicDto> getAllTopics(int page, int pageSize) {
        int effectivePageSize = pageableUtils.determineTopicPageSize(page, pageSize);
        Page<TopicEntity> topicEntities = pagingRepository.findAll(PageRequest.of(page, effectivePageSize));
        return mapper.buildListOfTopics(topicEntities.getContent());
    }

    public TopicDto updateTopic(Long id, TopicDto topicDto) {
        if (topicDto == null || topicDto.getUserId() == null || StringUtils.isEmpty(topicDto.getTitle()))
            throw new MissingInputDataException("Missing input data for topic");
        TopicEntity topicEntity = repository.findById(id).orElseThrow(() -> new NonExistingEntityException("Topic not found by Id: " + id));
        UserEntity userEntity = userService.fetchUserById(topicDto.getUserId());
        if (!userEntity.getId().equals(topicEntity.getUser().getId())) topicEntity.setUser(userEntity);
        mapper.updateEntity(topicEntity, topicDto);
        repository.save(topicEntity);
        return mapper.toDto(topicEntity);
    }

    public void updateTopicViews(Long id, Integer numberOfViews) {
        TopicEntity topicEntity = repository.findById(id).orElseThrow(() -> new NonExistingEntityException("Topic not found by Id: " + id));
        topicEntity.setViews(numberOfViews);
        repository.save(topicEntity);
    }
}
