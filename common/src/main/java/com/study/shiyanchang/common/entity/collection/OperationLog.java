package com.study.shiyanchang.common.entity.collection;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "operation_log")
public class OperationLog {
    /**
     * 操作人ID
     */
    private Long userId;

    /**
     * 操作时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 操作内容
     */
    private String content;
}
