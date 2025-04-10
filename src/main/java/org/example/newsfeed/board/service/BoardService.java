package org.example.newsfeed.board.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.board.dto.*;
import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.board.repository.BoardRepository;
import org.example.newsfeed.exception.MisMatchUserException;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.example.newsfeed.user.entity.User;
import org.example.newsfeed.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    //게시글 생성
    @Transactional
    public CreateResponseDto create(CreateRequestDto requestDto, UserResponseDto userResponseDto) {

        User findedUser = userRepository.findUserByIdOrElseThrow(userResponseDto.getId());

        Board board = new Board(requestDto);
        board.setUser(findedUser);
        Board savedBoard = boardRepository.save(board);

        return new CreateResponseDto(savedBoard);
    }


    //게시글 전체 조회
    public List<BoardResponseDto> findAll() {

        return boardRepository.findAll()
                .stream()
                .map(BoardResponseDto::findAll)
                .toList();
    }

    //특정 게시글 조회
    public BoardResponseDto findById(Long id) {

        Board findedById = boardRepository.findByIdOrElseThrow(id);

        return new BoardResponseDto(findedById);
    }

    //특정 게시물 수정
    @Transactional
    public BoardResponseDto update(Long id, UpdateRequestDto requestDto, UserResponseDto userResponseDto) {

        Board findedBoard = boardRepository.findByIdOrElseThrow(id);

        if (!findedBoard.getUser().isSameUser(userResponseDto.getId())) { //작성자와 로그인된 회원이 다를경우 예외처리
            throw new MisMatchUserException("작성자만 수정할 수 있습니다.");
        }

        if (requestDto.getTitle() == null && requestDto.getContents() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "잘못된 요청입니다."
            );
        }

        if (requestDto.getTitle() != null) {
            findedBoard.updateTitle(requestDto);
        }

        if (requestDto.getContents() != null) {
            findedBoard.updateContents(requestDto);
        }

        boardRepository.save(findedBoard);

        return new BoardResponseDto(findedBoard);
    }

    //특정 게시물 삭제
    @Transactional
    public void delete(Long id, UserResponseDto userResponseDto) {

        Board findedBoard = boardRepository.findByIdOrElseThrow(id);

        if (!findedBoard.getUser().isSameUser(userResponseDto.getId())) { //작성자와 로그인된 회원이 다를경우 예외처리
            throw new MisMatchUserException("작성자만 삭제할 수 있습니다.");
        }

        boardRepository.delete(findedBoard);
    }

    //게시글 페이지로 조회
    public Page<PageResponseDto> findAllPage(int page) {

        int adjustedPage = (page > 0) ? page - 1 : 0;
        PageRequest pageable = PageRequest.of(adjustedPage, 10, Sort.by("createdAt").descending());

        Page<Board> boardPage = boardRepository.findAll(pageable);

        return boardPage.map(board -> new PageResponseDto(board));
    }

    //수정일자 기준 최신순으로 조회
    public Page<PageResponseDto> sortedByModifiedAt(int page) {

        int adjustedPage = (page > 0) ? page - 1 : 0;
        PageRequest pageable = PageRequest.of(adjustedPage, 10, Sort.by("modifiedAt").descending());

        Page<Board> boardPage = boardRepository.findAll(pageable);

        return boardPage.map(board -> new PageResponseDto(board));
    }

    //기간별 검색
    public List<BoardResponseDto> findByPeriod(PeriodRequestDto requestDto) {

        LocalDateTime startDate = LocalDate.parse(requestDto.getStartDate()).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(requestDto.getEndDate()).plusDays(1).atStartOfDay();

        return boardRepository.findByCreatedAtBetween(startDate, endDate)
                .stream()
                .map(BoardResponseDto::findAll)
                .toList();
    }

}
