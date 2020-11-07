package com.study.shiyanchang.api.filter;

import com.github.phantomthief.scope.Scope;
import com.study.shiyanchang.api.config.MyProperties;
import com.study.shiyanchang.api.service.UserTokenService;
import com.study.shiyanchang.common.base.CurrentScope;
import com.study.shiyanchang.common.entity.po.TUser;
import com.study.shiyanchang.common.excption.ServiceException;
import com.study.shiyanchang.common.util.ApplicationContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.study.shiyanchang.common.base.ResultCode.ERROR_SYSTEM;

public class UserTokenFilter extends OncePerRequestFilter {
    private static final String DEFAULT_TOKEN_HEAD_KEY = "Authorization"; // token放得位置

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        MyProperties myProperties = ApplicationContextUtil.getBean(MyProperties.class);
        boolean isLogin = myProperties.getLoginPath().equals(requestURI);
        Scope.beginScope();

        try {
            if (!isLogin) {
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
