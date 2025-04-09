package org.example.newsfeed.post.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.example.newsfeed.user.entity.Users;

@Getter
public class CreateRequestDto {

    @NotNull(message = "제목을 입력해주세요.")
    private final String title;

    @NotNull(message = "내용을 입력해주세요.")
    private final String contents;

    public CreateRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
