package org.example.newsfeed.common.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    /**
     * 주어진 비밀번호를 해싱하여 인코딩합니다.
     *
     * @param rawPassword 인코딩할 원본 비밀번호
     * @return 해싱된 비밀번호
     */
    public String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    /**
     * 원본 비밀번호와 인코딩된 비밀번호를 비교하여 일치 여부를 확인합니다.
     *
     * @param rawPassword 원본 비밀번호
     * @param encodedPassword 인코딩된 비밀번호
     * @return 비밀번호가 일치하면 true, 그렇지 않으면 false
     */
    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
}
