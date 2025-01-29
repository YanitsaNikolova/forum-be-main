package com.uni.project.forum.controller;

import com.uni.project.forum.data.dto.CommentDto;
import com.uni.project.forum.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
@Log4j2
public class CommentController {
    private final CommentService service;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto request) {
        CommentDto persist = service.createComment(request);
        return ResponseEntity.ok(persist);
    }
}
