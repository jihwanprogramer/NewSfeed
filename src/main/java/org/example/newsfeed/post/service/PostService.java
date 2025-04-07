package org.example.newsfeed.post.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.post.dto.CreateRequestDto;
import org.example.newsfeed.post.dto.CreateResponseDto;
import org.example.newsfeed.post.entity.Post;
import org.example.newsfeed.post.repository.PostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public CreateResponseDto create(CreateRequestDto requestDto) {

        Post post = new Post(requestDto);

        Post savedPost = postRepository.save(post);

        return new CreateResponseDto(savedPost);
    }
}
