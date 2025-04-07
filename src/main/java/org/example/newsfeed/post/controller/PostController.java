package org.example.newsfeed.post.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.post.service.PostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
}
