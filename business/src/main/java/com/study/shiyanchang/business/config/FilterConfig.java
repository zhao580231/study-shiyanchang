package com.study.shiyanchang.business.config;

import com.study.shiyanchang.business.filter.RequestUrlFilter;
import com.study.shiyanchang.common.filter.ServiceExceptionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注意 filter可能各个模块都不想同，这里只指定了最基础的检验token的filter order =1 （最高优先级）
 */
@Configuration
public class FilterConfig {
    /**
     * 请求地址过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean requestUrlFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new RequestUrlFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        return bean;
    }
    /**
     * 用于对ServiceException的统一处理
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean serviceExceptionFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new ServiceExceptionFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        return bean;
    }
}
