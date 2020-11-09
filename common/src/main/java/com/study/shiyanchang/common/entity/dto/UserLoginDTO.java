package com.study.shiyanchang.common.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户登录接口数据传输对象
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserLoginDTO", description="用户登录信息入参")
public class UserLoginDTO {
    /**
     * 用户输入的账号
     */
    @ApiModelProperty(value = "用户输入的账号")
    private String userName;
    /**
     * 用户输入的密码
     */
    @ApiModelProperty(value = "用户输入的密码")
    private String password;
}
