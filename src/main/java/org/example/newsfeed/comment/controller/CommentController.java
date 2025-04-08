package org.example.newsfeed.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.comment.dto.CommentResponseDto;
import org.example.newsfeed.comment.dto.CommentRequestDto;
import org.example.newsfeed.comment.dto.CommentSaveRequestDto;
import org.example.newsfeed.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{post_id}/comments")
    public ResponseEntity<CommentResponseDto> save(
            @PathVariable Long post_id,
            @RequestBody CommentSaveRequestDto commentSaveRequestDto
    ){
        CommentResponseDto save = commentService.save(post_id,commentSaveRequestDto);

        return new ResponseEntity<>(save,HttpStatus.CREATED);
    }

}
