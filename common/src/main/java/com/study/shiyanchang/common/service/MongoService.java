package com.study.shiyanchang.common.service;

import com.study.shiyanchang.common.base.CurrentScope;
import com.study.shiyanchang.common.entity.collection.LoginErrorLog;
import com.study.shiyanchang.common.entity.collection.OperationLog;
import com.study.shiyanchang.common.entity.collection.RequestLog;
import com.study.shiyanchang.common.excption.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class MongoService {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存请求日志
     * @param request
     */
    public void saveRequestLog(HttpServletRequest request) throws ServiceException {
        // 请求地址
        String requestURI = request.getRequestURI();
        int lastIndexOf = requestURI.lastIndexOf("/");
        String realUrl = requestURI.substring(0,lastIndexOf);
        RequestLog requestLog = new RequestLog();
        requestLog.setModule(request.getContextPath());
        requestLog.setRealUrl(realUrl);
        requestLog.setRequestUrl(requestURI);
        requestLog.setRequestDateTimes(LocalDateTime.now());
        // 获取参数
        int len = request.getContentLength();
        try {
            if(len == -1){
                // query参数
                requestLog.setParams(request.getQueryString());
            }else{
                // 可以从post接收到请求参数
                ServletInputStream iii = request.getInputStream();
                byte[] buffer = new byte[len];
                iii.read(buffer, 0, len);
                requestLog.setParams(new String(buffer));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        mongoTemplate.insert(requestLog);
    }

    /**
     * 新增操作日志的记录
     * @param content 操作内容，由调用方自定义
     */
    public void insertOperation(String content){
        OperationLog operationLog = new OperationLog();
        operationLog.setUserId(CurrentScope.getLoginUserId());
        operationLog.setGmtCreate(LocalDateTime.now());
        operationLog.setContent(content);
        mongoTemplate.insert(operationLog);
    }

    /**
     * 新增登录错误日志的记录
     * @param userName
     * @param pwd
     */
    public void insertLoginError(String userName, String pwd){
        LoginErrorLog loginErrorLog = new LoginErrorLog();
        loginErrorLog.setUserName(userName);
        loginErrorLog.setPwd(pwd);
        loginErrorLog.setGmtCreate(LocalDateTime.now());
        loginErrorLog.setAddr(CurrentScope.getLoginUserAddr().get());
    }
}
