package com.exam.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class GeneratorTest {

    /**
     * 代码生成实例
     */
    @Test
    public void testGenerator() {
        // 1.全局配置
        GlobalConfig config = new GlobalConfig();
        // 是否支持AR模式
        config.setActiveRecord(false)
                // 设置作者
                .setAuthor("杨德石")
                // 生成路径，生成到java路径位置
                .setOutputDir("D:\\MyProject\\exam_boot\\src\\main\\java")
                // 多次生成是否文件覆盖
                .setFileOverride(false)
                // 设置主键策略
                .setIdType(IdType.AUTO)
                // 设置生成的service接口的名字的首字母是否为I，默认有
                .setServiceName("%sService")
                // 生成SQL映射文件
                .setBaseResultMap(true)
                // 生成基本SQL片段
                .setBaseColumnList(true)
                .setEntityName("%sDO");
        // 2.数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // 设置数据库类型
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/exam_boot?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8")
                .setUsername("root")
                .setPassword("yangdeshi");
        // 3.策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // 开启全局大写
        strategyConfig.setCapitalMode(true)
                // 设置数据库表映射到实体时下划线转驼峰策略
                .setNaming(NamingStrategy.underline_to_camel)
                // 设置表名前缀
                .setTablePrefix("ex_").setInclude("ex_true_false");
        // 4.包名策略配置
        PackageConfig packageConfig = new PackageConfig();
        // 设置每一种代码生成的包名，傻子都能看懂，不写注释了
        packageConfig.setParent("com.exam")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("pojo")
                .setXml("mapper");
        // 5.整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setGlobalConfig(config)
                .setStrategy(strategyConfig);
        // 6.执行
        autoGenerator.execute();
    }


}
