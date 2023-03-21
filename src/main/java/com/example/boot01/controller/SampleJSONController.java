package com.example.boot01.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class SampleJSONController {

    /*브라우저에 helloArr경로를 호출하면 배열이 그대로 출력
    * 중요한 점은 서버에서 해당 데이터는 application/json이라는것을 전송*/
    @GetMapping("/helloArr")
    public String[] helloARR() {
        log.info("HelloArr...");

        return new String[] {"AAA", "BBB", "CCC"};
    }
}
