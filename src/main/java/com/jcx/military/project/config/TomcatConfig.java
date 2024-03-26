package com.jcx.military.project.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
public class TomcatConfig {

    @Value("${spring.http.server.maxFileSize:30}")
    private Long maxFileSize;
    @Value("${spring.http.server.maxRequestSize:30}")
    private Long maxRequestSize;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个数据大小
        // factory.setMaxFileSize(MaxFileSize); // KB,MB
        factory.setMaxFileSize(DataSize.ofMegabytes(maxFileSize));
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(maxRequestSize));
        // factory.setMaxRequestSize(MaxRequestSize);
        return factory.createMultipartConfig();
    }

}
