package org.wolf.security4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.wolf.security4.*.mapper")
@SpringBootApplication
public class Security4Application {

    public static void main(String[] args) {
        SpringApplication.run(Security4Application.class, args);
    }

}
