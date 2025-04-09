package org.example.newsfeed.board.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.board.dto.*;
import org.example.newsfeed.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //게시글 생성
    @PostMapping
    public ResponseEntity<CreateResponseDto> create(@Valid @RequestBody CreateRequestDto requestDto, HttpSession session) {

        CreateResponseDto responseDto = boardService.create(requestDto, session);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    //게시글 전체 조회
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll() {

        List<BoardResponseDto> postList = boardService.findAll();

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    //특정 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> findById(@PathVariable Long id) {

        BoardResponseDto findedById = boardService.findById(id);

        return new ResponseEntity<>(findedById, HttpStatus.OK);
    }

    //게시글 수정
    @PatchMapping("/{id}")
    public ResponseEntity<BoardResponseDto> update(@PathVariable Long id, @RequestBody UpdateRequestDto requestDto) {

        BoardResponseDto update = boardService.update(id, requestDto);

        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        boardService.delete(id);
    }

    //게시글 페이지로 조회
    @GetMapping("/pages")
    public ResponseEntity<Page<PageResponseDto>> findAllPage(@RequestParam(defaultValue = "1") int page) {

        Page<PageResponseDto> findAllPage = boardService.findAllPage(page);

        return new ResponseEntity<>(findAllPage, HttpStatus.OK);
    }

    //수정일자 기준 최신순으로 조회
    @GetMapping("/sorted-by-modifiedAt")
    public ResponseEntity<List<BoardResponseDto>> sortedByModifiedAt() {

        List<BoardResponseDto> sortedByModifiedAt = boardService.sortedByModifiedAt();

        return new ResponseEntity<>(sortedByModifiedAt, HttpStatus.OK);
    }

    //기간별 검색
    @GetMapping("/period")
    public ResponseEntity<List<BoardResponseDto>> findByPeriod(@Valid @ModelAttribute PeriodRequestDto requestDto) {

        List<BoardResponseDto> findedByPeriod = boardService.findByPeriod(requestDto);

        return new ResponseEntity<>(findedByPeriod, HttpStatus.OK);
    }
}
