package com.example.boot01.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value={AuditingEntityListener.class})
@Getter

/*
* @MappedSuperclass를 이용한 공통 속성 처리
* 데이터베이스의 거의 모든 테이블에는 데이터가 추가된 시간이나 수정된 시간 등이 칼럼을 작성
* 자바에서는 이를 쉽게 처리하고자 @MappedSuperclass를 이용해서 공통으로 사용되는
* 칼럼을 지정하고 해당 클래스를 상속해서 이를 손쉽게 처리*/

//BaseEntity에서 가장 중요한 부분은 자동으로 Spring Data JPA의 AuditingEntityListener를 지정하는 부분


public class BaseEntity {
    @CreatedDate
    @Column(name="regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name="moddate")
    private LocalDateTime modDate;
}
