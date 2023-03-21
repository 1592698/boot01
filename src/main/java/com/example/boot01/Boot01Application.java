package com.example.boot01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//AuditingEntityListener를 활성화 시키기 위해서는 프로젝터 설정에 @EnableJpaAuditing을 추가해 주어야 함.
public class Boot01Application {

    public static void main(String[] args) {
        SpringApplication.run(Boot01Application.class, args);
    }

}


//아니 근데 진짜 아니 근데 진짜로
        //장난 아냐 얼마나 장난 아니냐면 진짜 장난 아니야 ,,
//자판 너무 어렵네 .. ㅎ,,,,ㅎ,.... 지금 타자 50 정도일 듯,,,