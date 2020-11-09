package com.study.shiyanchang.common.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 日期工具
 */
public class DateUtil {
    /**
     * 毫秒数转换成LocalDateTime
     * @param dateTime
     * @return
     */
    public static LocalDateTime long2DateTime(long dateTime){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(dateTime), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换成毫秒数
     * @param dateTime
     * @return
     */
    public static Long dateTime2Long(LocalDateTime dateTime){
        return Timestamp.valueOf(dateTime).getTime();
    }
}
