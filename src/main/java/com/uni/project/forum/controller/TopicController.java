package com.uni.project.forum.controller;

import com.uni.project.forum.data.dto.TopicDto;
import com.uni.project.forum.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/topics")
public class TopicController {
    private final TopicService topicService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicDto> createTopic(@RequestBody TopicDto topic) {
        TopicDto topicDto = topicService.createTopic(topic);
        return ResponseEntity.ok(topicDto);
    }

    @GetMapping(path = "/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TopicDto>> getReplyByTopic(
            @PathVariable String username,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "0") int pageSize) {
        return ResponseEntity.ok(topicService.getAllTopicsByUsername(
                username,
                page, pageSize
        ));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicDto> getTopic(@PathVariable("id") Long id) {
        return ResponseEntity.ok(topicService.getTopic(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TopicDto>> getAllTopics(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "0") Integer pageSize
    ) {
        return ResponseEntity.ok(topicService.getAllTopics(page, pageSize));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicDto> updateTopic(
            @PathVariable("id") Long id, @RequestBody TopicDto topicDto) {
        return ResponseEntity.ok(topicService.updateTopic(id, topicDto));
    }

    @PutMapping(path = "/update/{id}/views/{viewNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateTopic(
            @PathVariable("id") Long id, @PathVariable("viewNumber") Integer numberOfViews) {
        if (numberOfViews == null) numberOfViews = 0;
        topicService.updateTopicViews(id, numberOfViews);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
