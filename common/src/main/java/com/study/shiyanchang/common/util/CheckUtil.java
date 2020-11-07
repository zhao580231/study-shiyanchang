package com.study.shiyanchang.common.util;

import com.alibaba.fastjson.JSONArray;

import java.util.List;

public class CheckUtil {
    /**
     * 检查字符串是否是时间戳毫秒数，将字符串转换成long并返回
     * @param str
     * @return
     */
    public static long checkStrIsTimesNum(String str){
        try {
            return Long.parseLong(str);
        }catch (Exception e){
            return -1;
        }
    }

    /**
     * 查看请求的url是否在各模块允许访问的api集合地址内
     * @param apiPathListStr 允许访问的api集合地址
     * @param realPath 请求的url
     * @return
     */
    public static boolean checkRealUrl(String apiPathListStr, String realPath){
        String separator = "/";
        boolean result = true;
        List<String> apiPathList = JSONArray.parseArray(apiPathListStr, String.class);
        if(apiPathList == null || apiPathList.isEmpty()){
            return result;
        }
        try{
        String[] realPathSplit = realPath.split(separator);
        for(String apiPath: apiPathList){
            if(result){
                String[] aStr = apiPath.split(separator);
                StringBuilder newStr = new StringBuilder();
                for(int i=0;i<aStr.length;i++){
                    String str = aStr[i];
                    // 忽略不判断带path参数{XXX}的
                    if(str.contains("{")){
                        str = realPathSplit[i];
                    }
                    newStr.append(i == 0 ? "" : "/").append(str);
                }
                if(newStr.toString().equals(realPath)){
                    result = false;
                }
            }
        }
        }catch (Exception e){
            return true;
        }
        return result;
    }
}
