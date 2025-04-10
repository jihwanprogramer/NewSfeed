package org.example.newsfeed.board.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.board.dto.*;
import org.example.newsfeed.board.service.BoardService;
import org.example.newsfeed.common.Const;
import org.example.newsfeed.user.dto.UserResponseDto;
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

        UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        CreateResponseDto responseDto = boardService.create(requestDto, userResponseDto);

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
    public ResponseEntity<BoardResponseDto> update(@PathVariable Long id, @RequestBody UpdateRequestDto requestDto, HttpSession session) {

        UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        BoardResponseDto update = boardService.update(id, requestDto, userResponseDto);

        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, HttpSession session) {

        UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        boardService.delete(id, userResponseDto);
    }

    //게시글 페이지로 조회
    @GetMapping("/pages")
    public ResponseEntity<Page<PageResponseDto>> findAllPage(@RequestParam(defaultValue = "1") int page) {

        Page<PageResponseDto> findAllPage = boardService.findAllPage(page);

        return new ResponseEntity<>(findAllPage, HttpStatus.OK);
    }

    //수정일자 기준 최신순으로 조회
    @GetMapping("/sorted-by-modifiedAt")
    public ResponseEntity<Page<PageResponseDto>> sortedByModifiedAt(@RequestParam(defaultValue = "1") int page) {

        Page<PageResponseDto> pageResponseDto = boardService.sortedByModifiedAt(page);

        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    //기간별 검색
    @GetMapping("/period")
    public ResponseEntity<List<BoardResponseDto>> findByPeriod(@Valid @ModelAttribute PeriodRequestDto requestDto) {

        List<BoardResponseDto> findedByPeriod = boardService.findByPeriod(requestDto);

        return new ResponseEntity<>(findedByPeriod, HttpStatus.OK);
    }
}
