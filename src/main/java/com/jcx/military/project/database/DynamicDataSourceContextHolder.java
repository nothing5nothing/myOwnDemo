package com.jcx.military.project.database;


import org.springframework.context.annotation.Configuration;


import java.sql.SQLException;
import java.util.ArrayList;

@Configuration
public class DynamicDataSourceContextHolder {
    //加在动态数据源
//    @Resource
//    private DynamicDataSource dynamicDataSource;
//
//
//    @PostConstruct
//    public void initDataSource() throws SQLException {
//
//        for (TenantInfo tenantInfo : tenantList) {
//            DruidDataSource dataSource = new DruidDataSource();
//            dataSource.setDriverClassName(tenantInfo.getDataSourceDriver());
//            dataSource.setUrl(tenantInfo.getDataSourceUrl());
//            dataSource.setUsername(tenantInfo.getDataSourceUsername());
//            dataSource.setPassword(tenantInfo.getDataSourcePassword());
//            dataSource.init();
//            dynamicDataSource.setDataSources(tenantInfo);
//        }
//    }

}
