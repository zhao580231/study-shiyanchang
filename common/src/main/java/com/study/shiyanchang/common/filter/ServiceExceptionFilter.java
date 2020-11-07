package com.study.shiyanchang.common.filter;

import com.alibaba.fastjson.JSONObject;
import com.study.shiyanchang.common.base.ResponseUtils;
import com.study.shiyanchang.common.excption.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.NestedServletException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.alibaba.fastjson.JSON.toJSON;
import static com.study.shiyanchang.common.base.ResultCode.ERROR_SYSTEM;

public class ServiceExceptionFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(ServiceExceptionFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            if (e instanceof NestedServletException) {
                if (e.getCause().getClass().equals(ServiceException.class)) {
                    ServiceException se = (ServiceException)e.getCause();
                    output(response, se.getCode(), se.getMessage());
                } else {
                    output(response, ERROR_SYSTEM, "系统异常，请查看日志");
                }
            }
            else if (e instanceof ServiceException) {
                output(response, ((ServiceException) e).getCode(), e.getMessage());
            } else {
                output(response, ERROR_SYSTEM, "系统异常，请查看日志");
            }
        }
    }

    private void output(HttpServletResponse response,int code,String msg){
        response.setStatus(code);
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("message", msg);
        ResponseUtils.output(response, JSONObject.toJSONString(json));
    }
}
