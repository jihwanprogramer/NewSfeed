package org.example.newsfeed.common.handler;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ErrorResponseDto {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String code;
    private String message;
    private String path;
    private List<GlobalFieldError> globalFieldErrors;

    public ErrorResponseDto() {
    }

    public ErrorResponseDto(
            LocalDateTime timestamp,
            int status, String error,
            String code,
            String message,
            String path,
            List<GlobalFieldError> globalFieldErrors) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.code = code;
        this.message = message;
        this.path = path;
        this.globalFieldErrors = globalFieldErrors;
    }
}

