package org.example.newsfeed.board.service;

import org.example.newsfeed.board.dto.*;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardService {

    public CreateResponseDto createBoard(CreateRequestDto requestDto, UserResponseDto userResponseDto);

    public List<BoardResponseDto> findAll();

    public Page<PageResponseDto> findAllPage(int page);

    public BoardResponseDto findBoardById(Long id);

    public BoardResponseDto updateBoard(Long id, UpdateRequestDto requestDto, UserResponseDto userResponseDto);

    public void deleteBoard(Long id, UserResponseDto userResponseDto);

    public Page<PageResponseDto> sortedByModifiedAt(int page);

    public Page<PageResponseDto> findBoardByPeriod(PeriodRequestDto requestDto, int page);
}
