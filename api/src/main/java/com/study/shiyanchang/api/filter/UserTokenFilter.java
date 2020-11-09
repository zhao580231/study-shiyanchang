package com.study.shiyanchang.api.filter;

import com.alibaba.fastjson.JSONArray;
import com.github.phantomthief.scope.Scope;
import com.study.shiyanchang.api.config.MyProperties;
import com.study.shiyanchang.api.service.UserTokenService;
import com.study.shiyanchang.common.base.CurrentScope;
import com.study.shiyanchang.common.entity.po.TUser;
import com.study.shiyanchang.common.excption.ServiceException;
import com.study.shiyanchang.common.util.ApplicationContextUtil;
import com.study.shiyanchang.common.util.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.study.shiyanchang.common.base.ResultCode.ERROR_SYSTEM;

@Slf4j
public class UserTokenFilter extends OncePerRequestFilter {
    private static final String DEFAULT_TOKEN_HEAD_KEY = "Authorization"; // token放得位置

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        MyProperties myProperties = ApplicationContextUtil.getBean(MyProperties.class);
        // 验证是否为登录地址
        boolean isLogin = myProperties.getLoginPath().equals(requestURI);
        // 验证是否是swagger的接口，注意这部分，正式环境上需要注释掉，并且在nacos上需要删除对应的属性
        boolean isSwagger = CheckUtil.checkSwaggerUrl(requestURI);
        Scope.beginScope();
        try {
            // 不是swagger和登录地址，那么就需要加验证咯
            if (!isSwagger && !isLogin) {
                String token = request.getHeader(DEFAULT_TOKEN_HEAD_KEY);
                if(StringUtils.isEmpty(token)){
                    throw new ServiceException(ERROR_SYSTEM, "请登录");
                }
                UserTokenService userTokenService = ApplicationContextUtil.getBean(UserTokenService.class);
                TUser user = userTokenService.getUserByToken(token);
                if(user == null){
                    throw new ServiceException(ERROR_SYSTEM, "用户不存在");
                }
                CurrentScope.setLoginUserId(user.getId());
                CurrentScope.setLoginUserToken(token);
            }
            filterChain.doFilter(request, response);
        }finally {
            Scope.endScope();
        }
    }

}
