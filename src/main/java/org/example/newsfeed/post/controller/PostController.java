package org.example.newsfeed.post.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.post.dto.CreateRequestDto;
import org.example.newsfeed.post.dto.CreateResponseDto;
import org.example.newsfeed.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<CreateResponseDto> create(@RequestBody CreateRequestDto requestDto) {

        CreateResponseDto responseDto = postService.create(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
