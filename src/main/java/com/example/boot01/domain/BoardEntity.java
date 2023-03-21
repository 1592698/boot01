package com.example.boot01.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//엔티티 객체를 위한 엔티티 클래스는 반드시 @Entity를 적용해야 하고 @Id가 필요
//게시물은 데이터베이스에 추가 될 때 생성되는 번호 auto increment를 이용할 것이므로
//이런 경우이 키 생성 전략 key generate strategy 중에 GenerationType.IDENTITY로
//데이터베이스에서 알아서 결정하는 방식을 이용
@Entity
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String title;
    private String content;
    private String writer;
}
