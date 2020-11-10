package com.study.shiyanchang.common.entity.collection;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "login_error_log")
public class LoginErrorLog {
    /**
     * 账号
     */
    private String userName;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 登录时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 访问IP
     */
    private String addr;
}
