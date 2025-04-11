package org.example.newsfeed.common;
/**
 * 애플리케이션 전역에서 사용되는 상수를 정의하는 인터페이스입니다.
 */
public interface Const {

    /**
     * 세션에 저장된 로그인 사용자 키.
     */
    String LOGIN_USER = "loginUser";

    /**
     * 인증이 필요 없는 화이트리스트 경로 목록입니다.
     */
    String[] WHITE_LIST = {
            "/",
            "/login",
            "/users/signup"
    };
}
