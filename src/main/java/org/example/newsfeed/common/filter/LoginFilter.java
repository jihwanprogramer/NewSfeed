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

        //특정URI에 비로그인 사용자 접근 금지
        if(!isWhiteList(requestURI)){

            if (session==null||session.getAttribute(Const.LOGIN_USER)==null){
                throw new LoginAuthException("로그인 해주세요");
            }

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean isWhiteList(String requestURI){
        return PatternMatchUtils.simpleMatch(Const.WHITE_LIST, requestURI);
    }


}
