///*
//package org.example.newsfeed.comment.controller;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.example.newsfeed.comment.dto.CommentPageResponseDto;
//import org.example.newsfeed.comment.dto.CommentResponseDto;
//import org.example.newsfeed.comment.dto.CommentSaveRequestDto;
//import org.example.newsfeed.comment.dto.CommentUpdateRequestDto;
//import org.example.newsfeed.comment.service.CommentService;
//import org.example.newsfeed.user.dto.UserResponseDto;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/boards")
//@RequiredArgsConstructor
//public class CommentController {
//
//    private final CommentService commentService;
//
//    @PostMapping("/{board_id}/comments")
//    public ResponseEntity<CommentResponseDto> saveComment(
//            @SessionAttribute(name = "loginUser") UserResponseDto loginUser,
//            @PathVariable Long board_id,
//            @Valid @RequestBody CommentSaveRequestDto commentSaveRequestDto
//    ) {
//        CommentResponseDto save = commentService.save(loginUser.getId(), board_id, commentSaveRequestDto);
//
//        return new ResponseEntity<>(save, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{board_id}/comments")
//    public ResponseEntity<List<CommentResponseDto>> findByPostId(@PathVariable Long board_id) {
//        List<CommentResponseDto> commentResponseDtoList = commentService.findByPost(board_id);
//
//        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
//    }
//
//    @PutMapping("/comments/{comment_id}")
//    public ResponseEntity<CommentResponseDto> updateComment(
//            @SessionAttribute(name = "loginUser") UserResponseDto loginUser,
//            @PathVariable Long comment_id,
//            @Valid @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
//
//        CommentResponseDto updateComment = commentService.updateComment(comment_id, loginUser.getId(), commentUpdateRequestDto);
//
//        return new ResponseEntity<>(updateComment, HttpStatus.OK);
//
//    }
//
//    @DeleteMapping("/comments/{comment_id}")
//    public ResponseEntity<Void> deleteComment(
//            @SessionAttribute(name = "loginUser") UserResponseDto loginUser,
//            @PathVariable Long comment_id) {
//        commentService.delete(comment_id, loginUser.getId());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping("/comments/pages")
//    public ResponseEntity<Page<CommentPageResponseDto>> findAllPage(
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "10") int size
//    ){
//        Page<CommentPageResponseDto> result = commentService.findAllPage(page,size);
//        return new ResponseEntity<>(result,HttpStatus.OK);
//    }
//
//}
//*/
