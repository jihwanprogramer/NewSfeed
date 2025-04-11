package org.example.newsfeed.common.handler;

import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * 유효성 검사 실패 시 발생한 필드 에러 정보를 담는 DTO입니다.
 */
@Getter
@NoArgsConstructor
public class GlobalFieldError {

    /**
     * 오류가 발생한 필드 이름.
     */
    private String field;

    /**
     * 필드에 대한 에러 메시지.
     */
    private String message;

    /**
     * 필드명과 에러 메시지를 포함하는 생성자입니다.
     *
     * @param field 오류가 발생한 필드명
     * @param message 필드 오류 메시지
     */
    public GlobalFieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
