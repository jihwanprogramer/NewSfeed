package org.example.newsfeed.like.Service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.board.repository.BoardRepository;
import org.example.newsfeed.exception.AlreadyExistsEsception;
import org.example.newsfeed.like.dto.BoardLikeResponseDto;
import org.example.newsfeed.like.entity.BoardLike;
import org.example.newsfeed.like.repository.BoardLikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

        return new BoardLikeResponseDto(boardLike.getId(), boardLike.isLikeYN());
    }

    public boolean changeLikeYN(Long boardId, Long userId) {

        Board findBoard = boardRepository.findByIdOrElseThrow(boardId);

        Optional<BoardLike> optionalBoardLike = boardLikeRepository.findByBoardAndUserId(findBoard, userId);
        if(optionalBoardLike.isEmpty()){
            throw new AlreadyExistsEsception("좋아요 내역이 존재 하지 않습니다.");
        }

        return optionalBoardLike.get().changeLikeYN();
    }

//    public List<BoardLikeResponseDto> findAllBoardLike() {
//
//        return boardLikeRepository.findAllBoardLike();
//    }

//    public BoardLikeResponseDto findBoardLikeById(Long id){
//
//        BoardLike boardLike = boardLikeRepository.findBoardLikeById(id);
//
//        return new BoardLikeResponseDto(boardLike, );
//    }
}
