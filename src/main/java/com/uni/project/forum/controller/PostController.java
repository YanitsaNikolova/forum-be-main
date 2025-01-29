package com.uni.project.forum.controller;

import com.uni.project.forum.data.dto.PostDto;
import com.uni.project.forum.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Log4j2
public class PostController {
    private final PostService service;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto request) {
        PostDto persist = service.createPost(request);
        return ResponseEntity.ok(persist);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "0") int pageSize) {
        return ResponseEntity.ok(service.getAllPosts(page, pageSize));
    }

    @GetMapping(path = "/topic/{topicId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostDto>> getPostsByTopic(
            @PathVariable Long topicId,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "0") int pageSize) {
        return ResponseEntity.ok(service.getAllPostsByTopic(topicId, page, pageSize));
    }

    @PutMapping(path = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDto> updatePost(
            @PathVariable Long postId, @RequestBody PostDto reply) {
        return ResponseEntity.ok(service.updatePost(postId, reply));
    }
}
