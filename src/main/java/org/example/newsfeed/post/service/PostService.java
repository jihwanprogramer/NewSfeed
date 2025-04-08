package org.example.newsfeed.post.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.post.dto.CreateRequestDto;
import org.example.newsfeed.post.dto.CreateResponseDto;
import org.example.newsfeed.post.dto.PostResponseDto;
import org.example.newsfeed.post.dto.UpdateRequestDto;
import org.example.newsfeed.post.entity.Post;
import org.example.newsfeed.post.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    //게시글 생성
    public CreateResponseDto create(CreateRequestDto requestDto) {

        Post post = new Post(requestDto);
        Post savedPost = postRepository.save(post);

        return new CreateResponseDto(savedPost);
    }


    //게시글 전체 조히
    public List<PostResponseDto> findAll() {

        return postRepository.findAll()
                .stream()
                .map(PostResponseDto::findAll)
                .toList();
    }

    //특정 게시글 조회
    public PostResponseDto findById(Long id) {

        Post findedById = postRepository.findByIdOrElseThrow(id);

        return new PostResponseDto(findedById);
    }

    //특정 게시물 수정
    public PostResponseDto update(Long id, UpdateRequestDto requestDto) {

        Post findedPost = postRepository.findByIdOrElseThrow(id);

        if (requestDto.getTitle() == null && requestDto.getContents() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "잘못된 요청입니다."
            );
        }

        if (requestDto.getTitle() != null) {
            findedPost.updateTitle(requestDto);
        }

        if (requestDto.getContents() != null) {
            findedPost.updateContents(requestDto);
        }

        postRepository.save(findedPost);

        return new PostResponseDto(findedPost);
    }

    //특정 게시물 삭제
    public void delete(Long id) {

        Post findedPost = postRepository.findByIdOrElseThrow(id);

        postRepository.delete(findedPost);
    }
}
