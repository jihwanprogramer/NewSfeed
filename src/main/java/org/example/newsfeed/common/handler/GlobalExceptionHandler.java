package org.example.newsfeed.common.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.example.newsfeed.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
/**
 * 애플리케이션 전역에서 발생하는 예외를 처리하는 핸들러입니다.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 유효성 검사 실패 시 발생하는 예외를 처리합니다.
     *
     * @param ex MethodArgumentNotValidException 예외
     * @param request 현재 요청 정보
     * @return 에러 응답 DTO
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        List<GlobalFieldError> globalFieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new GlobalFieldError(error.getField(), error.getDefaultMessage()))
                .toList();

        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .code("V001")
                .message("잘못된 입력값입니다")
                .path(request.getRequestURI())
                .globalFieldErrors(globalFieldErrors)
                .build();

        return ResponseEntity.badRequest().body(errorResponseDto);
    }

    /**
     * 로그인 인증 관련 예외를 처리합니다.
     *
     * @param ex LoginAuthException 예외
     * @param request 현재 요청 정보
     * @return 에러 응답 DTO
     */
    @ExceptionHandler(LoginAuthException.class)
    public ResponseEntity<ErrorResponseDto> handleLoginAuthException(
            LoginAuthException ex,
            HttpServletRequest request
    ) {
        ErrorResponseDto error = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .code("A001")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    /**
     * 응답이 null일 경우 발생하는 예외를 처리합니다.
     *
     * @param ex NullResponseException 예외
     * @param request 현재 요청 정보
     * @return 에러 응답 DTO
     */
    @ExceptionHandler(NullResponseException.class)
    public ResponseEntity<ErrorResponseDto> handleNullResponseException(
            NullResponseException ex,
            HttpServletRequest request
    ) {
        ErrorResponseDto error = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .code("B001")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * 비밀번호 불일치 시 발생하는 예외를 처리합니다.
     *
     * @param ex MisMatchPasswordException 예외
     * @param request 현재 요청 정보
     * @return 에러 응답 DTO
     */
    @ExceptionHandler(MisMatchPasswordException.class)
    public ResponseEntity<ErrorResponseDto> handleMisMatchPasswordException(
            MisMatchPasswordException ex,
            HttpServletRequest request
    ) {
        ErrorResponseDto error = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                .code("F001")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    /**
     * 중복된 리소스가 존재할 경우 발생하는 예외를 처리합니다.
     *
     * @param ex AlreadyExistsException 예외
     * @param request 현재 요청 정보
     * @return 에러 응답 DTO
     */
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleAlreadyExistsException(
            AlreadyExistsException ex,
            HttpServletRequest request
    ) {
        ErrorResponseDto error = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .code("C001")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    /**
     * 접근 권한이 없을 때 발생하는 예외를 처리합니다.
     *
     * @param ex AccessDeniedException 예외
     * @param request 현재 요청 정보
     * @return 에러 응답 DTO
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> handleAccessDeniedException(
            AccessDeniedException ex,
            HttpServletRequest request
    ) {
        ErrorResponseDto error = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                .code("F002")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    /**
     * 자신을 팔로우하려고 할 때 발생하는 예외를 처리합니다.
     *
     * @param ex SelfFollowNotAllowedException 예외
     * @param request 현재 요청 정보
     * @return 에러 응답 DTO
     */
    @ExceptionHandler(SelfFollowNotAllowedException.class)
    public ResponseEntity<ErrorResponseDto> handleSelfFollowNotAllowedException(
            SelfFollowNotAllowedException ex,
            HttpServletRequest request
    ) {
        ErrorResponseDto error = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .code("B002")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * 사용자 불일치 시 발생하는 예외를 처리합니다.
     *
     * @param ex MisMatchUserException 예외
     * @param request 현재 요청 정보
     * @return 에러 응답 DTO
     */
    @ExceptionHandler(MisMatchUserException.class)
    public ResponseEntity<ErrorResponseDto> handleMisMatchUserException(
            MisMatchUserException ex,
            HttpServletRequest request
    ) {
        ErrorResponseDto error = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                .code("F003")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    /**
     * 잘못된 비밀번호 입력 시 발생하는 예외를 처리합니다.
     *
     * @param ex WrongPasswordException 예외
     * @param request 현재 요청 정보
     * @return 에러 응답 DTO
     */
    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorResponseDto> handleWrongPasswordException(
            WrongPasswordException ex,
            HttpServletRequest request
    ) {
        ErrorResponseDto error = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                .code("F004")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
}
