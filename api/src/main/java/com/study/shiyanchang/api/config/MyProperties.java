package com.study.shiyanchang.api.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 在nacos的配置属性
 */
@Component
@Data
@RefreshScope
public class MyProperties {
    @Value("${login_path}")
    private String loginPath;
}
