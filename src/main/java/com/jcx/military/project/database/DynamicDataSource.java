//package com.jcx.military.project.database;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
//import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
//import javax.sql.DataSource;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//public class DynamicDataSource extends AbstractRoutingDataSource {
//
//    private Map<Object, Object> dynamicTargetDataSources;
//
//    /**
//     * 决定使用哪个数据源之前需要把多个数据源的信息以及默认数据源信息配置好
//     *
//     * @param defaultTargetDataSource 默认数据源
//     * @param targetDataSources       目标数据源
//     */
//    DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
//        setDefaultTargetDataSource(defaultTargetDataSource);
//        setTargetDataSources(targetDataSources);
//        this.dynamicTargetDataSources = targetDataSources;
//        super.afterPropertiesSet();
//    }
//
//    public DynamicDataSource() {
//    }
//
//    /**
//     * 如果不希望数据源在启动配置时就加载好，可以定制这个方法，从任何你希望的地方读取并返回数据源
//     * 比如从数据库、文件、外部接口等读取数据源信息，并最终返回一个DataSource实现类对象即可
//     */
//    @Override
//    protected DataSource determineTargetDataSource() {
//        return super.determineTargetDataSource();
//    }
//
//    /**
//     * 如果希望所有数据源在启动配置时就加载好，这里通过设置数据源Key值来切换数据，定制这个方法
//     *
//     * 实现数据源切换要扩展的方法，该方法的返回值就是项目中所要用的DataSource的key值，
//     * 拿到该key后就可以在resolvedDataSource中取出对应的DataSource，如果key找不到对应的DataSource就使用默认的数据源。
//     */
//    @Override
//    protected Object determineCurrentLookupKey() {
//        String dataSourceName = DynamicDataSourceContextHolder.getDataSourceKey();
//        if (!StringUtils.isEmpty(dataSourceName)) {
//            Map<Object, Object> dynamicTargetDataSources2 = this.dynamicTargetDataSources;
//            if (dynamicTargetDataSources2.containsKey(dataSourceName)) {
//                log.info("当前数据源为：" + dataSourceName);
//            } else {
//                log.info("不存在的数据源："+ dataSourceName);
//            }
//        } else {
//            log.info("当前数据源为：默认数据源");
//        }
//        return dataSourceName;
//    }
//
//    /**
//     * 设置默认数据源
//     * @param defaultDataSource Object
//     */
//    @Override
//    public void setDefaultTargetDataSource(Object defaultDataSource) {
//        super.setDefaultTargetDataSource(defaultDataSource);
//    }
//
//    /**
//     * 设置数据源
//     * @param dataSources Map<Object, Object>
//     */
//    @Override
//    public void setTargetDataSources(Map<Object, Object> dataSources) {
//
//        super.setTargetDataSources(dataSources);
//
//        this.dynamicTargetDataSources = dataSources;
//
//        // 将数据源的 key 放到数据源上下文的 key 集合中，用于切换时判断数据源是否有效
//        DynamicDataSourceContextHolder.addDataSourceKeys(dataSources.keySet());
//    }
//
//
//
//
//    public DataSource buildShardDatasources(DataSource dataSource,String type) {
//        // 配置多数据源
//        Map<String, DataSource> dsMap = new HashMap<>();
//        dsMap.put(type, dataSource);
//        //tableRuleConfiguration.setDatabaseShardingStrategyConfig();分库规则，要分库的自己加进去
//        //act_test_data表分片规则添加
//        /*TableRuleConfiguration manuHisTableRuleConfig = new TableRuleConfiguration("act_test_data", type+".act_test_data_$->{2020..2023}");
//        StandardShardingStrategyConfiguration manu_workorder = new StandardShardingStrategyConfiguration("testtime", new TableShardingAlgorithm());
//        manuHisTableRuleConfig.setTableShardingStrategyConfig(manu_workorder);
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        shardingRuleConfig.getTableRuleConfigs().add(manuHisTableRuleConfig);
//
//        //manu_order表分片规则添加
//        TableRuleConfiguration manuHisTableRuleConfig = new TableRuleConfiguration("manu_order", type+".manu_order_$->{2020..2023}");
//        StandardShardingStrategyConfiguration manu_workorder = new StandardShardingStrategyConfiguration("created_date", new TableShardingAlgorithm());
//        manuHisTableRuleConfig.setTableShardingStrategyConfig(manu_workorder);
//        shardingRuleConfig = new ShardingRuleConfiguration();*/
//        String [] tableNames="act_test_data,manu_workorder,manu_order,manu_workorder_list".split(",");
//        String [] columNames="testtime,integrate_time,created_date,create_time".split(",");
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        for(int i=0;i<4;i++){
//            TableRuleConfiguration tableRuleConfig = this.getTableRuleConfig(tableNames[i], columNames[i], type);
//            shardingRuleConfig.getTableRuleConfigs().add(tableRuleConfig);
//        }
//        //shardingRuleConfig.getTableRuleConfigs().add(manuHisTableRuleConfig);
//
//        try {
//            Properties properties = new Properties();
//            properties.setProperty("sql.show", "true");
//            DataSource dataSource1 = ShardingDataSourceFactory.createDataSource(dsMap, shardingRuleConfig, properties);
//            return dataSource1;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            throw new IllegalArgumentException();
//        }
//    }
//
//    /**
//     * 根据表名、分列名、租户标识,获取表的分片规则
//     * @param tableName
//     * @param columName
//     * @param type
//     * @return
//     */
//    private TableRuleConfiguration getTableRuleConfig(String tableName, String columName, String type){
//        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration(tableName, type+"."+tableName+"_$->{2020..2023}");
//        StandardShardingStrategyConfiguration shardingRuleConfig = new StandardShardingStrategyConfiguration(columName, new TableShardingAlgorithm());
//        tableRuleConfiguration.setTableShardingStrategyConfig(shardingRuleConfig);
//        return tableRuleConfiguration;
//    }
//
//}
