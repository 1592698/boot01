package com.example.boot01;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@Log4j2
public class DataSourceTests {
    //@Autowired : 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
    //생성자, setter, 필드
    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException{
        //@Cleanup : try-with-resource 구문과 비슷한 효과를 가진다.
        // 구문이 종료될 때 AutoCloseable 인터페이스의 close()가 호출되는
        // try-with-resource와 달리 Scope가 종료될 때 close()가 호출된다.

        @Cleanup Connection connection = dataSource.getConnection();

        log.info("conn : " + connection);
        Assertions.assertNotNull(connection);
    }
}
