package org.example.newsfeed.like.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.board.repository.BoardRepository;
import org.example.newsfeed.exception.AlreadyExistsEsception;
import org.example.newsfeed.like.dto.BoardLikeResponseDto;
import org.example.newsfeed.like.entity.BoardLike;
import org.example.newsfeed.like.repository.BoardLikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class BoardLikeService {

    private final BoardLikeRepository boardLikeRepository;

    private final BoardRepository boardRepository;

    public BoardLikeResponseDto saveLikeYN(Long boardId, Long userId) {
        //보드 아이디로 보드 찾기 좋아요 여부 저장

        Board findBoard = boardRepository.findByIdOrElseThrow(boardId);

        Optional<BoardLike> optionalBoardLike = boardLikeRepository.findByBoardAndUserId(findBoard, userId);
        if(optionalBoardLike.isPresent()) {
            throw new AlreadyExistsEsception("이미 좋아요 내역이 존재합니다.");
        }

            BoardLike boardLike = new BoardLike(userId, true);
            boardLike.setBoard(findBoard);

            boardLikeRepository.save(boardLike);

        return new BoardLikeResponseDto(boardLike.getId(),countLike(findBoard) ,boardLike.isLikeYN());
    }

    @Transactional
    public BoardLikeResponseDto changeLikeYN(Long boardId, Long userId) {

        Board findBoard = boardRepository.findByIdOrElseThrow(boardId);

        Optional<BoardLike> optionalBoardLike = boardLikeRepository.findByBoardAndUserId(findBoard, userId);
        if(optionalBoardLike.isEmpty()){
            throw new AlreadyExistsEsception("좋아요 내역이 존재 하지 않습니다.");
        }
        optionalBoardLike.get().changeLikeYN();

        BoardLike changedBoardLike = optionalBoardLike.get();

        return new BoardLikeResponseDto(changedBoardLike.getId(), countLike(findBoard), changedBoardLike.isLikeYN());
    }

    public BoardLikeResponseDto findBoardLikeByIdOrElseThrow(Long boardId, Long longinUserId){

        Board findBoard = boardRepository.findByIdOrElseThrow(boardId);

        Optional<BoardLike> optionalBoardLike = boardLikeRepository.findByBoardAndUserId(findBoard, longinUserId);
        if(optionalBoardLike.isEmpty()){
            return new BoardLikeResponseDto(null, countLike(findBoard), false);
        } else {
            return new BoardLikeResponseDto(optionalBoardLike.get().getId(), countLike(findBoard), optionalBoardLike.get().isLikeYN());
        }

    }

    public int countLike(Board board){

        return boardLikeRepository.countByBoardAndLikeYN(board, true);
    }
}
