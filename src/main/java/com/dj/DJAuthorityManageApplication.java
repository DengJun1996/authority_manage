package com.dj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.dj.system.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DJAuthorityManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(DJAuthorityManageApplication.class);
    }
}
