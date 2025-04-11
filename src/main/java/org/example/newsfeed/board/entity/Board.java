package org.example.newsfeed.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.newsfeed.board.dto.CreateRequestDto;
import org.example.newsfeed.board.dto.UpdateRequestDto;
import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.common.entity.BaseEntity;
import org.example.newsfeed.exception.MisMatchUserException;
import org.example.newsfeed.user.entity.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "boards")
public class Board extends BaseEntity {

    /**
     * 게시글 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 게시글 제목
     */
    @Column(nullable = false)
    private String title;

    /**
     * 게시글 내용
     */
    @Column(nullable = false)
    private String contents;

    /**
     * 게시글 작성 유저
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 게시글 댓글
     */
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    /**
     * 게시글 좋아요 갯수
     */
    @Column(nullable = false)
    private int likesCount;

    private Board(CreateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();

    }

    public Board() {

    }

    /**
     * 게시글 생성 정적 메서드
     *
     * @param requestDto 게시글 생성 객체 dto
     * @return 생성된 게시글 객체
     */
    public static Board of(CreateRequestDto requestDto, User user) {
        Board board = new Board(requestDto);
        board.initUser(user);
        return board;
    }

    /**
     * 유저 주입 메서드
     *
     * @param user 게시글 작성 유저
     */
    private void initUser(User user) {
        this.user = user;
    }

    /**
     * 게시글 제목 수정 메서드
     *
     * @param requestDto 제목 수정 dto
     * @return 제목이 수정된 게시글 객체
     */
    public void updateTitle(UpdateRequestDto requestDto) {
        if (requestDto.getTitle() != null) {
            this.title = requestDto.getTitle();
        }
    }

    /**
     * 게시글 제목 수정 메서드
     *
     * @param requestDto 내용 수정 dto
     * @return 내용이 수정된 게시글 객체
     */
    public void updateContents(UpdateRequestDto requestDto) {
        if (requestDto.getContents() != null) {
            this.contents = requestDto.getContents();
        }
    }

    /**
     * 게시글 작성자와 수정하려는 유저가 같은지 확인하는 메서드
     *
     * @param userId 수정하려는 유저
     * @return 값을 비교 후 boolean 반환
     */
    public void isSameUser(Long userId) {
        if (!this.user.getId().equals(userId)) {
            throw new MisMatchUserException("작성자만 수정할 수 있습니다.");
        }
    }

    /**
     * 게시글 좋아요 증가
     */
    public void increaseLike() {
        likesCount++;
    }

    /**
     * 게시글 좋아요 감소
     */
    public void decreaseLike() {
        likesCount--;
    }

}






