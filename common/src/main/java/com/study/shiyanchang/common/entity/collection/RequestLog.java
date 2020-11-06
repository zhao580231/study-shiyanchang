package com.study.shiyanchang.common.entity.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "request_log")
public class RequestLog {

    /**
     * 请求模块
     */
    private String module;

    /**
     * 实际调用的接口地址
     */
    private String realUrl;

    /**
     * 客户端发来的请求地址
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 发起请求的时间
     */
    private LocalDateTime requestDateTimes;
}
