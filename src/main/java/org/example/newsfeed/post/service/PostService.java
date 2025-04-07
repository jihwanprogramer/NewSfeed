package org.example.newsfeed.post.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.post.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
}
