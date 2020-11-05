package com.study.shiyanchang.common.entity.po;

import java.io.Serializable;
import lombok.Data;

/**
 * 试验场（场地）表(TSite)实体类
 *
 * @author makejava
 * @since 2020-11-05 23:13:06
 */
@Data
public class TSite implements Serializable {
    private static final long serialVersionUID = 896474639006263550L;
    /**
    * 试验场（场地）ID，主键
    */
    private Long id;
    /**
    * 试验场名称
    */
    private String name;
    /**
    * 详细地址
    */
    private String addr;
    /**
    * 经度
    */
    private Double lng;
    /**
    * 纬度
    */
    private Double lat;
    /**
    * 所属公司ID
    */
    private Long companyId;
    /**
    * 创建时间（毫秒数）
    */
    private Long gmtCreate;

}