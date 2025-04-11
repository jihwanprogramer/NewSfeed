package org.example.newsfeed.board.service;

import org.example.newsfeed.board.dto.*;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import java.util.List;

public interface BoardService {

    CreateResponseDto createBoard(CreateRequestDto requestDto, UserResponseDto userResponseDto);

    List<BoardResponseDto> findAll();

    Page<PageResponseDto> findAllPage(int page);

    BoardResponseDto findBoardById(Long id);

    BoardResponseDto updateBoard(Long id, UpdateRequestDto requestDto, UserResponseDto userResponseDto);

    Page<PageResponseDto> sortedByModifiedAt(int page);

    Page<PageResponseDto> findBoardByPeriod(PeriodRequestDto requestDto, int page);

    Page<LikesResponseDto> sortedByLikes(int page);

}
