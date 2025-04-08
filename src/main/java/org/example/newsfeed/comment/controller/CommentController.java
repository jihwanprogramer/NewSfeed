package org.example.newsfeed.comment.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.comment.dto.CommentReponseDto;
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
    public ResponseEntity<CommentReponseDto> save(
            @PathVariable Long post_id,
            @RequestBody CommentRequestDto commentRequestDto
    ){

    }

}
