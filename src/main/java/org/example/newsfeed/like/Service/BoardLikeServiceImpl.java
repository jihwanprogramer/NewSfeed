package org.example.newsfeed.like.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.board.repository.BoardRepository;
import org.example.newsfeed.exception.AlreadyExistsException;
import org.example.newsfeed.exception.NullResponseException;
import org.example.newsfeed.like.dto.BoardLikeResponseDto;
import org.example.newsfeed.like.entity.BoardLike;
import org.example.newsfeed.like.repository.BoardLikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class BoardLikeServiceImpl implements BoardLikeService{

    private final BoardLikeRepository boardLikeRepository;

    private final BoardRepository boardRepository;

    /**
     * 게시물 좋아요 생성
     * @param boardId 게시물 ID
     * @param userId 좋아요 유저 ID
     * @return 생성된 게시물 좋아요 DTO
     * @throws AlreadyExistsException 이미 좋아요가 존재할 경우 예외 발생
     */
    @Override
    public BoardLikeResponseDto saveLikeYN(Long boardId, Long userId) {
        //보드 아이디로 보드 찾기 좋아요 여부 저장

        Board findBoard = boardRepository.findByIdOrElseThrow(boardId);

        Optional<BoardLike> optionalBoardLike = boardLikeRepository.findByBoardAndUserId(findBoard, userId);
        if(optionalBoardLike.isPresent()) {
            throw new AlreadyExistsException("이미 좋아요 내역이 존재합니다.");
        }

            BoardLike boardLike = BoardLike.createLikeYN(findBoard, userId);
            findBoard.increaseLike();

            boardLikeRepository.save(boardLike);

        return new BoardLikeResponseDto(boardLike.getId(),countLike(findBoard) ,boardLike.isLikeYN());
    }

    /**
     * 좋아요 상태를 토글
     * @param boardId 게시물 ID
     * @param userId 사용자 ID
     * @return 좋아요의 상태 true, false
     * @throws NullResponseException 좋아요 내역이 없다면 발생하는 예외
     */
    @Transactional
    @Override
    public BoardLikeResponseDto changeLikeYN(Long boardId, Long userId) {

        Board findBoard = boardRepository.findByIdOrElseThrow(boardId);

        Optional<BoardLike> optionalBoardLike = boardLikeRepository.findByBoardAndUserId(findBoard, userId);
        if(optionalBoardLike.isEmpty()){
            throw new NullResponseException("좋아요 내역이 존재 하지 않습니다.");
        }

        optionalBoardLike.get().changeLikeYN();

        BoardLike changedBoardLike = optionalBoardLike.get();
        findBoard.decreaseLike();

        return new BoardLikeResponseDto(changedBoardLike.getId(), countLike(findBoard), changedBoardLike.isLikeYN());
    }

    /**
     * 좋아요 내역 조회 하기
     * @param boardId 게시물 ID
     * @param longinUserId 로그인한 유저 ID
     * @return 좋아요 확인여부의 정보 DTO
     *
     */
    @Override
    public BoardLikeResponseDto findBoardLikeByIdOrElseThrow(Long boardId, Long longinUserId){

        Board findBoard = boardRepository.findByIdOrElseThrow(boardId);

        Optional<BoardLike> optionalBoardLike = boardLikeRepository.findByBoardAndUserId(findBoard, longinUserId);
        if(optionalBoardLike.isEmpty()){
            return new BoardLikeResponseDto(null, countLike(findBoard), false);
        } else {
            return new BoardLikeResponseDto(optionalBoardLike.get().getId(), countLike(findBoard), optionalBoardLike.get().isLikeYN());
        }

    }

    /**
     *  게시물의 좋아요 총 갯수
     * @param board 게시물 정보
     * @return 좋아요 갯수
     */
    @Override
    public int countLike(Board board){

        return boardLikeRepository.countByBoardAndLikeYN(board, true);
    }
}
