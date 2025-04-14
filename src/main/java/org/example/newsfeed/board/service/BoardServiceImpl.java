package org.example.newsfeed.board.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.board.dto.*;
import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.board.repository.BoardRepository;
import org.example.newsfeed.like.repository.BoardLikeRepository;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.example.newsfeed.user.entity.User;
import org.example.newsfeed.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardLikeRepository boardLikeRepository;

    /**
     * 게시글 생성
     *
     * @param requestDto      게시글 생성 요청 데이터
     * @param userResponseDto 사용자 정보
     * @return 생성된 게시글 응답 DTO
     */
    @Transactional
    public CreateResponseDto createBoard(CreateRequestDto requestDto, UserResponseDto userResponseDto) {

        User findedUser = userRepository.findUserByIdOrElseThrow(userResponseDto.getId());

        Board board = Board.of(requestDto,findedUser);
        Board savedBoard = boardRepository.save(board);

        return new CreateResponseDto(savedBoard);
    }


    /**
     * 게시글 전체 조회
     *
     * @return 게시글 응답 DTO 리스트
     */
    public List<BoardResponseDto> findAll() {

        return boardRepository.findAll()
                .stream()
                .map(BoardResponseDto::findAll)
                .toList();
    }

    /**
     * 게시글 페이지 조회
     *
     * @param page 요청 페이지 번호
     * @return 페이지 응답 DTO
     */
    public Page<PageResponseDto> findAllPage(int page) {

        int adjustedPage = (page > 0) ? page - 1 : 0;
        PageRequest pageable = PageRequest.of(adjustedPage, 10, Sort.by("createdAt").descending());

        Page<Board> boardPage = boardRepository.findAll(pageable);

        return boardPage.map(board -> new PageResponseDto(board));
    }

    /**
     * 특정 게시글 조회
     *
     * @param id 게시글 ID
     * @return 게시글 응답 DTO
     */
    public BoardResponseDto findBoardById(Long id) {

        Board findedById = boardRepository.findByIdOrElseThrow(id);

        return new BoardResponseDto(findedById);
    }

    /**
     * 게시글 수정
     *
     * @param id              게시글 ID
     * @param requestDto      수정 요청 DTO
     * @param userResponseDto 요청한 사용자 정보
     * @return 수정된 게시글 응답 DTO
     */
    @Transactional
    public BoardResponseDto updateBoard(Long id, UpdateRequestDto requestDto, UserResponseDto userResponseDto) {

        Board findedBoard = boardRepository.findByIdOrElseThrow(id);
        Long userId = userResponseDto.getId();

        findedBoard.isSameUser(userId);
        findedBoard.updateBoard(requestDto);

        boardRepository.save(findedBoard);

        return new BoardResponseDto(findedBoard);
    }

    /**
     * 게시글 삭제
     *
     * @param id              게시글 ID
     * @param userResponseDto 요청한 사용자 정보
     */
    @Transactional
    public void deleteBoard(Long id, UserResponseDto userResponseDto) {

        Board findedBoard = boardRepository.findByIdOrElseThrow(id);
        Long userId = userResponseDto.getId();

        findedBoard.isSameUser(userId); //작성자와 로그인된 회원이 다를경우 예외처리

        boardRepository.delete(findedBoard);
    }

    /**
     * 수정일 기준 최신순 게시글 페이지 조회
     *
     * @param page 요청 페이지 번호
     * @return 페이지 응답 DTO
     */
    public Page<PageResponseDto> sortedByModifiedAt(int page) {

        int adjustedPage = (page > 0) ? page - 1 : 0;
        PageRequest pageable = PageRequest.of(adjustedPage, 10, Sort.by("modifiedAt").descending());

        Page<Board> boardPage = boardRepository.findAll(pageable);

        return boardPage.map(board -> new PageResponseDto(board));
    }

    /**
     * 기간별 게시글 검색
     *
     * @param requestDto 기간 검색 요청 DTO
     * @param page       요청 페이지 번호
     * @return 기간 내 게시글 페이지 응답 DTO
     */
    public Page<PageResponseDto> findBoardByPeriod(PeriodRequestDto requestDto, int page) {

        LocalDateTime startDate = LocalDate.parse(requestDto.getStartDate()).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(requestDto.getEndDate()).plusDays(1).atStartOfDay();

        int adjustedPage = (page > 0) ? page - 1 : 0;
        PageRequest pageable = PageRequest.of(adjustedPage, 10, Sort.by("createdAt").descending());

        Page<Board> boardPage = boardRepository.findByCreatedAtBetween(startDate, endDate, pageable);

        return boardPage.map(board -> new PageResponseDto(board));
    }

    /**
     * 좋아요 수 기준 정렬
     *
     * @param page 요청 페이지 번호
     * @return 좋아요 기준 정렬된 페이지 응답 DTO
     */
    public Page<LikesResponseDto> sortedByLikes(int page) {

        int adjustedPage = (page > 0) ? page - 1 : 0;
        PageRequest pageable = PageRequest.of(adjustedPage, 10, Sort.by("likesCount").descending());

        Page<Board> boardPage = boardRepository.findAll(pageable);

        return boardPage.map(board -> new LikesResponseDto(board));
    }

}
