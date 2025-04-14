package org.example.newsfeed.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.newsfeed.common.Const;
import org.example.newsfeed.exception.LoginAuthException;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {

    /**
     * 요청을 필터링하여 비로그인 사용자의 접근을 제한합니다.
     *
     * @param servletRequest 요청
     * @param servletResponse 응답
     * @param filterChain 필터 체인
     * @throws IOException 입출력 예외
     * @throws ServletException 서블릿 예외
     */
    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpServletRequest.getSession(false);

        if (!isWhiteList(requestURI)) {
            if (session == null || session.getAttribute(Const.LOGIN_USER) == null) {
                throw new LoginAuthException("로그인 해주세요");
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * 요청 URI가 화이트리스트에 있는지 확인합니다.
     *
     * @param requestURI 요청 URI
     * @return 화이트리스트에 있으면 true, 아니면 false
     */
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(Const.WHITE_LIST, requestURI);
    }
}
