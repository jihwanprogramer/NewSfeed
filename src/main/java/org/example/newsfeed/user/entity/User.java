package org.example.newsfeed.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.newsfeed.common.config.PasswordEncoder;
import org.example.newsfeed.exception.WrongPasswordException;

@Entity
@Getter
@Table(name = "users")
public class User extends BaseEntity{

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

    protected User() {

    }
    public User(String name, Integer age, String email, String password) {
        this.name = name;
        this.age = age;
        this.email=email;
        this.password=password;
    }

    public void passwordMatch(String password, PasswordEncoder passwordEncoder) {

        if (!passwordEncoder.matches(password,this.password)) {
            throw new WrongPasswordException("비밀번호가 일치하지 않습니다.");
        }

    }


    public void notNullUpdate(String name, Integer age, String email, String password, String newPassword,
                              String checkNewPassword, PasswordEncoder passwordEncoder) {

        if (name != null) {
            this.name = name;
        }

        if (age != null) {
            this.age = age;
        }

        // 널은 허용, 빈 문자열은 허용X
        if (email != null && !email.isEmpty()) {
            this.email=email;
        }

        // 둘 다 넣어야 변경
        if (newPassword != null && checkNewPassword != null) {

            passwordMatch(password, passwordEncoder);

            this.password = passwordEncoder.encode(newPassword);

        }

    }

    public boolean isSameUser(Long id){
        return this.id.equals(id);
    }

}
