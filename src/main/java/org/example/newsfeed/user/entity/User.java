package org.example.newsfeed.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.newsfeed.common.config.PasswordEncoder;
import org.example.newsfeed.common.entity.BaseEntity;
import org.example.newsfeed.exception.MisMatchPasswordException;
import org.example.newsfeed.exception.WrongPasswordException;

@Entity
@Getter
@Setter
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

    protected User() {

    }

    public User(String name, Integer age, String email, String password) {
        this.name = name;
        this.age = age;
        this.email=email;
        this.password=password;
    }

    public static User of(String name, Integer age, String email, String encodedPassword) {
        return new User(name,age,email,encodedPassword);
    }

    public boolean isSameUser(Long id){
        return this.id.equals(id);
    }

}
