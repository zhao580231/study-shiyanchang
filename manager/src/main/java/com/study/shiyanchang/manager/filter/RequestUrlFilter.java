package com.study.shiyanchang.manager.filter;

import com.alibaba.fastjson.JSONObject;
import com.github.phantomthief.scope.Scope;
import com.study.shiyanchang.common.base.ResponseUtils;
import com.study.shiyanchang.common.base.ResultCode;
import com.study.shiyanchang.common.excption.ServiceException;
import com.study.shiyanchang.common.util.CheckUtil;
import com.study.shiyanchang.manager.config.ApplicationContextUtil;
import com.study.shiyanchang.manager.config.MyProperties;
import com.study.shiyanchang.manager.service.MongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

@Slf4j
public class RequestUrlFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 请求地址
        String requestURI = request.getRequestURI();
        MyProperties myProperties = ApplicationContextUtil.getBean(MyProperties.class);
        Scope.beginScope();
        try {
            String realIp = getRealIp(request);
            if(!myProperties.getAuthorizedRequestPath().contains(realIp)){
                throw new ServiceException(ResultCode.ERROR_METHODS,"请求IP未注册到白名单内！");
            }
            int lastIndexOf = requestURI.lastIndexOf("/");
            String times = requestURI.substring(lastIndexOf + 1);
            long timesNum = CheckUtil.checkStrIsTimesNum(times);
            if(timesNum == -1){
                throw new ServiceException(ResultCode.ERROR_METHODS,"请求接口未按照要求传递必须参数！");
            }
            long nowTime = System.currentTimeMillis();
            if(nowTime - timesNum > 10000){
                throw new ServiceException(ResultCode.ERROR_METHODS,"调用接口所传的时间戳超过固定期限！");
            }
            String realUrl = requestURI.substring(0,lastIndexOf);
            if(!myProperties.getApiPathList().contains(realUrl)){
                throw new ServiceException(ResultCode.ERROR_METHODS,"调用接口地址未经过许可，或者不存在！");
            }
            MongoService mongoService = ApplicationContextUtil.getBean(MongoService.class);
            mongoService.saveRequestLog(request);
            // 不需要前面项目名的
            request.getRequestDispatcher(realUrl.replace(request.getContextPath(),"")).forward(request, response);
        }catch (ServiceException e){
            int code = e.getCode();
            String message = e.getMessage();
            response.setStatus(code);
            JSONObject json = new JSONObject();
            json.put("code", code);
            json.put("message", message);
            ResponseUtils.output(response, JSONObject.toJSONString(json));
        }finally {
            Scope.endScope();
        }
    }

    public static String getRealIp(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = request.getHeader("X-Forwarded-For");
        if (log.isInfoEnabled()) {
            log.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
                }
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }
}
