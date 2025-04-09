package org.example.newsfeed.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateRequestDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private final String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private final String contents;

    public CreateRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
