package com.study.shiyanchang.common.entity.po;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户与试验场关联关系表(TUserSite)实体类
 *
 * @author makejava
 * @since 2020-11-05 23:13:06
 */
@Data
public class TUserSite implements Serializable {
    private static final long serialVersionUID = 815607674063805429L;
    /**
    * 用户ID
    */
    private Long userId;
    /**
    * 试验场（场地）ID
    */
    private Long siteId;

}