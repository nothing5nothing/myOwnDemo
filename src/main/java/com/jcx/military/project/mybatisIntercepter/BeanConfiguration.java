package com.jcx.military.project.mybatisIntercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeanConfiguration {

    @Bean(name = "snowflakeIdWorker")
    @Primary
    public  SnowflakeIdWorker snowflakeIdWorker() {
        return new SnowflakeIdWorker(1,1);
    }

}
