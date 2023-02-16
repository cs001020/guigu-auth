package com.chen.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务认证计量
 *
 * @author CHEN
 * @date 2023/02/10
 */
@SpringBootApplication
@MapperScan("com.chen.system.mapper")
public class ServiceAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }
}
