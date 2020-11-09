package com.study.shiyanchang.manager.filter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.phantomthief.scope.Scope;
import com.study.shiyanchang.common.base.ResponseUtils;
import com.study.shiyanchang.common.base.ResultCode;
import com.study.shiyanchang.common.excption.ServiceException;
import com.study.shiyanchang.common.util.ApplicationContextUtil;
import com.study.shiyanchang.common.util.CheckUtil;
import com.study.shiyanchang.common.util.IpUtil;
import com.study.shiyanchang.manager.config.MyProperties;
import com.study.shiyanchang.manager.service.MongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.study.shiyanchang.common.base.ResultCode.ERROR_SYSTEM;

@Slf4j
public class RequestUrlFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 请求地址
        String requestURI = request.getRequestURI();
        MyProperties myProperties = ApplicationContextUtil.getBean(MyProperties.class);
        Scope.beginScope();
        try {
            String realIp = IpUtil.getRealIp(request);
            String authorizedRequestPath = myProperties.getAuthorizedRequestPath();
            if(!authorizedRequestPath.equals("*") && !authorizedRequestPath.contains(realIp)){
                throw new ServiceException(ResultCode.ERROR_METHODS,"请求IP未注册到验证名单内！");
            }
            int lastIndexOf = requestURI.lastIndexOf("/");
            String times = requestURI.substring(lastIndexOf + 1);
            long timesNum = CheckUtil.checkStrIsTimesNum(times);
            if(timesNum == -1){
                log.error("请求接口未按照要求传递必须参数：{}",timesNum);
                throw new ServiceException(ResultCode.ERROR_METHODS,"请求接口未按照要求传递必须参数！");
            }
            long nowTime = System.currentTimeMillis();
            if(nowTime - timesNum > 10000){
                log.error("调用接口所传的时间戳超过固定期限：{}",nowTime - timesNum);
                throw new ServiceException(ResultCode.ERROR_METHODS,"调用接口所传的时间戳超过固定期限！");
            }
            String realUrl = requestURI.substring(0,lastIndexOf);
            if(!CheckUtil.checkRealUrl(myProperties.getApiPathList(), realUrl)){
                //myProperties.getApiPathList().contains(realUrl)
                log.error("调用接口地址未经过许可，或者不存在：{}",realUrl);
                throw new ServiceException(ResultCode.ERROR_METHODS,"调用接口地址未经过许可，或者不存在！");
            }
            MongoService mongoService = ApplicationContextUtil.getBean(MongoService.class);
            mongoService.saveRequestLog(request);
            // 不需要前面项目名的
            request.getRequestDispatcher(realUrl.replace(request.getContextPath(),"")).forward(request, response);
        }finally {
            Scope.endScope();
        }
    }
}
