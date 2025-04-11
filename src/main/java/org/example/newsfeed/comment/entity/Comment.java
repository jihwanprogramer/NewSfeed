package org.example.newsfeed.comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.newsfeed.common.entity.BaseEntity;
import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.like.entity.CommentLike;
import org.example.newsfeed.user.entity.User;

import java.util.ArrayList;
import java.util.List;
/**
 * 게시판 댓글 정보를 나타내는 엔티티입니다.
 */
@Getter
@Entity
public class Comment extends BaseEntity {

    /**
     * 댓글 식별자.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 댓글 내용.
     */
    @Column(nullable = false, columnDefinition = "longtext")
    private String content;

    /**
     * 댓글이 속한 게시판.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    /**
     * 댓글 작성자.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 댓글에 대한 좋아요 리스트.
     */
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private final List<CommentLike> commentLike = new ArrayList<>();

    /**
     * 댓글의 내용만을 받아 생성하는 생성자입니다.
     *
     * @param content 댓글 내용
     */
    private Comment(String content) {
        this.content = content;
    }

    /**
     * JPA 기본 생성자입니다.
     */
    public Comment() {}

    /**
     * 게시판을 초기화합니다.
     *
     * @param board 댓글이 속할 게시판
     */
    private void initBoard(Board board) {
        this.board = board;
    }

    /**
     * 작성자 정보를 초기화합니다.
     *
     * @param user 댓글 작성자
     */
    private void initUser(User user) {
        this.user = user;
    }

    /**
     * 댓글 객체를 생성하는 정적 팩토리 메서드입니다.
     *
     * @param board 댓글이 속하는 게시판
     * @param user 댓글 작성자
     * @param content 댓글 내용
     * @return 생성된 댓글 객체
     */
    public static Comment createComment(Board board, User user, String content) {
        Comment comment = new Comment(content);
        comment.initBoard(board);
        comment.initUser(user);
        return comment;
    }

    /**
     * 댓글 내용을 수정합니다.
     *
     * @param content 수정할 댓글 내용
     */
    public void updateContent(String content) {
        this.content = content;
    }
}
