package org.example.newsfeed.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentSaveRequestDto {

    @NotBlank(message = "내용을 입력하세요")
    private String content;

}
