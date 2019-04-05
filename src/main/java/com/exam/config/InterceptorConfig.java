package com.exam.config;

import com.exam.interceptor.CORSInterceptor;
import com.exam.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置拦截器
 * @author yds
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Value("${UPLOAD_URL}")
    private String UPLOAD_URL;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**", "/file/**");
        registry.addInterceptor(new CORSInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**", "/file/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + UPLOAD_URL);
        System.out.println("============================================" + "file:" + UPLOAD_URL);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
