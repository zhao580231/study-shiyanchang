package com.study.shiyanchang.common.entity.po;

import java.io.Serializable;
import lombok.Data;

/**
 * 设备表(TSensor)实体类
 *
 * @author makejava
 * @since 2020-11-05 23:13:06
 */
@Data
public class TSensor implements Serializable {
    private static final long serialVersionUID = 791367769248439718L;
    /**
    * 设备ID
    */
    private Long id;
    /**
    * 设备名称
    */
    private String name;
    /**
    * 类型ID
    */
    private Integer typeId;
    /**
    * 设备状态（1=正常运行，0=故障报修）
    */
    private Integer state;
    /**
    * 所属试验场（场地）ID
    */
    private Long siteId;
    /**
    * 创建时间（毫秒数）
    */
    private Long gmtCreate;

}