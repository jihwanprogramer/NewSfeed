package org.example.newsfeed.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.comment.dto.CommentResponseDto;
import org.example.newsfeed.comment.dto.CommentRequestDto;
import org.example.newsfeed.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping{"/{post_id}/comments"}
    public ResponseEntity<CommentResponseDto> save(
            @PathVariable Long post_id,
            @RequestBody CommentRequestDto commentRequestDto
    ){
        CommentResponseDto save = commentService.save(commentRequestDto.)

        return ResponseEntity<>()
    }

}
