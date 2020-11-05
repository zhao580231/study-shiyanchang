package com.study.shiyanchang.common.entity.po;

import java.io.Serializable;
import lombok.Data;

/**
 * 设备报修记录表(TSensorRepair)实体类
 *
 * @author makejava
 * @since 2020-11-05 23:13:06
 */
@Data
public class TSensorRepair implements Serializable {
    private static final long serialVersionUID = 983975259550070531L;
    /**
    * 报修记录ID
    */
    private Long id;
    /**
    * 设备ID
    */
    private Long sensorId;
    /**
    * 创建时间（毫秒数）
    */
    private Long gmtCreate;
    /**
    * 报修状态（0=未处理，1-已处理）
    */
    private Integer state;
    /**
    * 申请用户ID
    */
    private Long applyUserId;
    /**
    * 处理人ID
    */
    private Long solveUserId;
    /**
    * 处理时间（毫秒数）
    */
    private Long solveTime;

}