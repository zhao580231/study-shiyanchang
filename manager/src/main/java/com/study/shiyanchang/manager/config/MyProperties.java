package com.study.shiyanchang.manager.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 在nacos的配置属性，主要为了过滤器能获取到验证数据
 */
@Component
@Data
@RefreshScope
public class MyProperties {
    @Value("${api_path_list}")
    private String apiPathList;
    @Value("${authorized_request_path}")
    private String authorizedRequestPath;
}
