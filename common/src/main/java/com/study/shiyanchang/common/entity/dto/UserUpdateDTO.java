package com.study.shiyanchang.common.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserUpdateDTO", description="修改用户信息入参")
public class UserUpdateDTO {
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 联系方式
     */
    private String phone;
}
