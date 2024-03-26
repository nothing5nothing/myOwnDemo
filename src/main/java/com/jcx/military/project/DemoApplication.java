package com.jcx.military.project;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableApolloConfig
//@MapperScan(basePackages = "com.jcx.military.project.mapper")
@EnableFeignClients
@EnableAsync
//@EnableTransactionManagement
//@EnableTask
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
