package org.example.newsfeed.post.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.Const;
import org.example.newsfeed.common.config.ConfirmSameUser;
import org.example.newsfeed.post.dto.CreateRequestDto;
import org.example.newsfeed.post.dto.CreateResponseDto;
import org.example.newsfeed.post.dto.PostResponseDto;
import org.example.newsfeed.post.dto.UpdateRequestDto;
import org.example.newsfeed.post.entity.Post;
import org.example.newsfeed.post.repository.PostRepository;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.example.newsfeed.user.entity.Users;
import org.example.newsfeed.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ConfirmSameUser confirmSameUser;
    private final UserRepository userRepository;

    //게시글 생성
    @Transactional
    public CreateResponseDto create(CreateRequestDto requestDto, HttpSession session) {

        UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);
        Users findedUser = userRepository.findUserByIdOrElseThrow(userResponseDto.getId());

        Post post = new Post(requestDto);
        post.setUser(findedUser);
        Post savedPost = postRepository.save(post);

        return new CreateResponseDto(savedPost);
    }


    //게시글 전체 조회
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
    @Transactional
    public PostResponseDto update(Long id, UpdateRequestDto requestDto) {

        Post findedPost = postRepository.findByIdOrElseThrow(id);

        confirmSameUser.isSameUser(findedPost.getUser().getId()); //작성자와 로그인된 회원이 다를경우 예외처리

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
    @Transactional
    public void delete(Long id) {

        Post findedPost = postRepository.findByIdOrElseThrow(id);

        confirmSameUser.isSameUser(findedPost.getUser().getId()); //작성자와 로그인된 회원이 다를경우 예외처리

        postRepository.delete(findedPost);
    }
}
