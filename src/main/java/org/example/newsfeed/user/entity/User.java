package org.example.newsfeed.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.newsfeed.common.config.PasswordEncoder;
import org.example.newsfeed.common.entity.BaseEntity;
import org.example.newsfeed.exception.MisMatchUserException;
import org.example.newsfeed.exception.WrongPasswordException;

/**
 * 사용자(User) 정보를 나타내는 엔티티 클래스입니다.
 * 기본 엔티티 필드 외에 사용자 인증 및 권한 체크 기능도 포함되어 있습니다.
 */
@Entity
@Getter
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer age;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    /**
     * JPA 기본 생성자. 외부에서 직접 호출하지 않도록 protected로 선언.
     */
    protected User() {
    }

    /**
     * User 생성자입니다.
     *
     * @param name 사용자 이름
     * @param age 사용자 나이
     * @param email 사용자 이메일
     * @param password 인코딩된 비밀번호
     */
    public User(String name, Integer age, String email, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    /**
     * User 객체 생성 팩토리 메서드입니다.
     *
     * @param name 사용자 이름
     * @param age 사용자 나이
     * @param email 사용자 이메일
     * @param encodedPassword 인코딩된 비밀번호
     * @return 생성된 User 객체
     */
    public static User of(String name, Integer age, String email, String encodedPassword) {
        return new User(name, age, email, encodedPassword);
    }

    /**
     * 비밀번호 일치 여부를 검사합니다.
     *
     * @param password 입력된 평문 비밀번호
     * @param passwordEncoder 비밀번호 인코더
     * @throws WrongPasswordException 비밀번호가 일치하지 않을 경우
     */
    public void passwordMatch(String password, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(password, this.password)) {
            throw new WrongPasswordException("비밀번호가 일치하지 않습니다.");
        }
    }

    /**
     * 사용자 정보 업데이트 메서드입니다.
     * 전달된 값이 null이 아닌 경우에만 해당 필드를 업데이트합니다.
     * 비밀번호 변경 시 기존 비밀번호 일치 여부를 확인하고, 새 비밀번호와 확인 값이 모두 존재할 경우에만 변경합니다.
     *
     * @param name 변경할 이름
     * @param age 변경할 나이
     * @param email 변경할 이메일
     * @param password 기존 비밀번호
     * @param newPassword 새로운 비밀번호
     * @param checkNewPassword 새로운 비밀번호 확인
     * @param passwordEncoder 비밀번호 인코더
     */
    public void notNullUpdate(String name, Integer age, String email, String password, String newPassword,
                              String checkNewPassword, PasswordEncoder passwordEncoder) {

        if (name != null) {
            this.name = name;
        }

        if (age != null) {
            this.age = age;
        }

        // null은 허용, 빈 문자열은 허용하지 않음
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }

        // 기존 비밀번호가 맞는지 확인
        passwordMatch(password, passwordEncoder);

        // 새 비밀번호와 확인 값이 모두 존재하면 변경
        if (newPassword != null && checkNewPassword != null) {
            this.password = passwordEncoder.encode(newPassword);
        }
    }

    /**
     * 로그인한 사용자 ID와 현재 사용자 ID가 같은지 검사합니다.
     *
     * @param loginUserId 로그인한 사용자 ID
     * @throws MisMatchUserException ID가 다를 경우 권한 없음 예외 발생
     */
    public void sessionCheck(Long loginUserId) {
        if (!isSameUser(loginUserId)) {
            throw new MisMatchUserException("권한이 없습니다.");
        }
    }

    /**
     * 사용자 ID가 같은지 비교합니다.
     *
     * @param id 비교할 사용자 ID
     * @return 같으면 true, 다르면 false
     */
    public boolean isSameUser(Long id) {
        return this.id.equals(id);
    }
}