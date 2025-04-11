package org.example.newsfeed.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.board.dto.*;
import org.example.newsfeed.board.service.BoardServiceImpl;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;

    //게시글 생성
    @PostMapping
    public ResponseEntity<CreateResponseDto> createBoard(
            @Valid @RequestBody CreateRequestDto requestDto,
            @SessionAttribute(name = "loginUser") UserResponseDto userResponseDto)
    {
        CreateResponseDto responseDto = boardService.createBoard(requestDto, userResponseDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    //게시글 전체 조회
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll() {

        List<BoardResponseDto> postList = boardService.findAll();

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    //게시글 페이지로 조회
    @GetMapping("/pages")
    public ResponseEntity<Page<PageResponseDto>> findAllPage(@RequestParam(defaultValue = "1") int page) {

        Page<PageResponseDto> findAllPage = boardService.findAllPage(page);

        return new ResponseEntity<>(findAllPage, HttpStatus.OK);
    }

    //특정 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> findBoardById(@PathVariable Long id) {

        BoardResponseDto findedById = boardService.findBoardById(id);

        return new ResponseEntity<>(findedById, HttpStatus.OK);
    }

    //게시글 페이지로 조회
    @GetMapping("/pages")
    public ResponseEntity<Page<PageResponseDto>> findAllPage(@RequestParam(defaultValue = "1") int page) {

        Page<PageResponseDto> findAllPage = boardService.findAllPage(page);

        return new ResponseEntity<>(findAllPage, HttpStatus.OK);
    }

    //게시글 수정
    @PatchMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(
            @PathVariable Long id,
            @RequestBody UpdateRequestDto requestDto,
            @SessionAttribute(name = "loginUser") UserResponseDto userResponseDto)
    {
        BoardResponseDto update = boardService.updateBoard(id, requestDto, userResponseDto);

        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable Long id,
            @SessionAttribute(name = "loginUser") UserResponseDto userResponseDto)
    {
        boardService.deleteBoard(id, userResponseDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //수정일자 기준 최신순으로 조회
    @GetMapping("/sorted-by-modifiedAt")
    public ResponseEntity<Page<PageResponseDto>> sortedByModifiedAt(@RequestParam(defaultValue = "1") int page) {

        Page<PageResponseDto> pageResponseDto = boardService.sortedByModifiedAt(page);

        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    //기간별 조회
    @GetMapping("/period")
    public ResponseEntity<Page<PageResponseDto>> findBoardByPeriod(
            @Validated @ModelAttribute PeriodRequestDto requestDto,
            @RequestParam(defaultValue = "1") int page
            )
    {
        Page<PageResponseDto> findByPeriod = boardService.findBoardByPeriod(requestDto, page);

        return new ResponseEntity<>(findByPeriod, HttpStatus.OK);
    }

    //좋아요순으로 조회
    @GetMapping("/like")
    public ResponseEntity<Page<LikesResponseDto>> sortedByLikes(
            @RequestParam(defaultValue = "1") int page)
    {
        Page<LikesResponseDto> likesResponseDto = boardService.sortedByLikes(page);

        return new ResponseEntity<>(likesResponseDto, HttpStatus.OK);
    }
}
