package com.study.shiyanchang.common.entity.po;

import java.io.Serializable;
import lombok.Data;

/**
 * 设备分类ID(TSensorType)实体类
 *
 * @author makejava
 * @since 2020-11-05 23:13:06
 */
@Data
public class TSensorType implements Serializable {
    private static final long serialVersionUID = 856203162781837243L;
    /**
    * 设备类型ID
    */
    private Integer id;
    /**
    * 设备类型名称
    */
    private String name;
    /**
    * 状态（0=启用，1=禁用）
    */
    private Integer state;

}