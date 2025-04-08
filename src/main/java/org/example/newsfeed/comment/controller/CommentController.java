package org.example.newsfeed.comment.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.comment.dto.CommentResponseDto;
import org.example.newsfeed.comment.dto.CommentRequestDto;
import org.example.newsfeed.comment.dto.CommentSaveRequestDto;
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
    public ResponseEntity<CommentResponseDto> save(
            @PathVariable Long post_id,
            @RequestBody CommentSaveRequestDto commentSaveRequestDto
    ){
        CommentResponseDto save = commentService.save(post_id,commentSaveRequestDto);

        return new ResponseEntity<>(save,HttpStatus.CREATED);
    }

    @GetMapping("/{post_id}/comments")
    public ResponseEntity<List<CommentResponseDto>> findAll(@PathVariable Long id){
        List<CommentResponseDto> commentResponseDtoList = commentService.findByPost(id);

        return new ResponseEntity<>(commentResponseDtoList,HttpStatus.OK);
    }

}
