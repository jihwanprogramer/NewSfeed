package org.example.newsfeed.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.comment.dto.CommentResponseDto;
import org.example.newsfeed.comment.dto.CommentSaveRequestDto;
import org.example.newsfeed.comment.dto.CommentUpdateRequestDto;
import org.example.newsfeed.comment.service.CommentService;
import org.example.newsfeed.user.dto.UserResponseDto;
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
            @SessionAttribute(name = "loginUser") UserResponseDto loginUser,
            @PathVariable Long post_id,
            @Valid @RequestBody CommentSaveRequestDto commentSaveRequestDto
    ) {
        CommentResponseDto save = commentService.save(loginUser.getId(), post_id, commentSaveRequestDto);

        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/{post_id}/comments")
    public ResponseEntity<List<CommentResponseDto>> findByPostId(@PathVariable Long post_id) {
        List<CommentResponseDto> commentResponseDtoList = commentService.findByPost(post_id);

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    @PutMapping("/comments/{comment_id}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @SessionAttribute(name = "loginUser") UserResponseDto loginUser,
            @PathVariable Long comment_id,
            @Valid @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {

        CommentResponseDto updateComment = commentService.updateComment(comment_id, loginUser.getId(), commentUpdateRequestDto);

        return new ResponseEntity<>(updateComment, HttpStatus.OK);

    }

    @DeleteMapping("/comments/{comment_id}")
    public ResponseEntity<Void> deleteComment(
            @SessionAttribute(name = "loginUser") UserResponseDto loginUser,
            @PathVariable Long comment_id) {
        commentService.delete(comment_id, loginUser.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
