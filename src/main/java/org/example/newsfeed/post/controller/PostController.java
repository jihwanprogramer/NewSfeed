package org.example.newsfeed.post.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.post.dto.CreateRequestDto;
import org.example.newsfeed.post.dto.CreateResponseDto;
import org.example.newsfeed.post.dto.PostResponseDto;
import org.example.newsfeed.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시글 생성
    @PostMapping
    public ResponseEntity<CreateResponseDto> create(@RequestBody CreateRequestDto requestDto) {

        CreateResponseDto responseDto = postService.create(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    //게시글 전체 조회
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> findAll() {

        List<PostResponseDto> postList = postService.findAll();

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }
}
