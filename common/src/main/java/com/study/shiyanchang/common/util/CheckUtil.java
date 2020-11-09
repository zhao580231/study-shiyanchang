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
     * 查看请求的url是否包含在api集合地址内
     * @param apiPathListStr 允许访问的api集合地址
     * @param realPath 请求的url
     * @return true=允许，false=不允许
     */
    public static boolean checkRealUrl(String apiPathListStr, String realPath){
        String separator = "/";
        boolean result = false;
        List<String> apiPathList = JSONArray.parseArray(apiPathListStr, String.class);
        if(apiPathList == null || apiPathList.isEmpty()){
            return false;
        }
        try{
        String[] realPathSplit = realPath.split(separator);
        for(String apiPath: apiPathList){
            if(!result){
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
                    result = true;
                }
            }
        }
        }catch (Exception e){
            return false;
        }
        return result;
    }

    /**
     * 判断是否是swagger相关的url
     * @param requestURI
     * @return
     */
    public static boolean checkSwaggerUrl(String requestURI) {
        return requestURI.contains("swagger") || requestURI.contains("/api/v2/api-docs");
    }
}
