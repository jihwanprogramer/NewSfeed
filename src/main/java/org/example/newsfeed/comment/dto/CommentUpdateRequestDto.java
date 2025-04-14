package org.example.newsfeed.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentUpdateRequestDto {

    @NotBlank(message = "내용을 입력하세요")
    private String content;

    /**
     * 댓글 수정 요청 DTO의 생성자입니다.
     *
     * @param content 수정할 댓글 내용
     */
}
