package org.example.newsfeed.board.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class PeriodRequestDto {

    @Nullable
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\\\d|3[01])$", message = "잘못된 형식입니다")
    private final String startDate;

    @Nullable
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\\\d|3[01])$", message = "잘못된 형식입니다")
    private final String endDate;

    public PeriodRequestDto(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
