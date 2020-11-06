package com.study.shiyanchang.common.util;

public class CheckUtil {
    public static long checkStrIsTimesNum(String str){
        try {
            return Long.parseLong(str);
        }catch (Exception e){
            return -1;
        }
    }
}
