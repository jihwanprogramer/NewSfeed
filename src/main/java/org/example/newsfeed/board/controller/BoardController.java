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

    /**
     * 게시글 생성
     *
     * @param requestDto 게시글 생성 요청 DTO
     * @param userResponseDto 세션에서 가져온 로그인 사용자 정보
     * @return 생성된 게시글 응답 DTO
     */
    @PostMapping
    public ResponseEntity<CreateResponseDto> createBoard(
            @Valid @RequestBody CreateRequestDto requestDto,
            @SessionAttribute(name = "loginUser") UserResponseDto userResponseDto)
    {
        CreateResponseDto responseDto = boardService.createBoard(requestDto, userResponseDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    /**
     * 전체 게시글 조회
     *
     * @return 게시글 리스트 응답 DTO
     */
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll() {

        List<BoardResponseDto> postList = boardService.findAll();

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    /**
     * 게시글 페이지 단위 조회
     *
     * @param page 요청 페이지 번호
     * @return 페이징된 게시글 응답 DTO
     */
    @GetMapping("/pages")
    public ResponseEntity<Page<PageResponseDto>> findAllPage(@RequestParam(defaultValue = "1") int page) {

        Page<PageResponseDto> findAllPage = boardService.findAllPage(page);

        return new ResponseEntity<>(findAllPage, HttpStatus.OK);
    }

    /**
     * 특정 게시글 상세 조회
     *
     * @param id 게시글 ID
     * @return 게시글 응답 DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> findBoardById(@PathVariable Long id) {

        BoardResponseDto findedById = boardService.findBoardById(id);

        return new ResponseEntity<>(findedById, HttpStatus.OK);
    }

    /**
     * 게시글 수정
     *
     * @param id 게시글 ID
     * @param requestDto 수정 요청 DTO
     * @param userResponseDto 세션에서 가져온 로그인 사용자 정보
     * @return 수정된 게시글 응답 DTO
     */
    @PatchMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(
            @PathVariable Long id,
            @RequestBody UpdateRequestDto requestDto,
            @SessionAttribute(name = "loginUser") UserResponseDto userResponseDto)
    {
        BoardResponseDto update = boardService.updateBoard(id, requestDto, userResponseDto);

        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    /**
     * 게시글 삭제
     *
     * @param id 게시글 ID
     * @param userResponseDto 세션에서 가져온 로그인 사용자 정보
     * @return 응답 없음(204)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable Long id,
            @SessionAttribute(name = "loginUser") UserResponseDto userResponseDto)
    {
        boardService.deleteBoard(id, userResponseDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 수정일 기준 최신순으로 게시글 조회
     *
     * @param page 요청 페이지 번호
     * @return 정렬된 게시글 페이지 응답 DTO
     */
    @GetMapping("/sorted-by-modifiedAt")
    public ResponseEntity<Page<PageResponseDto>> sortedByModifiedAt(@RequestParam(defaultValue = "1") int page) {

        Page<PageResponseDto> pageResponseDto = boardService.sortedByModifiedAt(page);

        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    /**
     * 기간별 게시글 조회
     *
     * @param requestDto 검색 기간 요청 DTO
     * @param page 요청 페이지 번호
     * @return 기간 내 게시글 페이지 응답 DTO
     */
    @GetMapping("/period")
    public ResponseEntity<Page<PageResponseDto>> findBoardByPeriod(
            @Validated @ModelAttribute PeriodRequestDto requestDto,
            @RequestParam(defaultValue = "1") int page
            )
    {
        Page<PageResponseDto> findByPeriod = boardService.findBoardByPeriod(requestDto, page);

        return new ResponseEntity<>(findByPeriod, HttpStatus.OK);
    }

    /**
     * 좋아요 수 기준 게시글 정렬
     *
     * @param page 요청 페이지 번호
     * @return 좋아요 기준 정렬된 게시글 페이지 응답 DTO
     */
    @GetMapping("/like")
    public ResponseEntity<Page<LikesResponseDto>> sortedByLikes(
            @RequestParam(defaultValue = "1") int page)
    {
        Page<LikesResponseDto> likesResponseDto = boardService.sortedByLikes(page);

        return new ResponseEntity<>(likesResponseDto, HttpStatus.OK);
    }
}
