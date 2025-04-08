package org.example.newsfeed.common.config;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.Const;
import org.example.newsfeed.exception.MisMatchUserException;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConfirmSameUser {

    private final HttpSession session;

    public void confirmSameUser(long id){

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        if(id!=loginUser.getId()){
            throw new MisMatchUserException("작성자가 아닙니다.");
        }
    }
}
