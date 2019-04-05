package com.exam.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.exam.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 其他配置
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/3/28 0028 上午 10:03
 */
@Configuration
public class OtherConfig {

    /**
     * 注入id生成器
     * @return
     */
    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

    /**
     * 准入乐观锁使用
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 逻辑删除
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

}
