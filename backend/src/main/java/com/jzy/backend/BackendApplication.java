package com.jzy.backend;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jzy
 */
@SpringBootApplication
@Slf4j
@MapperScan("com.jzy.backend.DAO")
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        System.out.println("http://localhost:8080/doc.html#/");
    }

}
