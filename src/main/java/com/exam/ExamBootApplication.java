package com.exam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * SpringBoot启动类
 * @author
 */
@MapperScan("com.exam.mapper")
@EnableCaching
@EnableScheduling
@EnableTransactionManagement
@CrossOrigin
@SpringBootApplication
@PropertySource({"classpath:resource.properties"})
public class ExamBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamBootApplication.class, args);
    }

}
