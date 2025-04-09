package org.example.newsfeed.like.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 상속받은 entity에 공통 매핑 제공
@EntityListeners(AuditingEntityListener.class) //entity를 DB에 적용하기 전, 커스텀 롤백
public class BaseEntity {

    @CreatedDate //생성 시점 자동 기록
    @Column(updatable = false) // 생성일은 최초등록만 하면 되기 때문에 최신화 불필요
    private LocalDateTime createdAt;

    @LastModifiedDate //수정 시점 자동 기록
    private LocalDateTime modifiedAt;

}
