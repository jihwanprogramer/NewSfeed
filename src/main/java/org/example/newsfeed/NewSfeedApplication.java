package org.example.newsfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaAuditing
public class NewSfeedApplication {
     //테스트코드
    public static void main(String[] args) {
        SpringApplication.run(NewSfeedApplication.class, args);
    }

}
