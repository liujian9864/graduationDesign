package com.ecut.frozenpearassistant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ecut.forzenpearassistant.orm.mapper")
public class FrozenpearassistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrozenpearassistantApplication.class, args);
    }

}
