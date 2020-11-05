package com.study.shiyanchang.common.entity.po;

import java.io.Serializable;
import lombok.Data;

/**
 * 公司表(TCompany)实体类
 *
 * @author makejava
 * @since 2020-11-05 23:13:06
 */
@Data
public class TCompany implements Serializable {
    private static final long serialVersionUID = -70120460153551675L;

    private Long id;
    /**
    * 公司名称
    */
    private String name;
    /**
    * 详细地址
    */
    private String addr;
    /**
    * 联系方式
    */
    private String tel;
    /**
    * 联系人
    */
    private String contact;
    /**
    * 所属省级ID
    */
    private Integer provinceId;
    /**
    * 所属市级ID
    */
    private Integer cityId;
    /**
    * 所属区域ID
    */
    private Integer areaId;
    /**
    * 是否停用，1=是，0=否
    */
    private Integer isStop;
    /**
    * 是否为运营商公司（1=是，0=否）-- 手动维护
    */
    private Integer isRoot;
    /**
    * 创建时间戳（毫秒数）
    */
    private Long gmtCreate;

}