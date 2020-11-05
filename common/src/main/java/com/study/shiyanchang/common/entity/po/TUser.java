package com.study.shiyanchang.common.entity.po;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户表(TUser)实体类
 *
 * @author makejava
 * @since 2020-11-05 23:13:06
 */
@Data
public class TUser implements Serializable {
    private static final long serialVersionUID = -94619548999244002L;
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
    /**
    * 所属公司ID
    */
    private Long companyId;
    /**
    * 账号
    */
    private String userName;
    /**
    * 密码
    */
    private String pwd;
    /**
    * 状态（0=正常，1=锁定）
    */
    private Integer state;
    /**
    * 角色ID
    */
    private Integer roleId;
    
    private Long gmtCreate;
    /**
    * 最后登录时间
    */
    private Long lastTime;
    /**
    * 用户token（唯一）
    */
    private String token;

}