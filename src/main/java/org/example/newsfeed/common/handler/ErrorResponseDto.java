package org.example.newsfeed.common.handler;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;
/**
 * 예외 발생 시 클라이언트에게 반환되는 에러 응답 DTO입니다.
 */
@Getter
@Builder
@NoArgsConstructor
public class ErrorResponseDto {

    /**
     * 에러가 발생한 시간.
     */
    private LocalDateTime timestamp;

    /**
     * HTTP 상태 코드.
     */
    private int status;

    /**
     * HTTP 상태 텍스트 (예: BAD_REQUEST, UNAUTHORIZED 등).
     */
    private String error;

    /**
     * 애플리케이션에서 정의한 에러 코드.
     */
    private String code;

    /**
     * 에러 메시지.
     */
    private String message;

    /**
     * 요청 경로 (URI).
     */
    private String path;

    /**
     * 유효성 검사 에러 목록.
     */
    private List<GlobalFieldError> globalFieldErrors;

    /**
     * 전체 필드를 초기화하는 생성자입니다.
     *
     * @param timestamp 에러 발생 시간
     * @param status HTTP 상태 코드
     * @param error HTTP 상태 메시지
     * @param code 커스텀 에러 코드
     * @param message 에러 메시지
     * @param path 요청 URI
     * @param globalFieldErrors 필드 유효성 검사 에러 목록
     */
    public ErrorResponseDto(
            LocalDateTime timestamp,
            int status,
            String error,
            String code,
            String message,
            String path,
            List<GlobalFieldError> globalFieldErrors
    ) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.code = code;
        this.message = message;
        this.path = path;
        this.globalFieldErrors = globalFieldErrors;
    }
}
