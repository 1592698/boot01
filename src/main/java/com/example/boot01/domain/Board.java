package com.example.boot01.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length=500, nullable=false) //컬럼의 길이와 null 허용 여부
    private String title;

    @Column(length=2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

   /* 3) update 기능 테스트
    update 기능은 insert와 동일하게 save()를 통해서 처리.
    동일한 @ld 값을 가지는 개체를 생성해서 처리가능.
    update는 등록 시간이 필요하므로 가능하면 findById()로 가져온 객체를 이용하여 약간의 수정을 통해서 처라하도록 함.
    일반적으로 엔티티 객체는 가능하면 최소한의 변경이나 변경이 없는 불변 immutable하게 설계하는 것이 좋지만,
    강제적인 사항은 아니므로 Board 클래스에 수정이 가능한 부분을 미리 메소드로 설계.
    Board의 경우에는 '제목/내용'은 수정이 가능하므로 이에 맞도록 change() 라는 메서드를 추가
    */

    public void change(String title, String content) {
        this.title= title;
        this.content=content;
    }
}
