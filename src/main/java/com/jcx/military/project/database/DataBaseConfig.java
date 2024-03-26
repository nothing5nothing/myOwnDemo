//package com.jcx.military.project.database;
//
//
//
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.FactoryBean;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.*;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.*;
//
//@Configuration
//@ComponentScan(basePackageClasses = DataBaseConfig.class)
//public class DataBaseConfig {
//
//    Logger log = LoggerFactory.getLogger(DataBaseConfig.class);
//
//    @Bean
//    @Primary
////    @DependsOn("dataSource")
//    public SqlSessionFactory getSqlSessionFactory() throws Exception {
//        log.error("111");
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        // 创建spring的sqlSessionFactoryBean
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        //添加数据源
//        sqlSessionFactoryBean.setDataSource(dataSource());
//        // 添加配置
//        sqlSessionFactoryBean.setFailFast(true);
//        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));
//        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*Mapper.xml"));
//        return sqlSessionFactoryBean.getObject();
//    }
//
//
//    @Bean
//    @Primary
//    public PlatformTransactionManager transactionManager(@Qualifier("dataSource")DataSource dataSource) throws SQLException {
//        log.error("222");
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
//        transactionManager.setDataSource(dataSource());
//        return transactionManager;
//    }
//
//    @Bean("dataSource")
//    @Primary
//    public DataSource dataSource() throws SQLException {
//        log.error("444");
//        return DataSourceUtil.createDataSource("disk");
//    }
////
////    @Bean
////    public DataSource dataSource() throws SQLException {
////        ShardingRuleConfiguration shardingReluConfiguration = new ShardingRuleConfiguration();
////        List<TableRuleConfiguration> TableRuleList = getTableRuleConfigList();
////        shardingReluConfiguration.getTableRuleConfigs().addAll(TableRuleList);
////
////        // 给啥认定JDBC设置展示sql属性
////        Properties property = new Properties();
////        property.setProperty("sql.show","true");
////        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingReluConfiguration, property);
////    }
//
//
//    // 分表配置
////    private List<TableRuleConfiguration> getTableRuleConfigList() {
////
////        List<TableRuleConfiguration> list = new ArrayList<>();
////        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration("fs_object", "disk"+"."+"fs_object"+"_$->{0..1}");
////        StandardShardingStrategyConfiguration shardingRuleConfig = new StandardShardingStrategyConfiguration("fs_object", new PreciseModuloShardingTableAlgorithm());
////        tableRuleConfiguration.setTableShardingStrategyConfig(shardingRuleConfig);
////        list.add(tableRuleConfiguration);
////
////        tableRuleConfiguration = new TableRuleConfiguration("fs_sys_property", "disk"+"."+"fs_sys_property"+"_$->{0..1}");
////        tableRuleConfiguration.setTableShardingStrategyConfig(
////                new StandardShardingStrategyConfiguration("fs_sys_property", new PreciseModuloShardingTableAlgorithm()));
////
////        tableRuleConfiguration = new TableRuleConfiguration("e_tag", "disk"+"."+"etag"+"_$->{0..1}");
////        tableRuleConfiguration.setTableShardingStrategyConfig(
////                new StandardShardingStrategyConfiguration("e_tag", new PreciseModuloShardingTableAlgorithm()));
////        list.add(tableRuleConfiguration);
////
////        return list;
////    }
//
//
//
//    Map<String, DataSource> createDataSourceMap() {
//        Map<String, DataSource> result = new HashMap<>();
////        result.put("db0", DataSourceUtil.createDataSource("disk_read"));
//        result.put("db", DataSourceUtil.createDataSource("disk"));
//        return result;
//    }
//
//}
//
