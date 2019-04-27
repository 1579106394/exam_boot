package com.exam.config;

import com.exam.constant.CoreConstant;
import com.exam.interceptor.CorsInterceptor;
import com.exam.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置拦截器
 *
 * @author yds
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**", "/file/**");
        registry.addInterceptor(new CorsInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**", "/file/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**")
                .addResourceLocations("file:" + CoreConstant.IMG_URL, "file:" + CoreConstant.PAPER_URL);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}