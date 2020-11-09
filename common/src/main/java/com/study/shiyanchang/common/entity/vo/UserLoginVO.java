package com.study.shiyanchang.common.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserLoginVO", description="用户登录信息出参")
public class UserLoginVO {
    @ApiModelProperty(value = "用户ID")
    private Long id;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "用户token（唯一）")
    private String token;
}
