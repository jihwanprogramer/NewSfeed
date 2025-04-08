package org.example.newsfeed.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.comment.dto.CommentResponseDto;
import org.example.newsfeed.comment.dto.CommentSaveRequestDto;
import org.example.newsfeed.comment.dto.CommentUpdateRequestDto;
import org.example.newsfeed.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{post_id}/comments")
    public ResponseEntity<CommentResponseDto> saveComment(
            @SessionAttribute(name = "LOGIN_USER") Long userId,
            @PathVariable Long post_id,
            @RequestBody CommentSaveRequestDto commentSaveRequestDto
    ) {
        CommentResponseDto save = commentService.save(userId, post_id, commentSaveRequestDto);

        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/{post_id}/comments")
    public ResponseEntity<List<CommentResponseDto>> findByPostId(@PathVariable Long post_id) {
        List<CommentResponseDto> commentResponseDtoList = commentService.findByPost(post_id);

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    @PutMapping("/comments/{comment_id}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @SessionAttribute(name = "LOGIN_USER") Long userId,
            @PathVariable Long comment_id,
            @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {

        CommentResponseDto updateComment = commentService.updateComment(comment_id, userId, commentUpdateRequestDto);

        return new ResponseEntity<>(updateComment, HttpStatus.OK);

    }

    @DeleteMapping("/comments/{comment_id}")
    public ResponseEntity<Void> deleteComment(
            @SessionAttribute(name = "LOGIN_USER") Long userId,
            @PathVariable Long comment_id) {
        commentService.delete(comment_id, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
