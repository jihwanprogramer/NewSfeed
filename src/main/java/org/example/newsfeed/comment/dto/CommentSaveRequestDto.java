package org.example.newsfeed.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentSaveRequestDto {

    @NotBlank(message = "내용을 입력하세요")
    private String content;

    /**
     * 댓글 저장 요청 DTO의 생성자입니다.
     *
     * @param content 댓글 내용
     */
}
