package com.study.shiyanchang.common.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserInfoVO", description="用户详细信息出参")
public class UserInfoVO {
    @ApiModelProperty(value = "用户ID")
    private Long id;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "所属公司ID")
    private Long companyId;

    @ApiModelProperty(value = "状态（0=正常，1=锁定）")
    private Integer state;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;

    @ApiModelProperty(value = "注册时间")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "最后登录时间")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime lastTime;

    @ApiModelProperty(value = "用户token（唯一）")
    private String token;
}
